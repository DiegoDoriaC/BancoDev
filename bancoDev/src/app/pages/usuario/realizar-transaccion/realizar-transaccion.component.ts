import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TransferirDineroRequest } from 'src/app/interfaces/cuenta/TransferirDineroRequest';
import { CuentaBancariaService } from 'src/app/services/cuenta-bancaria.service';
import { UtilsService } from 'src/app/services/utils.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-realizar-transaccion',
  templateUrl: './realizar-transaccion.component.html',
  styleUrls: ['./realizar-transaccion.component.css']
})
export class RealizarTransaccionComponent {

  public mensaje = '';
  public estado:boolean = false

  public formularioTransaferir = new FormGroup({
    monto: new FormControl('', Validators.required),
    cuentaDestino: new FormControl('', Validators.required)
  })

  constructor(
      private _utils: UtilsService,
    private _cuenta: CuentaBancariaService
  ) {}

  onSubmit(){
    const montoValor = this.formularioTransaferir.get('monto')?.value ?? ''
    Swal.fire({
      title: 'Esta seguro?',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      showConfirmButton: true,
      confirmButtonText: 'Transferir',
      text: 'Está seguro que desea transferir ' + montoValor + ' soles?',
      icon: 'question',
    }).then((resultado) => {
      if(resultado.isConfirmed){
        this.transferirDinero()              
      }
    })
  }

  transferirDinero(){
    const data = this._utils.recuperarObjetoDelLocalStorage('usuario');
    const transferir: TransferirDineroRequest = {
      clienteId: data.id,
      monto: this.formularioTransaferir.get('monto')?.value ?? '',
      cuentaDestino: this.formularioTransaferir.get('cuentaDestino')?.value ?? '',
    }

    this._cuenta.transferirDinero(transferir).subscribe({
      next: (data) => {
        if(data.status){
          Swal.fire({
            title: 'Exito!',
            text: data.message,
            icon: 'success',
          }).then((result) => {
            if(result.isConfirmed)
              location.reload();
          })
        }
        else{
          Swal.fire({
            title: 'Opps!',
            text: data.message,
            icon: 'info',
          })
        }
      },
      error: (error) => {
        Swal.fire({
          title: 'Error',
          text: data.message,
          icon: 'error',
        })
      }
    })
  }

}
