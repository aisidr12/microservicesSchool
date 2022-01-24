import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {

  private baseEndpoint = 'htpp://localhost:8090/api/alumnos';
  constructor(private http:HttpClient) { }
}
