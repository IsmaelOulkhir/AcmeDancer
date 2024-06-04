package controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Alumno;
import domain.Shout;
import domain.TarjetaDeCredito;
import services.AlumnoService;
import services.ShoutService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController extends AbstractController {

	@Autowired
	private ShoutService shoutService;
	@Autowired
	private AlumnoService alumnoService;


	// Constructors -----------------------------------------------------------

	public AlumnoController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1(@RequestParam(required = false) final boolean showError) {
		ModelAndView result;
		Alumno al = this.alumnoService.findByPrincipal();
		TarjetaDeCredito tar =  al.getTarjetaDeCredito();

		result = new ModelAndView("alumno/action-1");
		if (tar!=null) {
			result.addObject("tarjetaDeCredito", tar);
		}else {
			TarjetaDeCredito t = new TarjetaDeCredito();
			result.addObject("tarjetaDeCredito", t);
		}
		result.addObject("alumno", al);
		result.addObject("showError", showError);

		return result;
	}
	


	@RequestMapping(value = "/action-1", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final TarjetaDeCredito tarjeta, final BindingResult binding) {
		ModelAndView result;
		Alumno al = this.alumnoService.findByPrincipal();
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("alumno/action-1");
			result.addObject("tarjetaDeCredito", tarjeta);
			result.addObject("alumno", al);
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
				this.alumnoService.saveTarjeta(tarjeta);
				result = new ModelAndView("redirect:/");
			} catch (Exception e) {
				System.out.println("tiene error de guardado");
				System.out.println(e.getMessage());
				result = new ModelAndView("alumno/action-1");
				result.addObject("tarjetaDeCredito", tarjeta);
				result.addObject("alumno", al);
				result.addObject("showError", true);
			}
		}

		return result;
	}

	@RequestMapping(value = "/action-1", method = RequestMethod.POST, params = "edit")
	public ModelAndView edit(@Valid final TarjetaDeCredito tarjeta, final BindingResult binding) {
		ModelAndView result;
		Alumno al = this.alumnoService.findByPrincipal();
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("alumno/action-1");
			result.addObject("tarjetaDeCredito", tarjeta);
			result.addObject("alumno", al);
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
				this.alumnoService.saveTarjeta(tarjeta);
				result = new ModelAndView("redirect:/");
			} catch (Exception e) {
				System.out.println("tiene error de guardado");
				System.out.println(e.getMessage());
				result = new ModelAndView("alumno/action-1");
				result.addObject("tarjetaDeCredito", tarjeta);
				result.addObject("alumno", al);
				result.addObject("showError", true);
			}
		}

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping(value = "/action-2", method = RequestMethod.GET)
	public ModelAndView action2Get() {
		ModelAndView result;
		Shout shout;

		shout = this.shoutService.create();

		result = new ModelAndView("alumno/action-2");
		result.addObject("shout", shout);

		return result;
	}

	@RequestMapping(value = "/action-2", method = RequestMethod.POST)
	public ModelAndView action2Post(@Valid final Shout shout, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			this.shoutService.save(shout);
			result = new ModelAndView("redirect:action-1.do");
		} else {
			result = new ModelAndView("alumno/action-2");
			result.addObject("shout", shout);
		}

		return result;

	}
}
