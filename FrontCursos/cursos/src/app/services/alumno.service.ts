import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService extends CommonService<Alumno>  {

  protected baseEndpoint = 'http://localhost:8090/api/alumnos';
 
  constructor(http: HttpClient) {
    super(http);
   }

   public crearConFoto(alumno:Alumno, archivo:File):Observable<Alumno>{
    // Envio de formaData en una peticion Post 
    const formData = new FormData();
    formData.append('archivo',archivo);
    formData.append('nombre',alumno.nombre);
    formData.append('apellido',alumno.apellido);
    formData.append('email',alumno.email);
    return this.http.post<Alumno>(this.baseEndpoint + '/crear-con-foto',formData);
   }

   public EditarConFoto(alumno:Alumno, archivo:File):Observable<Alumno>{
    // Envio de formaData en una peticion Post 
    const formData = new FormData();
    formData.append('archivo',archivo);
    formData.append('nombre',alumno.nombre);
    formData.append('apellido',alumno.apellido);
    formData.append('email',alumno.email);
    return this.http.put<Alumno>(`${this.baseEndpoint}/editar-foto/${alumno.id}`,formData);
   }
}
