package com.challenge.arturoIsidro.app.examenes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.challenge.arturoIsidro.commons.examenes.models.entity.Examen;

public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long> {
	
	@Query("Select e from Examen e Where e.nombre like %?1%")
	public List<Examen>findByNombre(String term);
}
