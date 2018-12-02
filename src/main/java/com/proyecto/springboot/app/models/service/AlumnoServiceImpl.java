package com.proyecto.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.models.dao.IAlumnoDao;
import com.proyecto.springboot.app.models.dao.ICursoDao;
import com.proyecto.springboot.app.models.dao.IMatriculaDao;
import com.proyecto.springboot.app.models.entity.Alumno;
import com.proyecto.springboot.app.models.entity.Curso;
import com.proyecto.springboot.app.models.entity.Matricula;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoDao alumnoDao;
	
	@Autowired
	private ICursoDao cursoDao;
	
	@Autowired
	private IMatriculaDao matriculaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findAll() {
		return (List<Alumno>) alumnoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Alumno alumno) {
		alumnoDao.save(alumno);
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findOne(Long id) {
		return alumnoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Alumno fetchByIdWithMatriculas(Long id) {
		return alumnoDao.fetchByIdWithMatriculas(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		alumnoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Alumno> findAll(Pageable pageable) {
		return alumnoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findByNombre(String term) {
		return cursoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveMatricula(Matricula matricula) {
		matriculaDao.save(matricula);
	}

	@Override
	@Transactional(readOnly=true)
	public Curso findCursoById(Long id) {
		return cursoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Matricula findMatriculaById(Long id) {
		return matriculaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteMatricula(Long id) {
		matriculaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Matricula fetchMatriculaByIdWithAlumnoWhithItemMatriculaWithCurso(Long id) {
		return matriculaDao.fetchByIdWithAlumnoWhithItemMatriculaWithCurso(id);
	}
	
	
}
