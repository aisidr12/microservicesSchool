package com.challenge.arturoIsidro.app.examenes.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.arturoIsidro.app.commons.controllers.CommonController;
import com.challenge.arturoIsidro.app.examenes.service.ExamenService;
import com.challenge.arturoIsidro.commons.examenes.models.entity.Examen;
import com.challenge.arturoIsidro.commons.examenes.models.entity.Pregunta;

@RestController
public class ExamenController extends CommonController<Examen,ExamenService>{

	@PutMapping("/{id}")
	public ResponseEntity<?>editar(@Valid @RequestBody Examen examen,BindingResult result,@PathVariable Long id){
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Examen>o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDb = o.get();		
		examenDb.setNombre(examen.getNombre());
		
		List<Pregunta>eliminadas = examenDb.getPreguntas()
		.stream()
		.filter(pdb -> !examen.getPreguntas().contains(pdb)).collect(Collectors.toList());
		
		eliminadas.forEach(examenDb::removePregunta);
		examenDb.setAsignaturaHija(examen.getAsignaturaHija());
		examenDb.setAsignaturaPadre(examen.getAsignaturaPadre());
		/*
		 * 
		 *
		//Dentro del examen hay examenes - listas
		List<Pregunta>eliminadas = new ArrayList<>();
		examenDb.getPreguntas().forEach(pdb ->{
			if(!examen.getPreguntas().contains(pdb)) {
				//Con esto creamos una lista de preguntas a eliminar,
				// es decir, hay preguntas de vista que no existen en bd
				eliminadas.add(pdb);
			}
		});
		
		eliminadas.forEach(p ->{
			//Eliminamos las preguntas que queriamos eliminar y que no estan incluidas en el json
			examenDb.removePregunta(p);
		});
		// ahora ponemos las nuevas preguntas.
		*/
		examenDb.setPreguntas(examen.getPreguntas());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?>filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?>listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
