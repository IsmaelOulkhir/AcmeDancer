/*
 * ProfileController.java
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Academia;
import domain.Alumno;
import security.RegisterService;
import services.AcademiaService;
import services.AlumnoService;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	private AcademiaService		academiaService;
	@Autowired
	private AlumnoService		alumnoService;
	@Autowired
	private RegisterService service;

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1(@RequestParam(required = false) final boolean showError) {
		ModelAndView result;
		Alumno alumno;

		alumno = this.alumnoService.findByPrincipal();
		Assert.notNull(alumno);
		result = new ModelAndView("profile/action-1");
		result.addObject("alumno", alumno);
		result.addObject("userAccount", alumno.getUserAccount());
		result.addObject("showError", showError);

		return result;
	}
	
	@RequestMapping(value = "/action-1", method = RequestMethod.POST, params = "save")
	public ModelAndView action1_save(@Valid final Alumno alumno, final BindingResult binding) {
		ModelAndView result;
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("profile/action-1");
			result.addObject("alumno", alumno);
			result.addObject("userAccount", alumno.getUserAccount());
			result.addObject("showError", true);
			// Imprimir errores en la consola
			for (ObjectError error : binding.getAllErrors()) {
				if (error instanceof FieldError) {
					FieldError fieldError = (FieldError) error;
					System.out.println("Field error in object '" + fieldError.getObjectName() + "' on field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage());
				} else {
					System.out.println("Error in object '" + error.getObjectName() + "': " + error.getDefaultMessage());
				}
			}
		} else {
			try {
				this.service.save(alumno);
				result = new ModelAndView("redirect:/");
			} catch (Exception e) {
				System.out.println("tiene error de guardado");
				System.out.println(e.getMessage());
				result = new ModelAndView("profile/action-1");
				result.addObject("alumno", alumno);
				result.addObject("userAccount", alumno.getUserAccount());
				result.addObject("showError", true);
			}
		}

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping(value = "/action-2", method = RequestMethod.GET)
	public ModelAndView action2(@RequestParam(required = false) final boolean showError) {
		ModelAndView result;
		Academia academia;

		academia = this.academiaService.findByPrincipal();
		Assert.notNull(academia);
		result = new ModelAndView("profile/action-2");
		result.addObject("academia", academia);
		result.addObject("userAccount", academia.getUserAccount());
		result.addObject("showError", showError);

		return result;
	}
	
	@RequestMapping(value = "/action-2", method = RequestMethod.POST, params = "save")
	public ModelAndView action2_save(@Valid final Academia academia, final BindingResult binding) {
		ModelAndView result;
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("profile/action-1");
			result.addObject("academia", academia);
			result.addObject("userAccount", academia.getUserAccount());
			result.addObject("showError", true);
			// Imprimir errores en la consola
			for (ObjectError error : binding.getAllErrors()) {
				if (error instanceof FieldError) {
					FieldError fieldError = (FieldError) error;
					System.out.println("Field error in object '" + fieldError.getObjectName() + "' on field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage());
				} else {
					System.out.println("Error in object '" + error.getObjectName() + "': " + error.getDefaultMessage());
				}
			}
		} else {
			try {
				this.service.save(academia);
				result = new ModelAndView("redirect:/");
			} catch (Exception e) {
				System.out.println("tiene error de guardado");
				System.out.println(e.getMessage());
				result = new ModelAndView("profile/action-1");
				result.addObject("academia", academia);
				result.addObject("userAccount", academia.getUserAccount());
				result.addObject("showError", true);
			}
		}

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-3")
	public ModelAndView action3() {
		throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
	}

}
