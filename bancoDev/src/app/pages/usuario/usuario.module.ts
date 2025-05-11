import { LOCALE_ID, NgModule } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import { PrestamosComponent } from './prestamos/prestamos.component';
import { CuentaComponent } from './cuenta/cuenta.component';
import { DetalleCuotaComponent } from './detalle-cuota/detalle-cuota.component';
import { DetalleTransaccionComponent } from './detalle-transaccion/detalle-transaccion.component';
import { RouterModule } from '@angular/router';
import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioComponent } from './usuario.component';
import { ListaTransaccionComponent } from './lista-transaccion/lista-transaccion.component';
import { ListaCuotaComponent } from './lista-cuota/lista-cuota.component';
import { RealizarTransaccionComponent } from './realizar-transaccion/realizar-transaccion.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SolicitarPrestamoComponent } from './solicitar-prestamo/solicitar-prestamo.component';
import localeEs from '@angular/common/locales/es';

registerLocaleData(localeEs, 'es');

@NgModule({
  declarations: [
    UsuarioComponent,
    PrestamosComponent,
    CuentaComponent,
    DetalleCuotaComponent,
    DetalleTransaccionComponent,
    ListaTransaccionComponent,
    ListaCuotaComponent,
    RealizarTransaccionComponent,
    SolicitarPrestamoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    UsuarioRoutingModule,
    ReactiveFormsModule,
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'es' } // Establece el locale globalmente
  ],
})
export class UsuarioModule { }
