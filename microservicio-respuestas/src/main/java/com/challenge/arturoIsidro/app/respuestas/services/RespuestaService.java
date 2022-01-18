package com.challenge.arturoIsidro.app.respuestas.services;

import com.challenge.arturoIsidro.app.respuestas.models.entity.Respuesta;

public interface RespuestaService {

	public Iterable<Respuesta> saveAll(Iterable<Respuesta>respuestas);
	
	public Iterable<Respuesta>findRespuestaByAlumno(Long alumnoId,Long examenId);

	public	Iterable<Long> findExamenedIdsConRespuestasByAlumno(Long alumnoId);

}
