import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ClienteCrearRequest } from 'src/app/interfaces/cliente/ClienteCrearRequest';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent {

  public formularioRegistrarse = new FormGroup({
    nombres: new FormControl('', Validators.required),
    apellidos: new FormControl('', Validators.required),
    dni: new FormControl('', Validators.required),
    fecha: new FormControl('', Validators.required),
    direccion: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required)
  })

  constructor( private _authService: AuthService ) {}

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
        console.log(data.message);
        } else {
          console.log(data.message);
        }
      })
    }


}
