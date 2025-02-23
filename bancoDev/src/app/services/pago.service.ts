import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { ApiResponse } from '../interfaces/ApiResponse';
import { PagoResponse } from '../interfaces/prestamos/PagoResponse';
import { PagoRealizarRequest } from '../interfaces/prestamos/PagoRealizarRequest';
import { RealizarPagoTransaccionRequest } from '../interfaces/prestamos/RealizarPagoTransaccionRequest';

@Injectable({
  providedIn: 'root'
})
export class PagoService {

  private api:string = `${environment.url}/pago`;

  constructor( private http: HttpClient ) { }

  listarTodosLosPagos(idPrestamo:number): Observable<ApiResponse<PagoResponse[]>> {
    return this.http.get<ApiResponse<PagoResponse[]>>(`${this.api}/listarTodosLosPagos/${idPrestamo}`)
  }

  listarPagosPagados(idPrestamo:number): Observable<ApiResponse<PagoResponse[]>> {
    return this.http.get<ApiResponse<PagoResponse[]>>(`${this.api}/listarPagosPagados/${idPrestamo}`)
  }

  listarPagosPendiendes(idPrestamo:number): Observable<ApiResponse<PagoResponse[]>> {
    return this.http.get<ApiResponse<PagoResponse[]>>(`${this.api}/listarPagosPendiendes/${idPrestamo}`)
  }

  buscarPago(id:number): Observable<ApiResponse<PagoResponse>> {
    return this.http.get<ApiResponse<PagoResponse>>(`${this.api}/buscarPago/${id}`)
  }

  realizarPago(pagoRealizar:PagoRealizarRequest): Observable<ApiResponse<PagoResponse>> {
    return this.http.put<ApiResponse<PagoResponse>>(`${this.api}/realizarPago`, pagoRealizar)
  }

  realizarPagoTransaccion(pagoRealizar:RealizarPagoTransaccionRequest): Observable<ApiResponse<PagoResponse>> {
    return this.http.put<ApiResponse<PagoResponse>>(`${this.api}/realizarPagoTransaccion`, pagoRealizar)
  }

}
