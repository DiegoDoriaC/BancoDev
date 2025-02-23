import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path:'', redirectTo:'bancoDev', pathMatch:'full' },
  { path:'bancoDev', loadChildren: () => import('./pages/principal/home.module').then(m => m.HomeModule) },
  { path:'login', loadChildren: () => import('./pages/login/login.module').then(m => m.LoginModule) },
  { path:'usuario', loadChildren: () => import('./pages/usuario/usuario.module').then(m => m.UsuarioModule) },
  { path:'analista', loadChildren: () => import('./pages/analista/analista.module').then(m => m.AnalistaModule) },
  { path:'cajero', loadChildren: () => import('./pages/cajero/cajero.module').then(m => m.CajeroModule) },
  { path:'**', redirectTo:'bancoDev' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
