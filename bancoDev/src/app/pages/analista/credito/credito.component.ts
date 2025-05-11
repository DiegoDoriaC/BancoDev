import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PrestamoResponse } from 'src/app/interfaces/prestamos/PrestamoResponse';
import { PrestamoService } from 'src/app/services/prestamo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-credito',
  templateUrl: './credito.component.html',
  styleUrls: ['./credito.component.css']
})
export class CreditoComponent implements OnInit {

  public formularioConsultar!: FormGroup
  public listadoPrestamos: PrestamoResponse[] = [];
  public mensaje: string = '';

  constructor(
    private fb: FormBuilder,
    private _prestamoService: PrestamoService
  ) { }


  ngOnInit(): void {
    this.formularioConsultar = this.fb.group({
      dni: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]]
    })
  }

  onSubmit(){
    if(this.formularioConsultar.valid){
      const dni = this.formularioConsultar.get('dni')?.value
      this.buscarPrestamosPorDniCliente(dni);
    }
    else{
      this.formularioConsultar.markAllAsTouched();
    }
  }

  buscarPrestamosPorDniCliente(dni: string){
    this._prestamoService.buscarPrestamosPorDniCliente(dni).subscribe({
      next: (data) => {
        if(data.status){
          this.listadoPrestamos = data.data
        }
        else{
          this.mensaje = data.message;
          Swal.fire({
            title: 'No encontrado',
            text: data.message,
            icon: 'info'
          })
        }
      },
      error: (error) => {
        Swal.fire({
          title: 'Opps!',
          text: 'Ocurrio un error, por favor contacte con su proveedor de servicios. Error: ' + error.message,
          icon: 'error'
        }).then((result) => {
          if(result.isConfirmed){
            location.reload()
          }
        })
      }
    })
  }

  campoEsValido(campo: string): boolean {
    const control = this.formularioConsultar.get(campo);
    return !!control && control.invalid && control.touched;
  }

}
