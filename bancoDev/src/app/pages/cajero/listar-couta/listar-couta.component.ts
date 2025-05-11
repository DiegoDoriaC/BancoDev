import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PagoResponse } from 'src/app/interfaces/prestamos/PagoResponse';
import { PagoService } from 'src/app/services/pago.service';
import { PagoRealizarRequest } from '../../../interfaces/prestamos/PagoRealizarRequest';
import { UtilsService } from 'src/app/services/utils.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-couta',
  templateUrl: './listar-couta.component.html',
  styleUrls: ['./listar-couta.component.css']
})
export class ListarCoutaComponent implements OnInit {

  public listadoPagos: PagoResponse[] = [];
  public mensaje: string = '';

  constructor(
      private _utils: UtilsService,
    private route: ActivatedRoute,
    private _pago: PagoService,
    private _router: Router,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('idCuota')!;
    this.obtenerListadoDePagos(Number(id));
  }

  obtenerListadoDePagos(id:number){
    this._pago.listarTodosLosPagos(id).subscribe(data => {
      if(data.status){
        this.listadoPagos = data.data
      }
      this.mensaje = data.message
    })
  }

  registrarPagoDeCuota(idCuota: number, montoPago: number){    
    const objeto = this._utils.recuperarObjetoDelLocalStorage('usuario');
    const pago: PagoRealizarRequest = {
      pagoId: idCuota,
      montoPagado: montoPago.toString(),
      empleadoId: objeto.id
    }

    Swal.fire({
      title: 'Esta Seguro?',
      text: 'Esta seguro que desea realizar el pago de la cuota?',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      showConfirmButton: true,
      confirmButtonText: 'Sí, pagar'
    }).then((resultado) => {
      if(resultado.isConfirmed){
        this._pago.realizarPago(pago).subscribe(data => {
          if(data.status){
            Swal.fire({
              title: 'Exito',
              text: data.message,
              icon: 'success'
            }).then((resultado) => {
              if(resultado.isConfirmed)
                this._router.navigate(['cajero'])
            })
          }
          else{
             Swal.fire({
              title: 'Exito',
              text: data.message,
              icon: 'success'
            })
          }
        })
      }
    })

    
  }

}
