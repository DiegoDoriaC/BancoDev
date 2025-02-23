import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-cajero',
  templateUrl: './cajero.component.html',
  styleUrls: ['./cajero.component.css']
})
export class CajeroComponent implements OnInit {

  public nombreUsuario: string = ''

  constructor(
    private _utils: UtilsService,
    private _route: Router,
  ) {}

  ngOnInit(): void {
    const data = this._utils.recuperarObjetoDelLocalStorage('usuario');
    this.nombreUsuario = data?.nombres;
  }

  cerrarSesion(){
    this._utils.eliminarObjetoDeLocalStorage('usuario');
    this._route.navigate(['/bancoDev']);
  }

}
