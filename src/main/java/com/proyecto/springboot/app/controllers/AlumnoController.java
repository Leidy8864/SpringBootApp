package com.proyecto.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.springboot.app.models.entity.Alumno;
import com.proyecto.springboot.app.models.service.IAlumnoService;
import com.proyecto.springboot.app.models.service.IUploadFileService;
import com.proyecto.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {

	@Autowired
	private IAlumnoService alumnoService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Alumno alumno = alumnoService.fetchByIdWithMatriculas(id);
		if (alumno == null) {
			flash.addFlashAttribute("error", "El alumno no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("alumno", alumno);
		model.put("titulo", "Detalle alumno: " + alumno.getNombre());
		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Alumno> alumnos = alumnoService.findAll(pageRequest);

		PageRender<Alumno> pageRender = new PageRender<Alumno>("/listar", alumnos);
		model.addAttribute("titulo", "Listado de alumnos");
		model.addAttribute("alumnos", alumnos);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Alumno alumno = new Alumno();
		model.put("alumno", alumno);
		model.put("titulo", "Crear Alumno");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Alumno alumno = null;

		if (id > 0) {
			alumno = alumnoService.findOne(id);
			if (alumno == null) {
				flash.addFlashAttribute("error", "El ID del alumno no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del alumno no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("alumno", alumno);
		model.put("titulo", "Editar Alumno");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Alumno alumno, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Alumno");
			return "form";
		}

		if (!foto.isEmpty()) {

			if (alumno.getId() != null && alumno.getId() > 0 && alumno.getFoto() != null
					&& alumno.getFoto().length() > 0) {

				uploadFileService.delete(alumno.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			alumno.setFoto(uniqueFilename);

		}

		String mensajeFlash = (alumno.getId() != null) ? "Alumno editado con éxito!" : "Alumno creado con éxito!";

		alumnoService.save(alumno);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Alumno alumno = alumnoService.findOne(id);

			alumnoService.delete(id);
			flash.addFlashAttribute("success", "Alumno eliminado con éxito!");

			if (uploadFileService.delete(alumno.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + alumno.getFoto() + " eliminada con exito!");
			}

		}
		return "redirect:/listar";
	}
}
