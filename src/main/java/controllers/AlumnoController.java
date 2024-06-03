
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Alumno;
import domain.TarjetaDeCredito;
import services.AlumnoService;
import services.TarjetaDeCreditoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController extends AbstractController {

	@Autowired
	private AlumnoService			alumnoService;

	@Autowired
	private TarjetaDeCreditoService	tarjetaDeCreditoService;


	// Constructors -----------------------------------------------------------

	public AlumnoController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Alumno> alumnos;

		alumnos = this.alumnoService.findAll();

		result = new ModelAndView("alumno/action-1");
		result.addObject("alumno", alumnos);

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping(value = "/action-2", method = RequestMethod.GET)
	public ModelAndView action2Get() {
		ModelAndView result;
		Alumno alumno;

		alumno = this.alumnoService.create();

		result = new ModelAndView("alumno/action-2");
		result.addObject("alumno", alumno);

		return result;
	}

	@RequestMapping(value = "/action-2", method = RequestMethod.POST)
	public ModelAndView action2Post(@Valid final Alumno alumno, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			this.alumnoService.save(alumno);
			result = new ModelAndView("redirect:action-1.do");
		} else {
			result = new ModelAndView("alumno/action-2");
			result.addObject("alumno", alumno);
		}

		return result;
	}

	// Action-3 ---------------------------------------------------------------

	@PreAuthorize("hasRole('ROLE_ALUMNO')")
	@RequestMapping(value = "/action-3", method = RequestMethod.GET)
	public ModelAndView action3Get(@RequestParam(required = false) final Integer tarjetaId) {
		ModelAndView result;
		Alumno alumno;
		TarjetaDeCredito tarjetaDeCredito;

		// Obteniendo el alumno autenticado
		alumno = this.alumnoService.findByPrincipal();

		if (tarjetaId != null) {
			tarjetaDeCredito = this.tarjetaDeCreditoService.findOne(tarjetaId);
			Assert.notNull(tarjetaDeCredito, "Tarjeta de crédito no encontrada");
		} else
			tarjetaDeCredito = this.tarjetaDeCreditoService.create();

		result = new ModelAndView("alumno/action-3");
		result.addObject("alumno", alumno);
		result.addObject("tarjetaDeCredito", tarjetaDeCredito);

		return result;
	}

	@PreAuthorize("hasRole('ROLE_ALUMNO')")
	@RequestMapping(value = "/action-3", method = RequestMethod.POST)
	public ModelAndView action3Post(@Valid final TarjetaDeCredito tarjetaDeCredito, final BindingResult binding) {
		ModelAndView result;
		final Alumno alumno = this.alumnoService.findByPrincipal();

		if (!binding.hasErrors()) {
			tarjetaDeCredito.setAlumno(alumno);
			this.tarjetaDeCreditoService.save(tarjetaDeCredito);
			result = new ModelAndView("redirect:action-1.do");
		} else {
			result = new ModelAndView("alumno/action-3");
			result.addObject("alumno", alumno);
			result.addObject("tarjetaDeCredito", tarjetaDeCredito);
		}

		return result;
	}
}
