import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { ApiResponse } from '../interfaces/ApiResponse';
import { AuthResponse } from '../interfaces/login/AuthResponse';
import { ClienteCrearRequest } from '../interfaces/cliente/ClienteCrearRequest';
import { ClienteResponse } from '../interfaces/cliente/ClienteResponse';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private api:string = `${environment.url}/auth`;

  constructor( private http: HttpClient ) { }

  iniciarSesion(correo:string, password:string): Observable<ApiResponse<AuthResponse>> {
    const params = new HttpParams().set('correo', correo).set('password', password);
    return this.http.get<ApiResponse<AuthResponse>>(`${this.api}/iniciarSesion`, {params})
  }

  crearCuenta(cliente:ClienteCrearRequest): Observable<ApiResponse<ClienteResponse>> {
    return this.http.post<ApiResponse<ClienteResponse>>(`${this.api}/crearCuenta`, cliente)
  }

}
