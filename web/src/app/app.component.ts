import { Component } from '@angular/core';
import { RouterModule, Router, NavigationEnd  } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, CommonModule, SidebarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'IJUX ALARM';
  mostrarMenu = false;

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        console.log('URL actual:', event.url);
        this.mostrarMenu = !['/login', '/registrar-usuario', '/recuperar-contrasena', '/recuperar-contrasena-nueva'].includes(event.url);
        console.log('¿Mostrar menú?', this.mostrarMenu);
      }
    });
  }
}
