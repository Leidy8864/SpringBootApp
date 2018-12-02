package com.proyecto.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.springboot.app.models.entity.Alumno;
import com.proyecto.springboot.app.models.entity.Curso;
import com.proyecto.springboot.app.models.entity.ItemMatricula;
import com.proyecto.springboot.app.models.entity.Matricula;
import com.proyecto.springboot.app.models.service.IAlumnoService;

@Controller
@RequestMapping("/matricula")
@SessionAttributes("matricula")
public class MatriculaController {

	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Matricula matricula = alumnoService.fetchMatriculaByIdWithAlumnoWhithItemMatriculaWithCurso(id); // alumnoService.findMatriculaById(id);

		if (matricula == null) {
			flash.addFlashAttribute("error", "La matricula no existe en la base de datos!");
			return "redirect:/listar";
		}

		model.addAttribute("matricula", matricula);
		model.addAttribute("titulo", "Matricula: ".concat(matricula.getAnio()));
		return "matricula/ver";
	}

	@GetMapping("/form/{alumnoId}")
	public String crear(@PathVariable(value = "alumnoId") Long alumnoId, Map<String, Object> model,
			RedirectAttributes flash) {

		Alumno alumno = alumnoService.findOne(alumnoId);

		if (alumno == null) {
			flash.addFlashAttribute("error", "El alumno no existe en la base de datos");
			return "redirect:/listar";
		}

		Matricula matricula = new Matricula();
		matricula.setAlumno(alumno);

		model.put("matricula", matricula);
		model.put("titulo", "Crear Matricula");

		return "matricula/form";
	}

	@GetMapping(value = "/cargar-cursos/{term}", produces = { "application/json" })
	public @ResponseBody List<Curso> cargarCursos(@PathVariable String term) {
		return alumnoService.findByNombre(term);
	}

	@PostMapping("/form")
	public String guardar(@Valid Matricula matricula, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Matricula");
			return "matricula/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Matricula");
			model.addAttribute("error", "Error: La matricula NO puede no tener líneas!");
			return "matricula/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Curso producto = alumnoService.findCursoById(itemId[i]);

			ItemMatricula linea = new ItemMatricula();
			linea.setCurso(producto);
			matricula.addItemMatricula(linea);

		}

		alumnoService.saveMatricula(matricula);
		status.setComplete();

		flash.addFlashAttribute("success", "Matricula creada con éxito!");

		return "redirect:/ver/" + matricula.getAlumno().getId();
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Matricula matricula = alumnoService.findMatriculaById(id);

		if (matricula != null) {
			alumnoService.deleteMatricula(id);
			flash.addFlashAttribute("success", "Matricula eliminada con éxito!");
			return "redirect:/ver/" + matricula.getAlumno().getId();
		}
		flash.addFlashAttribute("error", "La matricula no existe en la base de datos, no se pudo eliminar!");

		return "redirect:/listar";
	}

}
