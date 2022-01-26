import { Component, OnInit } from '@angular/core';
import { Alumno } from '../../models/alumno';
import { HttpClient } from '@angular/common/http';
import { AlumnoService } from '../../services/alumno.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent implements OnInit {

  titulo = "Crear Alumnos";
  error : any;

  alumno:Alumno = new Alumno();
  constructor(private service:AlumnoService,
              private router:Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {   
    this.route.paramMap.subscribe(params =>{
      const id :number  = +params.get('id');
      if(id){
        console.log(id);
        this.service.ver(id).subscribe(alumno =>{
          this.alumno = alumno;
        });
      }
    })
  }

  public crear():void{
    this.service.crear(this.alumno).subscribe(alumno =>{
      console.log("Intento de creacion " +alumno);
      Swal.fire('Nuevo',`Alumno  ${alumno.nombre} creado con exito`,'success');
      this.router.navigate(['/alumnos'])
    },err =>{
      if(err.status === 400){
        this.error = err.error;
        console.log(" Este error "+ this.error);
      }
    });
  }

  public editar():void{
    this.service.editar(this.alumno).subscribe(alumno =>{
      console.log("Intento de modificar " +alumno);
      Swal.fire('Modificado',`Alumno  ${alumno.nombre} actualizado con exito`,'info');
      this.router.navigate(['/alumnos'])
    },err =>{
      if(err.status === 400){
        this.error = err.error;
        console.log(" Este error "+ this.error);
      }
    });
  }

 
}
