import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmpleadoResponse } from 'src/app/interfaces/empleado/EmpleadoResponse';
import { PrestamoCrearDto } from 'src/app/interfaces/prestamos/PrestamoCrearDto';
import { EmpleadoService } from 'src/app/services/empleado.service';
import { PrestamoService } from 'src/app/services/prestamo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-solicitar-prestamo',
  templateUrl: './solicitar-prestamo.component.html',
  styleUrls: ['./solicitar-prestamo.component.css']
})
export class SolicitarPrestamoComponent implements OnInit {
  
  formularioSolicitarPrestamo!: FormGroup
  montoAproximadoPago: string = '';
  solicitudPrestamo!: PrestamoCrearDto;
  paso2: boolean = false

  listaEmpleado: EmpleadoResponse[] = [];

  imagenSeleccionada: number | null = null;
  imagenEnviada: number | null = null;

  constructor(
    private fb: FormBuilder,
    private _prestamoService: PrestamoService,
    private _empleadoService: EmpleadoService,
  ) {}

  ngOnInit(): void {
    this.listarEmpleados();
    this.formularioSolicitarPrestamo = this.fb.group({
      monto: ['', [Validators.required, Validators.min(1000)]],
      sueldo: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      plazo: ['DOCE']
    });
  }

  onSubmit(): void {
    if (this.formularioSolicitarPrestamo.valid) {
      this.solicitudPrestamo = {
        clienteId: 1,
        empleadoId: this.imagenSeleccionada!,
        plazoMeses: this.formularioSolicitarPrestamo.get('plazo')?.value,
        socreCrediticio: 3,
        sueldoCliente: this.formularioSolicitarPrestamo.get('sueldo')?.value,
        montoSolicitado: this.formularioSolicitarPrestamo.get('monto')?.value
      }
      Swal.fire({
        title: 'Estás seguro que quieres solicitar el prestamo?',
        icon: 'question',
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        showConfirmButton: true,
        confirmButtonText: 'Solicitar',
      }).then((respuesta) => {
        if(respuesta.isConfirmed) this.solicitarPrestamo(this.solicitudPrestamo)
        else if (respuesta.isDenied) Swal.fire("Se ha CANCELADO la solicitud", "", "info");
      })       
    } else {
      this.formularioSolicitarPrestamo.markAllAsTouched();
      Swal.fire({
        title: 'Datos invalidos',
        text: 'Por favor revise la información registrada',
        icon: 'warning'
      })
    }
  }

  solicitarPrestamo(prestamo: PrestamoCrearDto){
    this._prestamoService.solicitarPrestamo(prestamo).subscribe({
      next: (data) => {
        if(data.status){
          Swal.fire({
            title: 'Solicitud exitosa',
            text: data.message,
            icon: 'success'
          })
        }
        else{
          Swal.fire({
            title: 'Ocurrió un error',
            text: data.message,
            icon: 'error'
          })
        }
      },
      error: (error) => {
        Swal.fire({
          title: 'Opps!',
          text: 'Ocurrio un error al comunicarse con el servidor, por favor contacte con soporte tecnico \n Errror: ' +  error.message,
          icon: 'error'
        })
      }
    })
  }

  volver() {
      this.paso2 = false
  }

  seleccionarImagen(id: number) {
    this.imagenSeleccionada = id;
  }

  enviarSeleccion() {
    if (this.imagenSeleccionada) {
      this.imagenEnviada = this.imagenSeleccionada;
      this.paso2 = true
    }
  }

  listarEmpleados(){
    this._empleadoService.listarEmpleadoPorIdSucursal(1).subscribe({
      next: (data) => {
        if(data.status) {
          this.listaEmpleado = data.data
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

}
