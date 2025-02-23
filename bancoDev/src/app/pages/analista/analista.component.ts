import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-analista',
  templateUrl: './analista.component.html',
  styleUrls: ['./analista.component.css']
})
export class AnalistaComponent implements OnInit {

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
