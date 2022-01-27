import { Injectable } from '@angular/core';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService extends CommonService<Alumno> {

  protected baseEndpoint = 'http://localhost:8090/api/alumnos';
 
}
