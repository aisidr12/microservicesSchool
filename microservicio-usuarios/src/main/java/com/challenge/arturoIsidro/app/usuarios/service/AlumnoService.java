package com.challenge.arturoIsidro.app.usuarios.service;

import java.util.List;

import com.challenge.arturoIsidro.app.commons.service.CommonService;
import com.challenge.arturoIsidro.commons.alumnos.models.entity.Alumno;

public interface AlumnoService extends CommonService<Alumno> {

	
	public List<Alumno> findByNombreOrApellido(final String termino);
}
