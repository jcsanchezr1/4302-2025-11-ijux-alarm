import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common'; 
import { ReactiveFormsModule } from '@angular/forms'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css'],
  standalone: true, 
  imports: [CommonModule, ReactiveFormsModule] 
})
export class RegistrarUsuarioComponent {
  registerForm: FormGroup;
  showSuccessModal = false;

  constructor(private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group({
      nombres: ['', [Validators.required]],
      apellidos: ['', [Validators.required]],
      correo: ['', [Validators.required, Validators.email]],
      telefono: ['', [Validators.required, Validators.pattern('[0-9]{10}')]],
      contrasena: ['', [Validators.required]],
      confirmarContrasena: ['', [Validators.required]]
    });
  }

  registrarse() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    if (this.registerForm.value.contrasena !== this.registerForm.value.confirmarContrasena) {
      this.registerForm.get('confirmarContrasena')?.setErrors({ noCoincide: true });
      return;
    }
    
    this.showSuccessModal = true;
    console.log('Usuario registrado:', this.registerForm.value);
  }

  aceptarModal() {
    this.showSuccessModal = false;
    this.router.navigate(['/login']);
  }
}
