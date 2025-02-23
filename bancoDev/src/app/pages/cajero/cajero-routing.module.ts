import { RouterModule, Routes } from "@angular/router";
import { CajeroComponent } from "./cajero.component";
import { NgModule } from "@angular/core";
import { BuscarClienteComponent } from "./buscar-cliente/buscar-cliente.component";
import { ListarPrestamoComponent } from "./listar-prestamo/listar-prestamo.component";
import { ListarCoutaComponent } from "./listar-couta/listar-couta.component";

const route : Routes = [
  { path:'', component:CajeroComponent, children:[
    { path:'', component:BuscarClienteComponent },
    { path:'listarPrestamo/:idCliente', component:ListarPrestamoComponent },
    { path:'listarCuota/:idCuota', component:ListarCoutaComponent }
  ]}
]

@NgModule({
  imports:[RouterModule.forChild(route)],
  exports:[RouterModule]
})

export class CajeroRoutingModule {}
