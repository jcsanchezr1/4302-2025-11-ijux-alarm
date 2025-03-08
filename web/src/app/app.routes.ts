import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';



export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' }
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes), ReactiveFormsModule], 
    exports: [RouterModule]
  })

  export class AppRoutingModule {}