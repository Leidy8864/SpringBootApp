package com.proyecto.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyecto.springboot.app.models.entity.Curso;

public interface ICursoDao extends CrudRepository<Curso, Long>{

	@Query("select p from Curso p where p.nombre like %?1%")
	public List<Curso> findByNombre(String term);
	
	public List<Curso> findByNombreLikeIgnoreCase(String term);
}
