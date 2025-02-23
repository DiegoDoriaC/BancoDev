import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TransaccionCompletaResponse } from 'src/app/interfaces/cuenta/TransaccionCompletaResponse';
import { TransaccionService } from 'src/app/services/transaccion.service';

@Component({
  selector: 'app-detalle-transaccion',
  templateUrl: './detalle-transaccion.component.html',
  styleUrls: ['./detalle-transaccion.component.css']
})
export class DetalleTransaccionComponent implements OnInit {

  public transaccion?: TransaccionCompletaResponse;

  constructor(
    private route: ActivatedRoute,
    private _transaccion: TransaccionService,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('numeroTransaccion')!;
    this.obtenerDetalleTransaccion(id);
  }

  obtenerDetalleTransaccion(id:string){
    this._transaccion.detalleTransaccion(id).subscribe(data => {
      if(data.status){
        this.transaccion = data.data
      }
    })
  }



}
