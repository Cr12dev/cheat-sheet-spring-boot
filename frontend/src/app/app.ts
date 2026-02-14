import { Component, signal, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DataService } from './servicios/data';
import { User } from './servicios/data';

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

  // Signals para manejar el estado de la lista de usuarios
  users = signal<User[]>([]);

  // Objeto para el formulario (sin ID, ya que lo genera SQL)
  newUser = {
    nombre: '',
    email: '',
  };

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    // Especificamos que recibimos un array de User
    this.dataService.obtenerDatos().subscribe({
      next: (data: User[]) => {
        this.users.set(data);
        console.log('Usuarios cargados desde MySQL:', this.users());
      },
      error: (error) => console.error('Error al cargar usuarios:', error),
    });
  }

  addUser(): void {
    if (this.newUser.nombre.trim() && this.newUser.email.trim()) {
      // Enviamos el objeto al servicio
      this.dataService.saveUser(this.newUser).subscribe({
        next: (usuarioCreado: User) => {
          // Actualizamos la lista con el usuario que devuelve el backend (ya trae ID)
          this.users.update((prev) => [...prev, usuarioCreado]);

          // Limpiamos el formulario
          this.newUser = { nombre: '', email: '' };
          console.log('Usuario guardado con éxito');
        },
        error: (error) => {
          console.error('Error al añadir usuario:', error);
          alert('Hubo un error al guardar en la base de datos');
        },
      });
    }
  }

  deleteUser(id: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
      // 1. Llamada al backend para borrar de la DB real
      // Asegúrate de implementar deleteUser(id) en tu DataService
      this.dataService.deleteUser(id).subscribe({
        next: () => {
          // 2. Si el borrado en SQL fue bien, lo quitamos de la UI
          this.users.update((prev) => prev.filter((u) => u.id !== id));
          console.log('Usuario eliminado de MySQL');
        },
        error: (error) => console.error('Error al eliminar:', error),
      });
    }
  }
}
