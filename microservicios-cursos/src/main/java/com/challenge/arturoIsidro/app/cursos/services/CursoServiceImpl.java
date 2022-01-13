package com.challenge.arturoIsidro.app.cursos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.arturoIsidro.app.commons.service.CommonServiceImpl;
import com.challenge.arturoIsidro.app.cursos.models.entity.Curso;
import com.challenge.arturoIsidro.app.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso,CursoRepository> implements CursoService{

	@Override
	@Transactional
	public Curso findCursoByAlumno(Long id) {
		
		return repository.findCursoByAlumno(id);
	}

}
