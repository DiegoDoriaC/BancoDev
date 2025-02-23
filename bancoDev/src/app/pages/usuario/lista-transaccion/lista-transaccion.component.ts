import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TransaccionSimpleResponse } from 'src/app/interfaces/cuenta/TransaccionSimpleResponse';
import { TransaccionService } from 'src/app/services/transaccion.service';

@Component({
  selector: 'app-lista-transaccion',
  templateUrl: './lista-transaccion.component.html',
  styleUrls: ['./lista-transaccion.component.css']
})
export class ListaTransaccionComponent implements OnInit {

  public listadoTransacciones: TransaccionSimpleResponse[] = [];

  constructor(
    private route: ActivatedRoute,
    private _transaccion: TransaccionService,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('numeroCuenta')!;
    this.obtenerListadoDeTransacciones(id);
  }

  obtenerListadoDeTransacciones(numeroCuenta:string){
    this._transaccion.listarTransaccion(numeroCuenta).subscribe(data => {
      if(data.status){
        this.listadoTransacciones = data.data;
      }
    })
  }

}
