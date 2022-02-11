import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Curso } from 'src/app/models/curso';
import { Examen } from 'src/app/models/examen';
import { CursoService } from '../../services/curso.service';
import { ExamenService } from '../../services/examen.service';
import {map,flatMap, mergeMap} from 'rxjs/operators';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-asignar-examenes',
  templateUrl: './asignar-examenes.component.html',
  styleUrls: ['./asignar-examenes.component.css']
})
export class AsignarExamenesComponent implements OnInit {

  curso: Curso;
  autocompleteControl = new FormControl();
  examenesFiltrados: Examen [] = [];
  examenesAsignar: Examen [] = [];
  mostrarColumnas = ['nombre','asignatura','eliminar'];
  examenes : Examen[]= [];
  
  constructor(private route:ActivatedRoute,private router: Router,
    private cursoService:CursoService, private examenService:ExamenService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params =>{
      const id : number = +params.get('id');
      this.cursoService.ver(id).subscribe(c => {
        this.curso = c;
        this.examenes =  this.curso.examenes;
      });
    });
    //Esto es reactivo, por eso cuando se pilla el primer elemento
    // puede pillarlo como string, pero los demas los pilla como objeto por eso el pipe
    // luego con merge map se combinan dos flujos diferentes para que se pueda llamar a otro
    // servicio y luego se suscribe a ese servicio
    this.autocompleteControl.valueChanges.pipe(
      map(valor => typeof valor === 'string'? valor:valor.nombre),
      mergeMap(valor =>valor? this.examenService.filtrarPorNombre(valor):[])
      ).subscribe(examenes => this.examenesFiltrados = examenes);
  }

  /*
  Este metodo va con la etiqueta [displayWith] 
  para que no se pinte en el input
  
  */
  mostrarNombre(examen?:Examen):string{
    return examen?examen.nombre:'';
  }

  seleccionarExamen(event: MatAutocompleteSelectedEvent):void{
    const examen = event.option.value  as Examen;
    if(!this.existe(examen.id)){

 
     this.examenesAsignar = this.examenesAsignar.concat(this.examenes);
    console.log(this.examenesAsignar);
    this.autocompleteControl.setValue('');
    event.option.deselect();
    event.option.focus();
   }else{
     Swal.fire(
       'Error:',
       `el examen  ${examen.nombre} ya esta asignado  al curso`,
       'error'
     );
   }
  }

  private existe(id:number):boolean{
    let existe = false;
    this.examenesAsignar.concat(this.curso.examenes)
      .forEach( e =>{
        if(id === e.id){
          existe=true;
        }
      });
     return existe;
  }


  //Episodio 190
  eliminardelAsignar(examen:Examen):void{

    // que se filtran los que son distintos a los examenes
    this.examenesAsignar = this.examenesAsignar.filter(e => examen.id !== e.id);
  }

  asignar():void{
    this.cursoService.asignarExamenes(this.curso,this.examenesAsignar)
    .subscribe(curso=>{
     this.examenes = this.examenes.concat(this.examenesAsignar); 
     this.examenesAsignar = [] ;
      
      Swal.fire(
        'Asignados:',
        `Examenes asignados con existo al curso  ${curso.nombre}`,
        'success'
      );
    });
  }
}
