import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrestamosComponent } from './prestamos/prestamos.component';
import { CuentaComponent } from './cuenta/cuenta.component';
import { DetalleCuotaComponent } from './detalle-cuota/detalle-cuota.component';
import { DetalleTransaccionComponent } from './detalle-transaccion/detalle-transaccion.component';
import { RouterModule } from '@angular/router';
import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioComponent } from './usuario.component';
import { ListaTransaccionComponent } from './lista-transaccion/lista-transaccion.component';
import { ListaCuotaComponent } from './lista-cuota/lista-cuota.component';



@NgModule({
  declarations: [
    UsuarioComponent,
    PrestamosComponent,
    CuentaComponent,
    DetalleCuotaComponent,
    DetalleTransaccionComponent,
    ListaTransaccionComponent,
    ListaCuotaComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    UsuarioRoutingModule,
  ]
})
export class UsuarioModule { }
