import { Component, OnInit } from '@angular/core';
import { PrestamoResponse } from 'src/app/interfaces/prestamos/PrestamoResponse';
import { PrestamoService } from 'src/app/services/prestamo.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-prestamos',
  templateUrl: './prestamos.component.html',
  styleUrls: ['./prestamos.component.css']
})
export class PrestamosComponent implements OnInit {

  public listadoPrestamos: PrestamoResponse[] = [];
  public mensaje: string = '';

  constructor(
    private _utils: UtilsService,
    private _prestamo: PrestamoService,
  ) {}

  ngOnInit(): void {
    const objeto = this._utils.recuperarObjetoDelLocalStorage('usuario');
    console.log(objeto.id);
    this.obtenerListadoDePrestamos(objeto.id);
  }

  obtenerListadoDePrestamos(id:number){
    this._prestamo.listarTodosLosPrestamos(id).subscribe(data => {
      if(data.status){
        this.listadoPrestamos = data.data;
      }
      this.mensaje = data.message;
    })
  }

}
