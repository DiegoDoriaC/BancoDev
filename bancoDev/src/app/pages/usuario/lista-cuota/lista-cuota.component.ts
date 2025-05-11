import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PagoResponse } from 'src/app/interfaces/prestamos/PagoResponse';
import { PagoService } from 'src/app/services/pago.service';

@Component({
  selector: 'app-lista-cuota',
  templateUrl: './lista-cuota.component.html',
  styleUrls: ['./lista-cuota.component.css']
})
export class ListaCuotaComponent implements OnInit {

  public listadoPagos: PagoResponse[] = [];
  public mensaje: string = '';

  constructor(
      private route: ActivatedRoute,
      private _pago: PagoService,
  ) {}


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('numeroPrestamo')!;
    this.obtenerListadoDePagos(Number(id));
  }

  obtenerListadoDePagos(id:number){
    this._pago.listarTodosLosPagos(id).subscribe(data => {
      if(data.status){
        console.log(data.data);
        this.listadoPagos = data.data
      }
      console.log(data.data);
      this.mensaje = data.message
    })
  }



}
