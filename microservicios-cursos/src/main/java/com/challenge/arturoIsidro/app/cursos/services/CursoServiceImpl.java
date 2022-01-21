package com.challenge.arturoIsidro.app.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.arturoIsidro.app.commons.service.CommonServiceImpl;
import com.challenge.arturoIsidro.app.cursos.clients.RespuestaFeignClient;
import com.challenge.arturoIsidro.app.cursos.models.entity.Curso;
import com.challenge.arturoIsidro.app.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso,CursoRepository> implements CursoService{

	@Autowired
	RespuestaFeignClient client;
	
	@Override
	@Transactional
	public Curso findCursoByAlumno(Long id) {
		
		return repository.findCursoByAlumno(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestaAlumno(alumnoId);
	}

}
