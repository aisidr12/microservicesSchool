import { Component, OnInit } from '@angular/core';
import { Alumno } from '../../models/alumno';
import { HttpClient } from '@angular/common/http';
import { AlumnoService } from '../../services/alumno.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'
import { CommonFormComponent } from './common-form.component';


@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent extends
  CommonFormComponent<Alumno, AlumnoService> implements OnInit {

private fotoSeleccionada:File;

  constructor(service: AlumnoService,
    router: Router,
    route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = 'Crear Alumnos';
    this.model = new Alumno();
    this.redirect = '\alumnos';
    this.nombreModel = Alumno.name;
  }

public seleccionarFoto(event):void{
  //Se guarda la primera imagen del fichero - se podrian guardar mas fotos
    this.fotoSeleccionada = event.targe.files[0];
    console.log(this.fotoSeleccionada);
}


}
