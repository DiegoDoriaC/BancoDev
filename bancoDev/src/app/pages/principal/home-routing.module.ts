import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { NosotrosComponent } from "./nosotros/nosotros.component";
import { PrincipalComponent } from "./principal.component";

const route: Routes = [
  {path:'', component:PrincipalComponent, children:[
    {path:'', component:HomeComponent},
    {path:'nosotros', component: NosotrosComponent}
  ]},
]

@NgModule({
  imports:[RouterModule.forChild(route)],
  exports:[RouterModule]
})

export class HomeRoutingModule {}
