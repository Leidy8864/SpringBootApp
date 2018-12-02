package com.proyecto.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.proyecto.springboot.app.models.entity.Alumno;

public interface IAlumnoDao extends PagingAndSortingRepository<Alumno, Long> {

	@Query("select c from Alumno c left join fetch c.matriculas f where c.id=?1")
	public Alumno fetchByIdWithMatriculas(Long id);
}
