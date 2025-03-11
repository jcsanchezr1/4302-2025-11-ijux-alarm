import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from '../login/login.component';
import { ListarAlarmasComponent } from '../listar-alarmas/listar-alarmas.component';
import { RegistrarUsuarioComponent } from '../registrar-usuario/registrar-usuario.component';



export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'registrar-usuario', component: RegistrarUsuarioComponent },
    { path: 'listar-alarmas', component: ListarAlarmasComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' }
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes), ReactiveFormsModule], 
    exports: [RouterModule]
  })

  export class AppRoutingModule {}