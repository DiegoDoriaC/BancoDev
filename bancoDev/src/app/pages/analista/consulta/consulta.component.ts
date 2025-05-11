import { Component, OnInit } from '@angular/core';
import { PrestamoCrearFinalDto } from 'src/app/interfaces/prestamos/PrestamoCrearFinalDto';
import { PrestamoResponse } from 'src/app/interfaces/prestamos/PrestamoResponse';
import { PrestamoService } from 'src/app/services/prestamo.service';
import { UtilsService } from 'src/app/services/utils.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  public listadoPrestamos: PrestamoResponse[] = [];
  public mensaje: string = '';

  constructor(
    private _utils: UtilsService,
    private _prestamoService: PrestamoService,
  ) {}

  ngOnInit(): void {
    const data = this._utils.recuperarObjetoDelLocalStorage('usuario');
    this.listarPrestamosPendientesEmpleado(data.id);
  }

  listarPrestamosPendientesEmpleado(idEmpleado: number){
    this._prestamoService.listarPrestamosPendientesEmpleado(idEmpleado).subscribe({
      next: (data) => {
        if(data.status){
          console.log(data.data);          
          this.listadoPrestamos = data.data;
        }
        else{
          this.mensaje = data.message
        }
      },
      error: (error) => {
        Swal.fire({
          title: 'Error',
          text: error.message,
          icon: 'error'
        })
      }
    })
  }

  aprobarPrestamo(id: number){
    Swal.fire({
      title: 'Mensaje de aprobacion',
      text: 'Desea aprobar la solicitud de prestamo?',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      showConfirmButton: true,
      confirmButtonText: 'Aprobar',
      icon: 'question'
    }).then((resultado) => {
      if(resultado.isConfirmed){
        this._prestamoService.crearPrestamo(id).subscribe({
          next: (data) => {
            if(data.status){
              Swal.fire({
                title: 'Exito!',
                text: data.message,
                icon: 'success'
              }).then((resultado) => {
                location.reload
              })
            }
            else{
              Swal.fire({
                title: 'Opps!',
                text: data.message,
                icon: 'error'
              })
            }
          },
          error: (error) => {
            Swal.fire({
                title: 'Error!',
                text: 'Ocurrio un error inesperado, contacte con su proveedor de servicios, error: ' + error.message,
                icon: 'error'
              })
          }
        })
      }
    })
  }

}
