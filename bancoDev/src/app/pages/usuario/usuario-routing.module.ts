import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { PrestamosComponent } from "./prestamos/prestamos.component";
import { CuentaComponent } from "./cuenta/cuenta.component";
import { DetalleTransaccionComponent } from "./detalle-transaccion/detalle-transaccion.component";
import { DetalleCuotaComponent } from "./detalle-cuota/detalle-cuota.component";
import { UsuarioComponent } from "./usuario.component";
import { ListaTransaccionComponent } from "./lista-transaccion/lista-transaccion.component";
import { ListaCuotaComponent } from "./lista-cuota/lista-cuota.component";

const route: Routes = [
  { path:'', component:UsuarioComponent, children:[
    { path:'', component:CuentaComponent },
    { path:'listaTransaccion/:numeroCuenta', component:ListaTransaccionComponent },
    { path:'detalleTransaccion/:numeroTransaccion', component:DetalleTransaccionComponent },
    { path:'prestamos', component:PrestamosComponent },
    { path:'listaCuota/:numeroPrestamo', component:ListaCuotaComponent },
    { path:'detalleCuota/:numeroCuota', component:DetalleCuotaComponent },
  ]},
];

@NgModule({
  imports: [RouterModule.forChild(route)],
  exports: [RouterModule]
})

export class UsuarioRoutingModule {}
