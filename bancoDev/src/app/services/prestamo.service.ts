import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { ApiResponse } from '../interfaces/ApiResponse';
import { PrestamoResponse } from '../interfaces/prestamos/PrestamoResponse';
import { PrestamoCrearDto } from '../interfaces/prestamos/PrestamoCrearDto';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  private api:string = `${environment.url}/prestamo`;

  constructor( private http: HttpClient ) { }

  listarTodosLosPrestamos(idCliente:number): Observable<ApiResponse<PrestamoResponse[]>> {
    return this.http.get<ApiResponse<PrestamoResponse[]>>(`${this.api}/listarTodosLosPrestamos/${idCliente}`);
  }

  listarPrestamosPagados(idCliente:number): Observable<ApiResponse<PrestamoResponse[]>> {
    return this.http.get<ApiResponse<PrestamoResponse[]>>(`${this.api}/listarPrestamosPagados/${idCliente}`);
  }

  listarPrestamosPendientes(idCliente:number): Observable<ApiResponse<PrestamoResponse[]>> {
    return this.http.get<ApiResponse<PrestamoResponse[]>>(`${this.api}/listarPrestamosPendientes/${idCliente}`);
  }

  buscarPrestamosPorDniCliente(dni:string): Observable<ApiResponse<PrestamoResponse[]>> {
    return this.http.get<ApiResponse<PrestamoResponse[]>>(`${this.api}/buscarPrestamosPorDniCliente/${dni}`);
  }

  buscarPrestamo(id:number): Observable<ApiResponse<PrestamoResponse>> {
    return this.http.get<ApiResponse<PrestamoResponse>>(`${this.api}/buscarPrestamo/${id}`);
  }

  crearPrestamo(prestamo:PrestamoCrearDto): Observable<ApiResponse<PrestamoResponse>> {
    return this.http.post<ApiResponse<PrestamoResponse>>(`${this.api}/crearPrestamo`, prestamo);
  }

}
