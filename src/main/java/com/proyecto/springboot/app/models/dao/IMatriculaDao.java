package com.proyecto.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyecto.springboot.app.models.entity.Matricula;

public interface IMatriculaDao extends CrudRepository<Matricula, Long>{

	@Query("select f from Matricula f join fetch f.alumno c join fetch f.items l join fetch l.curso where f.id=?1")
	public Matricula fetchByIdWithAlumnoWhithItemMatriculaWithCurso(Long id);
}
