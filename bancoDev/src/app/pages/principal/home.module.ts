import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NosotrosComponent } from './nosotros/nosotros.component';
import { HomeComponent } from './home/home.component';
import { HomeRoutingModule } from './home-routing.module';
import { PrincipalComponent } from './principal.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    NosotrosComponent,
    PrincipalComponent,
    HomeComponent,
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    RouterModule,
  ],
  exports: [
  ]
})
export class HomeModule { }
