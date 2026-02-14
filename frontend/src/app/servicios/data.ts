import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private http = inject(HttpClient);

  obtenerDatos() {
    return this.http.get('http://localhost:8643/api/usuarios');
  }
}
