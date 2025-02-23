import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { ApiResponse } from '../interfaces/ApiResponse';
import { SucursalEntity } from '../interfaces/empleado/SucursalEntity';

@Injectable({
  providedIn: 'root'
})
export class SucursalService {

  private api:string = `${environment.url}/sucursal`;

  constructor( private http: HttpClient ) { }

  listadoSucursales(): Observable<ApiResponse<SucursalEntity[]>> {
    return this.http.get<ApiResponse<SucursalEntity[]>>(`${this.api}/listar`)
  }

}
