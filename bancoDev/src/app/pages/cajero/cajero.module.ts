import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CajeroComponent } from './cajero.component';
import { RouterModule } from '@angular/router';
import { CajeroRoutingModule } from './cajero-routing.module';
import { BuscarClienteComponent } from './buscar-cliente/buscar-cliente.component';
import { ListarPrestamoComponent } from './listar-prestamo/listar-prestamo.component';
import { ListarCoutaComponent } from './listar-couta/listar-couta.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    CajeroComponent,
    BuscarClienteComponent,
    ListarPrestamoComponent,
    ListarCoutaComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    CajeroRoutingModule,
    ReactiveFormsModule,
  ]
})
export class CajeroModule { }
