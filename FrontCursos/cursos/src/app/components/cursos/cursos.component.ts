import { Component, OnInit } from '@angular/core';
import { CommonListComponent } from '../common-list.component';
import { Curso } from '../../models/curso';
import { CommonService } from 'src/app/services/common.service';
import { CursoService } from 'src/app/services/curso.service';
import { HttpClient } from '@angular/common/http';
import { BASE_ENDPOINT } from 'src/app/config/app';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.css']
})
export class CursosComponent extends CommonListComponent<Curso,CursoService>  {

  
  baseEndpoint =  BASE_ENDPOINT + '/cursos';
   
  constructor(service: CursoService) {
    super(service);
    this.titulo = 'Listado de cursos';
    this.nombreModelo = Curso.name;
  }
 

}
