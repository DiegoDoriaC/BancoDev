import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PagoResponse } from 'src/app/interfaces/prestamos/PagoResponse';
import { PagoService } from 'src/app/services/pago.service';
import { PagoRealizarRequest } from '../../../interfaces/prestamos/PagoRealizarRequest';
import { UtilsService } from 'src/app/services/utils.service';

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
    this._pago.realizarPago(pago).subscribe(data => {
      if(data.status){
        console.log(data.message);
        this._router.navigate(['cajero'])
      }
      else{
        this.mensaje = data.message
      }
    })
  }

}
