import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Respuesta } from '../models/respuesta';
import { BASE_ENDPOINT } from '../config/app';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RespuestaService {

 
  private baseEndpoint = BASE_ENDPOINT + '/respuestas';

  private cabeceras: HttpHeaders  = new HttpHeaders({'Content-type':'application/json'});
  constructor(private http: HttpClient) {
   }

   crear(respuestas:Respuesta[]):Observable<Respuesta[]>{
     return this.http.post<Respuesta[]>(this.baseEndpoint,respuestas,{
       headers: this.cabeceras
     });
   }

}
