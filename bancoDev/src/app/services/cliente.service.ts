import { Injectable } from '@angular/core';
import { environment } from 'src/environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../interfaces/ApiResponse';
import { ClienteResponse } from '../interfaces/cliente/ClienteResponse';
import { ClienteCrearRequest } from '../interfaces/cliente/ClienteCrearRequest';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private api:string = `${environment.url}/cliente`;

  constructor( private http: HttpClient ) { }

  listarClientesActivos(): Observable<ApiResponse<ClienteResponse[]>> {
    return this.http.get<ApiResponse<ClienteResponse[]>>(`${this.api}/listar`);
  }

  buscarClientePorId(id:number): Observable<ApiResponse<ClienteResponse>> {
    return this.http.get<ApiResponse<ClienteResponse>>(`${this.api}/buscarPorId/${id}`);
  }

  buscarPorDni(dni:string): Observable<ApiResponse<ClienteResponse>> {
    return this.http.get<ApiResponse<ClienteResponse>>(`${this.api}/buscarPorDni/${dni}`);
  }

  crearCliente(cliente:ClienteCrearRequest): Observable<ApiResponse<ClienteResponse>> {
    return this.http.post<ApiResponse<ClienteResponse>>(`${this.api}/crear`, cliente);
  }

}
