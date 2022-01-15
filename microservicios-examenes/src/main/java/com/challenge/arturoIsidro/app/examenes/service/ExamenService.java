package com.challenge.arturoIsidro.app.examenes.service;

import java.util.List;

import com.challenge.arturoIsidro.app.commons.service.CommonService;
import com.challenge.arturoIsidro.commons.examenes.models.entity.Asignatura;
import com.challenge.arturoIsidro.commons.examenes.models.entity.Examen;

public interface ExamenService extends CommonService<Examen> {

	public List<Examen>findByNombre(String term);
	
	public Iterable<Asignatura>findAllAsignaturas();
}
