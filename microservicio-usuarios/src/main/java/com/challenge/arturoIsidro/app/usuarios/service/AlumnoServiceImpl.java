package com.challenge.arturoIsidro.app.usuarios.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.challenge.arturoIsidro.app.commons.service.CommonServiceImpl;
import com.challenge.arturoIsidro.app.usuarios.models.repository.AlumnoRepository;
import com.challenge.arturoIsidro.commons.alumnos.models.entity.Alumno;



//Se utiliza genericos
//La clase implements Se le indica que va implementar una clase generica 
// y se le pasa el tipo tambien la Interfaz en este caso CrudRepository
@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno,AlumnoRepository> 
implements AlumnoService {

	@Override
	@Transactional
	public List<Alumno> findByNombreOrApellido(String termino) {
		// TODO Auto-generated method stub
		return repository.findByNombreOrApellido(termino);
	}

	

}
