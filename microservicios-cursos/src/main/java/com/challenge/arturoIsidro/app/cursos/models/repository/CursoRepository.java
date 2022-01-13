package com.challenge.arturoIsidro.app.cursos.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.arturoIsidro.app.cursos.models.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso,Long>{

	@Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
	public Curso findCursoByAlumno(Long id);
}
