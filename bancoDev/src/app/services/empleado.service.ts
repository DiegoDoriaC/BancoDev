import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { ApiResponse } from '../interfaces/ApiResponse';
import { EmpleadoResponse } from '../interfaces/empleado/EmpleadoResponse';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private api:string = `${environment.url}/empleado`;

  constructor( private http: HttpClient ) { }

  listarEmpleadoPorIdSucursal(idSucursal:number): Observable<ApiResponse<EmpleadoResponse[]>> {
    return this.http.get<ApiResponse<EmpleadoResponse[]>>(`${this.api}/listarTransacciones/${idSucursal}`);
  }

}
