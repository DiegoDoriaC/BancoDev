import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { ApiResponse } from '../interfaces/ApiResponse';
import { CuentaResponse } from '../interfaces/cuenta/CuentaResponse';
import { TransferirDineroRequest } from '../interfaces/cuenta/TransferirDineroRequest';
import { TransferenciaResponse } from '../interfaces/cuenta/TransferenciaResponse';

@Injectable({
  providedIn: 'root'
})
export class CuentaBancariaService {

  private api:string = `${environment.url}/cuentaBancaria`;

  constructor(private http: HttpClient) { }

  verEstadoCuenta(idCliente:number): Observable<ApiResponse<CuentaResponse>> {
    return this.http.get<ApiResponse<CuentaResponse>>(`${this.api}/detalle/${idCliente}`);
  }

  transferirDinero(transferir:TransferirDineroRequest): Observable<ApiResponse<TransferenciaResponse>> {
    return this.http.put<ApiResponse<TransferenciaResponse>>(`${this.api}/transferirDinero`, transferir);
  }

}
