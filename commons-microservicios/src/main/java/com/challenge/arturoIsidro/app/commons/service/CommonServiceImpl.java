package com.challenge.arturoIsidro.app.commons.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;



//Se utiliza genericos
//La clase implements Se le indica que va implementar una clase generica 
// y se le pasa el tipo tambien la Interfaz en este caso CrudRepository
public class CommonServiceImpl<E,R extends PagingAndSortingRepository<E,Long>> implements CommonService<E> {

	@Autowired                              
	protected R repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
	
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E save(E entity) {
		
		return repository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAll(pageable);
	}

}
