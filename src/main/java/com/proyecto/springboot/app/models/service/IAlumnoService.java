package com.proyecto.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.springboot.app.models.entity.Alumno;
import com.proyecto.springboot.app.models.entity.Curso;
import com.proyecto.springboot.app.models.entity.Matricula;

public interface IAlumnoService {

	public List<Alumno> findAll();
	
	public Page<Alumno> findAll(Pageable pageable);

	public void save(Alumno alumno);
	
	public Alumno findOne(Long id);
	
	public Alumno fetchByIdWithMatriculas(Long id);
	
	public void delete(Long id);
	
	public List<Curso> findByNombre(String term);
	
	public void saveMatricula(Matricula matricula);
	
	public Curso findCursoById(Long id);
	
	public Matricula findMatriculaById(Long id);
	
	public void deleteMatricula(Long id);
	
	public Matricula fetchMatriculaByIdWithAlumnoWhithItemMatriculaWithCurso(Long id);

}
