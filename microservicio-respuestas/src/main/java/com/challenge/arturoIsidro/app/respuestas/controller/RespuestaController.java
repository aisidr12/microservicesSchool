package com.challenge.arturoIsidro.app.respuestas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.arturoIsidro.app.respuestas.models.entity.Respuesta;
import com.challenge.arturoIsidro.app.respuestas.services.RespuestaService;

@RestController
public class RespuestaController {

	@Autowired
	private RespuestaService service;

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas) {
		Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
	}

	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuestaPorAlumnoPorExamen(@PathVariable Long alumnoId,
			@PathVariable Long examenId) {
		Iterable<Respuesta> respuestas = service.findRespuestaByAlumno(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}
	
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public ResponseEntity<?>obtenerExamenesIdsConRespuestaAlumno(@PathVariable Long alumnoId) {
		Iterable<Long>examenesIds =  service.findExamenedIdsConRespuestasByAlumno(alumnoId);
		return ResponseEntity.ok(examenesIds);		
	}
}
