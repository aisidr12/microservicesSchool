import { Component, OnInit } from '@angular/core';
import { BASE_ENDPOINT } from 'src/app/config/app';
import { Examen } from 'src/app/models/examen';
import { ExamenService } from 'src/app/services/examen.service';
import { CommonService } from '../../services/common.service';
import { CommonListComponent } from '../common-list.component';

@Component({
  selector: 'app-examenes',
  templateUrl: './examenes.component.html',
  styleUrls: ['./examenes.component.css']
})
export class ExamenesComponent extends CommonListComponent<Examen,ExamenService>  {

  baseEndpoint =  BASE_ENDPOINT + '/examenes';

  constructor(service: ExamenService) {
    super(service);
    this.titulo = 'Listado de Examenes';
    this.nombreModelo = Examen.name;
  } 

}
