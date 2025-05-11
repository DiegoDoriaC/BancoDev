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
  public objeto: any;
  solicitarPrestamo: boolean = false;
  mostrarHistorial: boolean = false;

  constructor(
    private _utils: UtilsService,
    private _prestamo: PrestamoService,
  ) {}

  ngOnInit(): void {
    this.objeto = this._utils.recuperarObjetoDelLocalStorage('usuario');    
  }

  obtenerListadoDePrestamos(id:number){
    this._prestamo.listarTodosLosPrestamos(id).subscribe(data => {
      if(data.status){
        this.listadoPrestamos = data.data;
      }
      this.mensaje = data.message;
    })
  }

  activarSolicitudPrestamo(){
    this.mostrarHistorial = false;
    this.solicitarPrestamo = true;
  }

  activarMostrarHistorial(){
    this.solicitarPrestamo = false;
    this.obtenerListadoDePrestamos(this.objeto.id);
    this.mostrarHistorial = true; 
  }

}
