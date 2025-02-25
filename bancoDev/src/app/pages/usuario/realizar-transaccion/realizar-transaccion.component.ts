import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TransferirDineroRequest } from 'src/app/interfaces/cuenta/TransferirDineroRequest';
import { CuentaBancariaService } from 'src/app/services/cuenta-bancaria.service';

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
    private _cuenta: CuentaBancariaService
  ) {}

  onSubmit(){
    this.transferirDinero()
  }

  transferirDinero(){
    const transferir: TransferirDineroRequest = {
      clienteId: 3,
      monto: this.formularioTransaferir.get('monto')?.value ?? '',
      cuentaDestino: this.formularioTransaferir.get('cuentaDestino')?.value ?? '',
    }

    this._cuenta.transferirDinero(transferir).subscribe(data => {
      if(data.status){
        this.mensaje = data.message;
        this.estado = true
      }else{
        this.mensaje = data.message;
        this.estado = false
      }
    })
  }

}
