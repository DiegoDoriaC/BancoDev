import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PagoResponse } from 'src/app/interfaces/prestamos/PagoResponse';
import { PagoService } from 'src/app/services/pago.service';

@Component({
  selector: 'app-detalle-cuota',
  templateUrl: './detalle-cuota.component.html',
  styleUrls: ['./detalle-cuota.component.css']
})
export class DetalleCuotaComponent implements OnInit {

  public pago?: PagoResponse;
  public mensaje: string = '';

  constructor(
      private route: ActivatedRoute,
      private _pago: PagoService,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('numeroCuota')!;
    this.obtenerDetallePago(Number(id));
  }

  obtenerDetallePago(id:number){
    this._pago.buscarPago(id).subscribe(data => {
      if(data.status){
        this.pago = data.data;
      }
      this.mensaje = data.message;
    })
  }

}
