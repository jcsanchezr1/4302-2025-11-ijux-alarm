import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule], 
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log('Formulario válido', this.loginForm.value);
      this.router.navigate(['/listar-alarmas']); 
      console.log('Intentando redirigir a /listar-alarmas'); 
    } else {
      this.loginForm.markAllAsTouched();
      console.log('Formulario inválido');
    }
  }

  register() {
    console.log('Redirigiendo a registro...');
  }

  forgotPassword() {
    console.log('Redirigiendo a la página de recuperación de contraseña...');    
  }

}
