import { Component, OnInit } from '@angular/core';
import { CuentaBancariaService } from 'src/app/services/cuenta-bancaria.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-cuenta',
  templateUrl: './cuenta.component.html',
  styleUrls: ['./cuenta.component.css']
})
export class CuentaComponent implements OnInit {

  public numeroCuenta: string = ''
  public dineroActual: string = ''

  constructor(
    private _utils: UtilsService,
    private _cuenta: CuentaBancariaService
  ) {}

  ngOnInit(): void {
    const objeto = this._utils.recuperarObjetoDelLocalStorage('usuario');
    this.setearEstadoCuenta(objeto.id);
  }

  setearEstadoCuenta(id:number){
    this._cuenta.verEstadoCuenta(id).subscribe(data => {
      if(data.status){
        this.numeroCuenta = data.data.numeroCuenta,
        this.dineroActual = data.data.montoDinero
      }
    })
  }


}




