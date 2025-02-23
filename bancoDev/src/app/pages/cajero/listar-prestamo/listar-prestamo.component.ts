import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PrestamoResponse } from 'src/app/interfaces/prestamos/PrestamoResponse';
import { PrestamoService } from 'src/app/services/prestamo.service';

@Component({
  selector: 'app-listar-prestamo',
  templateUrl: './listar-prestamo.component.html',
  styleUrls: ['./listar-prestamo.component.css']
})
export class ListarPrestamoComponent {

  public listadoPrestamos: PrestamoResponse[] = [];
  public mensaje: string = '';

  constructor(
    private route: ActivatedRoute,
    private _prestamo: PrestamoService,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('idCliente')!;
    this.obtenerListadoDePrestamos(Number(id));
  }

  obtenerListadoDePrestamos(id:number){
    this._prestamo.listarTodosLosPrestamos(id).subscribe(data => {
      if(data.status){
        this.listadoPrestamos = data.data;
      }
      this.mensaje = data.message;
    })
  }

}
