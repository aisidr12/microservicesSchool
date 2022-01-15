package com.challenge.arturoIsidro.app.examenes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.arturoIsidro.app.commons.service.CommonServiceImpl;
import com.challenge.arturoIsidro.app.examenes.models.repository.AsignaturaRepository;
import com.challenge.arturoIsidro.app.examenes.models.repository.ExamenRepository;
import com.challenge.arturoIsidro.commons.examenes.models.entity.Asignatura;
import com.challenge.arturoIsidro.commons.examenes.models.entity.Examen;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen,ExamenRepository>implements ExamenService {
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Asignatura> findAllAsignaturas() {
		return asignaturaRepository.findAll();
	}

	

}
