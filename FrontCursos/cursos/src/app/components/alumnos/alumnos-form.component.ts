import { Component, OnInit } from '@angular/core';
import { Alumno } from '../../models/alumno';
import { HttpClient } from '@angular/common/http';
import { AlumnoService } from '../../services/alumno.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent implements OnInit {

  titulo = "Crear Alumnos";
  alumno:Alumno = new Alumno();
  constructor(private service:AlumnoService,private router:Router) { 


  }


  ngOnInit(): void {
  }

  public crear():void{
    this.service.crear(this.alumno).subscribe(alumno =>{
      console.log("Intento de creacion " +alumno);
      alert(`Alumno  ${alumno.nombre} creado con exito`)
      this.router.navigate(['/alumnos'])
    });
  }
}
