import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// Asegúrate de importar tu interfaz User o definirla aquí
export interface User {
  id?: number;
  nombre: string;
  email: string;
}

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private http = inject(HttpClient);
  // Centralizamos la URL para no cometer errores al escribirla
  private apiUrl = 'http://localhost:8643/api/usuarios';

  obtenerDatos(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  saveUser(user: Partial<User>): Observable<User> {
    // CORRECCIÓN: Usamos la variable apiUrl, no la función obtenerDatos
    return this.http.post<User>(this.apiUrl, user);
  }

  // En data.ts
  deleteUser(id: number): Observable<string> {
    return this.http.delete(`http://localhost:8643/api/usuarios/${id}`, { responseType: 'text' });
  }
}
