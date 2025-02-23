import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnalistaComponent } from './analista.component';
import { CreditoComponent } from './credito/credito.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { RouterModule } from '@angular/router';
import { AnalistaRoutingModule } from './analista-routing.module';



@NgModule({
  declarations: [
    AnalistaComponent,
    CreditoComponent,
    ConsultaComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    AnalistaRoutingModule
  ]
})
export class AnalistaModule { }
