import { Component, signal, inject, OnInit } from '@angular/core'; // 1. Corregido el import
import { RouterOutlet } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DataService } from './servicios/data';

// 2. Ajustada la interfaz para que coincida con tu clase Java (nombre vs name)
interface User {
  id: number;
  nombre: string;
  email: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  protected readonly title = signal('primeraweb');
  private dataService = inject(DataService);

  // Usamos signals para consistencia con Angular moderno
  users = signal<User[]>([]);

  // Objeto para el formulario
  newUser = {
    nombre: '',
    email: '',
  };

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    this.dataService.obtenerDatos().subscribe((data: any) => {
      this.users.set(data);
      console.log('Usuarios cargados:', this.users());
    });
  }

  addUser(): void {
    if (this.newUser.nombre && this.newUser.email) {
      console.log('Añadiendo usuario:', this.newUser);
      // Aquí iría la llamada al servicio para añadir
      // Por ahora simulamos:
      const nuevo: User = {
        id: Date.now(),
        nombre: this.newUser.nombre,
        email: this.newUser.email,
      };
      this.users.update((prev: User[]) => [...prev, nuevo]);
      this.newUser = { nombre: '', email: '' };
    }
  }

  deleteUser(id: number): void {
    console.log('Eliminando usuario con id:', id);
    this.users.update((prev: User[]) => prev.filter((u: User) => u.id !== id));
  }
}
