import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }

  guardarObjetoEnLocalStorage(key:string, data:any): void {
    localStorage.setItem(key, JSON.stringify(data));
  }

  recuperarObjetoDelLocalStorage(key:string): any {
    const usuario = localStorage.getItem(key)!
    return JSON.parse(usuario);
  }

  recuperarIdLocalStorage(key:string): number {
    const usuario = localStorage.getItem(key)!
    const objeto = JSON.parse(usuario);
    return objeto.id;
  }

  eliminarObjetoDeLocalStorage(key: string){
    localStorage.removeItem(key);
  }

}
