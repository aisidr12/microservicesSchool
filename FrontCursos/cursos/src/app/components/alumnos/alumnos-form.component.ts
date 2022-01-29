import { Component, OnInit } from '@angular/core';
import { Alumno } from '../../models/alumno';
import { HttpClient } from '@angular/common/http';
import { AlumnoService } from '../../services/alumno.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'
import { CommonFormComponent } from '../common-form.component';


@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent extends
  CommonFormComponent<Alumno, AlumnoService> implements OnInit {

  private fotoSeleccionada: File;

  constructor(service: AlumnoService,
    router: Router,
    route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = 'Crear Alumnos';
    this.model = new Alumno();
    this.redirect = '\alumnos';
    this.nombreModel = Alumno.name;
  }

  public seleccionarFoto(event): void {
    //Se guarda la primera imagen del fichero - se podrian guardar mas fotos
    this.fotoSeleccionada = event.target.files[0];
    console.log(this.fotoSeleccionada);
    if(this.fotoSeleccionada.type.indexOf('image')<0){
      this.fotoSeleccionada = null;
      Swal.fire('Error al seleccionar fichero : ', "El archivo tiene que ser de tipo imagen",'error');
    }
  }

  public crear():void{
    if(!this.fotoSeleccionada){
      super.crear();
    }else{
      this.service.crearConFoto(this.model,this.fotoSeleccionada).subscribe(alumno =>{
        console.log("Intento de creacion " + alumno);
        Swal.fire('Nuevo',`${this.nombreModel}  ${alumno.nombre} creado con exito`,'success');
        this.router.navigate([this.redirect])
      },err =>{
        if(err.status === 400){
          this.error = err.error;
          console.log(" Este error "+ this.error);
        }
      });
    }
  }

  public editar():void{
    if(!this.fotoSeleccionada){
      super.editar();
    }else{
      this.service.EditarConFoto(this.model,this.fotoSeleccionada).subscribe(alumno =>{
        console.log("Intento de creacion " + alumno);
        Swal.fire('Modificado: ',`${this.nombreModel}  ${alumno.nombre} actualizado con exito`,'success');
        this.router.navigate([this.redirect])
      },err =>{
        if(err.status === 400){
          this.error = err.error;
          console.log(" Este error "+ this.error);
        }
      });
    }
  }
  

}
