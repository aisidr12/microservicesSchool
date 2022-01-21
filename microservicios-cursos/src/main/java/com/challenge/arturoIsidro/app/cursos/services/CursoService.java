package com.challenge.arturoIsidro.app.cursos.services;

import com.challenge.arturoIsidro.app.commons.service.CommonService;
import com.challenge.arturoIsidro.app.cursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {

	public Curso findCursoByAlumno(Long id);
	
	public Iterable<Long>obtenerExamenesIdsConRespuestaAlumno(Long alumnoId);

}
