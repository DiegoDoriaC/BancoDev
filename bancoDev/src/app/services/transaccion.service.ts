import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { ApiResponse } from '../interfaces/ApiResponse';
import { TransaccionSimpleResponse } from '../interfaces/cuenta/TransaccionSimpleResponse';
import { TransaccionCompletaResponse } from '../interfaces/cuenta/TransaccionCompletaResponse';

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {

  private api:string = `${environment.url}/transaccion`;

  constructor(private http: HttpClient) { }

  listarTransaccion(numeroCuenta:string): Observable<ApiResponse<TransaccionSimpleResponse[]>> {
    return this.http.get<ApiResponse<TransaccionSimpleResponse[]>>(`${this.api}/listarTransacciones/${numeroCuenta}`);
  }

  detalleTransaccion(id:string): Observable<ApiResponse<TransaccionCompletaResponse>> {
    return this.http.get<ApiResponse<TransaccionCompletaResponse>>(`${this.api}/detalleTransaccion/${id}`);
  }

}
