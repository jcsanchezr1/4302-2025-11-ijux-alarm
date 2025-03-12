import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recuperar-contrasena-nueva',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './recuperar-contrasena-nueva.component.html',
  styleUrl: './recuperar-contrasena-nueva.component.css'
})
export class RecuperarContrasenaNuevaComponent {
  recuperarForm: FormGroup;
  showSuccessModal = false;  

  constructor(private fb: FormBuilder, private router: Router) {
    this.recuperarForm = this.fb.group({
      nuevaContrasena: ['', [Validators.required, Validators.minLength(8)]],
      confirmarContrasena: ['', [Validators.required]]
    }, { validators: this.contrasenasCoinciden });
  }

  get confirmarContrasena() {
    return this.recuperarForm.get('confirmarContrasena');
  }

  enviarSolicitud() {
    this.recuperarForm.updateValueAndValidity();
    if (this.recuperarForm.invalid) {
      this.recuperarForm.markAllAsTouched();
      return;
    }
    this.showSuccessModal = true;    
  }

  contrasenasCoinciden(control: AbstractControl) {
    const nuevaContrasena = control.get('nuevaContrasena');
    const confirmarContrasena = control.get('confirmarContrasena');
  
    if (!nuevaContrasena || !confirmarContrasena) {
      return null;
    }
  
    if (nuevaContrasena.value !== confirmarContrasena.value) {
      confirmarContrasena.setErrors({ noCoincide: true });
      return { noCoincide: true };
    } else {
      if (confirmarContrasena.hasError('noCoincide')) {
        confirmarContrasena.setErrors(null);
      }
      return null;
    }
  }

  aceptarModal() {
    this.showSuccessModal = false;
    this.router.navigate(['/login']);
  }
}
