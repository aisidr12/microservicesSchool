import { Component, OnInit } from '@angular/core';
import { Examen } from 'src/app/models/examen';
import { ExamenService } from 'src/app/services/examen.service';
import { CommonFormComponent } from '../common-form.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Asignatura } from '../../models/asignatura';
import { Pregunta } from 'src/app/models/pregunta';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-examen-form',
  templateUrl: './examen-form.component.html',
  styleUrls: ['./examen-form.component.css']
})
export class ExamenFormComponent extends CommonFormComponent<Examen,ExamenService> implements OnInit {

  asignaturaPadre :Asignatura[] = [];
  asignaturaHija :Asignatura[] = [];
  errorPregunta : string;

  constructor(service:ExamenService,router: Router,route: ActivatedRoute) {
    super(service,router,route);
    this.titulo = 'Crear Examen';
    this.model = new Examen();
    this.redirect = '\examenes';
    this.nombreModel = Examen.name;
  }

  ngOnInit(): void {    
      this.route.paramMap.subscribe(params =>{
        const id :number  = +params.get('id');
        if(id){
          console.log(id);
          this.service.ver(id).subscribe(m =>{
            this.model = m;
            this.titulo = 'Editar '+ this.nombreModel;

            this.service.findAllAsignatura().subscribe(asignatura =>{
              this.asignaturaHija = asignatura.filter(a=>a.padre && a.padre.id === this.model.asignaturaPadre.id)
            })

          });
        }
      });    
      this.service.findAllAsignatura().subscribe(asignaturas => {
        this.asignaturaPadre = asignaturas.filter(a => !a.padre);
      });
  }

  public crear(): void {
    if(this.model.preguntas.length === 0){
     // Swal.fire('Error Preguntas','Examen debe tener preguntas','error');
     this.errorPregunta = 'Examen debe tener preguntas';
      return;
    }
    this.eliminarPreguntasVacias();
      super.crear();
  }

  public editar(): void {
    if(this.model.preguntas.length === 0){
      Swal.fire('Error Preguntas','Examen debe tener preguntas','error');
      return;
    }
    this.eliminarPreguntasVacias();
      super.editar(); 
  }

  cargarHijos():void{
    this.asignaturaHija = this.asignaturaPadre?this.model.asignaturaPadre.hijos:[];
  }
  compararAsignatura(a1:Asignatura,a2:Asignatura):boolean{
    if(a1 === undefined &&  a2 === undefined){
      return true;
    }
    if(a1 === null || a2 === null || a1 === undefined || a2 === undefined){
      return false;
    }
    if(a1.id === a2.id){
      return true;
    }
  }

  agregarPregunta(): void{
    //Agregamos una nueva instancia de pregunta
    this.model.preguntas.push(new Pregunta());
  }
  asignarTexto(pregunta:Pregunta,event:any):void {
    pregunta.texto = event.target.value as string;
    console.log(this.model);
  }
  eliminarPregunta(pregunta: Pregunta):void{
    this.model.preguntas = this.model.preguntas.filter(p => pregunta.texto !== p.texto);
  }
  eliminarPreguntasVacias():void{
    this.model.preguntas = this.model.preguntas.filter(p => p.texto != null && p.texto.length >0);
  }
}
