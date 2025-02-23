import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login.component";
import { RegistrarseComponent } from "./registrarse/registrarse.component";
import { NgModule } from "@angular/core";

const route: Routes = [
  { path:'', component:LoginComponent, pathMatch:'full' },
  { path:'registrarse', component:RegistrarseComponent },
  { path:'**', redirectTo:'' }
]

@NgModule({
  imports:[RouterModule.forChild(route)],
  exports:[RouterModule]
})

export class LoginRoutingModule  {}
