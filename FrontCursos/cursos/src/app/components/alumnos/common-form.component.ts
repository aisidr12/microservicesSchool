import { Component, Directive, OnInit } from '@angular/core';
import { Alumno } from '../../models/alumno';
import { HttpClient } from '@angular/common/http';
import { AlumnoService } from '../../services/alumno.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'
import { CommonService } from '../../services/common.service';
import { Generic } from 'src/app/models/generic';

@Directive()
export abstract class CommonFormComponent<E extends Generic,S extends CommonService<E>> implements OnInit {

  titulo :string;
  model : E ;
  error : any;
  protected redirect : string;
  protected nombreModel : string;

  
  constructor(private service: S ,
              private router:Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {   
    this.route.paramMap.subscribe(params =>{
      const id :number  = +params.get('id');
      if(id){
        console.log(id);
        this.service.ver(id).subscribe(m =>{
          this.model = m;
        });
      }
    })
  }

  public crear():void{
    this.service.crear(this.model).subscribe(model =>{
      console.log("Intento de creacion " + model);
      Swal.fire('Nuevo',`${this.nombreModel}  ${model.nombre} creado con exito`,'success');
      this.router.navigate([this.redirect])
    },err =>{
      if(err.status === 400){
        this.error = err.error;
        console.log(" Este error "+ this.error);
      }
    });
  }

  public editar():void{
    this.service.editar(this.model).subscribe(model =>{
      console.log("Intento de modificar " +  model);
      Swal.fire('Modificado',`${this.nombreModel}  ${model.nombre} actualizado con exito`,'info');
      this.router.navigate([this.redirect])
    },err =>{
      if(err.status === 400){
        this.error = err.error;
        console.log(" Este error "+ this.error);
      }
    });
  }

 
}
