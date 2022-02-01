import { Component, OnInit } from '@angular/core';
import { Examen } from 'src/app/models/examen';
import { ExamenService } from 'src/app/services/examen.service';
import { CommonFormComponent } from '../common-form.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Asignatura } from '../../models/asignatura';

@Component({
  selector: 'app-examen-form',
  templateUrl: './examen-form.component.html',
  styleUrls: ['./examen-form.component.css']
})
export class ExamenFormComponent extends CommonFormComponent<Examen,ExamenService> implements OnInit {

  asignaturaPadre :Asignatura[] = [];
  asignaturaHija :Asignatura[] = [];

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
          });
        }
      });    
      this.service.findAllAsignatura().subscribe(asignaturas => {
        this.asignaturaPadre = asignaturas.filter(a => !a.padre);
      });
  }
  cargarHijos():void{
    this.asignaturaHija = this.asignaturaPadre?this.model.asignaturaPadre.hijos:[];
  }
}
