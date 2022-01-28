import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import { CommonListComponent } from './common-list.component';



@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})

export class AlumnosComponent extends
     CommonListComponent<Alumno,AlumnoService>  {
   
  constructor(service: AlumnoService) {
    super(service);
    this.titulo = 'Listado de alumnos';
    this.nombreModelo = Alumno.name;
  }

 

}
