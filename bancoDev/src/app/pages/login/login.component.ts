import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public formularioIniciarSesion = new FormGroup({
    correo: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required)
  })

  constructor(
    private _authService: AuthService ,
    private _utils: UtilsService,
    private _router: Router,
  ){}

  onSubmit(){
    this.iniciarSesion();
  }

  iniciarSesion(){
    const correo = this.formularioIniciarSesion.get('correo')?.value ?? '';
    const password = this.formularioIniciarSesion.get('password')?.value ?? '';
    this._authService.iniciarSesion(correo, password).subscribe( data => {
      if (data.status) {
        this._utils.guardarObjetoEnLocalStorage('usuario', data.data)
        switch(data.data.rol){
          case 'USER':
            this._router.navigate(['/usuario'])
            break;
          case 'ANALISTA':
            this._router.navigate(['/analista'])
            break;
          case 'CAJERO':
            this._router.navigate(['/cajero'])
            break;
          default :
            this._router.navigate(['/bancoDev'])
        }
      } else {
        console.log(data.message);
      }
    })
  }

}
