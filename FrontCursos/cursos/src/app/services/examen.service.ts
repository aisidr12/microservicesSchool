import { Injectable } from '@angular/core';
import { Curso } from '../models/curso';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class ExamenService extends CommonService<Curso> {

  protected baseEndpoint = 'http://localhost:8090/api/examenes';

}
