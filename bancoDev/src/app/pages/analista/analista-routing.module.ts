import { RouterModule, Routes } from "@angular/router";
import { AnalistaComponent } from "./analista.component";
import { CreditoComponent } from "./credito/credito.component";
import { ConsultaComponent } from "./consulta/consulta.component";
import { NgModule } from "@angular/core";

const route : Routes = [
  { path:'', component:AnalistaComponent, children:[
    { path:'', component:CreditoComponent },
    { path:'consulta', component:ConsultaComponent }
  ]},
]

@NgModule({
  imports: [RouterModule.forChild(route)],
  exports: [RouterModule]
})

export class AnalistaRoutingModule {}
