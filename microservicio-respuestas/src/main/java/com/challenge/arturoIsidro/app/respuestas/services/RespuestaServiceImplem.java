package com.challenge.arturoIsidro.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.arturoIsidro.app.respuestas.models.entity.Respuesta;
import com.challenge.arturoIsidro.app.respuestas.models.repository.RespuestaRepository;

@Service
public class RespuestaServiceImplem implements RespuestaService{

	@Autowired
	private  RespuestaRepository repository;
	
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		
		return repository.saveAll(respuestas);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumno(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumno(alumnoId, examenId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenedIdsConRespuestasByAlumno(Long alumnoId) {
		return repository.findExamenedIdsConRespuestasByAlumno(alumnoId);
	}

}
