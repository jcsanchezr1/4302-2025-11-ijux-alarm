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

    this.registerForm.get('confirmarContrasena')?.valueChanges.subscribe(() => {
      this.validarCoincidencia();
    });

    this.registerForm.get('contrasena')?.valueChanges.subscribe(() => {
      this.validarCoincidencia();
    });
  }

  registrarse() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      this.validarCoincidencia();
      return;
    }

    this.showSuccessModal = true;
    console.log('Usuario registrado:', this.registerForm.value);
  }

  validarCoincidencia() {
    const contrasena = this.registerForm.get('contrasena')?.value;
    const confirmarContrasena = this.registerForm.get('confirmarContrasena')?.value;

    if (confirmarContrasena && contrasena !== confirmarContrasena) {
      this.registerForm.get('confirmarContrasena')?.setErrors({ noCoincide: true });
    } else if (confirmarContrasena === '') {
      this.registerForm.get('confirmarContrasena')?.setErrors({ required: true });
    } else {
      this.registerForm.get('confirmarContrasena')?.setErrors(null);
    }
  }

  aceptarModal() {
    this.showSuccessModal = false;
    this.router.navigate(['/login']);
  }
}
