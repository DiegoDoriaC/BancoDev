import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteCrearRequest } from 'src/app/interfaces/cliente/ClienteCrearRequest';
import { AuthService } from 'src/app/services/auth.service';
import { UtilsService } from 'src/app/services/utils.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent {

  public formularioRegistrarse = new FormGroup({
    nombres: new FormControl('', Validators.required),
    apellidos: new FormControl('', Validators.required),
    dni: new FormControl('', [Validators.required, Validators.pattern('^\d{8}$')]),
    fecha: new FormControl('', Validators.required),
    direccion: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required)
  })

  constructor( 
    private _authService: AuthService,
    private _utils: UtilsService,
    private _router: Router,
  ) {}

  onSubmit(){
    this.iniciarSesion();
  }

  iniciarSesion(){
    const clienteCrea:ClienteCrearRequest = {
      nombres: this.formularioRegistrarse.get('nombres')?.value ?? '',
      apellidos: this.formularioRegistrarse.get('apellidos')?.value ?? '',
      dni: this.formularioRegistrarse.get('dni')?.value ?? '',
      fechaNacimiento: this.formularioRegistrarse.get('fecha')?.value ?? '',
      dirrecion: this.formularioRegistrarse.get('direccion')?.value ?? '',
      correo: this.formularioRegistrarse.get('email')?.value ?? '',
      contrasenia: this.formularioRegistrarse.get('password')?.value ?? ''
    }
    this._authService.crearCuenta(clienteCrea).subscribe( data => {

      if (data.status) {
        this._utils.guardarObjetoEnLocalStorage('usuario', data.data)
        this._router.navigate(['/usuario'])
      } else {
        Swal.fire({
          title: 'Error',
          text: data.message,
          icon: 'error'
        })
      }
    })
  }
}
