
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.TarjetaDeCredito;
import services.TarjetaDeCreditoService;

@Controller
@RequestMapping("/tarjetadecredito")
public class TarjetaDeCreditoController {

	@Autowired
	private TarjetaDeCreditoService tarjetadecreditoService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(); // Crear nueva tarjeta
		result = new ModelAndView("tarjetadecredito/create");
		result.addObject("tarjetadecredito", tarjetaDeCredito);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save1(@Valid final TarjetaDeCredito tarjetaDeCredito, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = new ModelAndView("tarjetadecredito/create");
			result.addObject("tarjetadecredito", tarjetaDeCredito);
		} else
			try {
				this.tarjetadecreditoService.save(tarjetaDeCredito);
				result = new ModelAndView("redirect:action-1.do"); //mirar
			} catch (final Throwable oops) {
				result = new ModelAndView("tarjetadecredito/create");
				result.addObject("tarjetadecredito", tarjetaDeCredito);
				result.addObject("message", "tarjetaDeCredito.save.error"); // Agregar mensaje de error
			}
		return result;
	}

	@Secured("ROLE_ALUMN")
	//editar
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tarjetaDeCreditoId) {
		ModelAndView result;
		TarjetaDeCredito tarjetaDeCredito;
		tarjetaDeCredito = this.tarjetadecreditoService.findOne(tarjetaDeCreditoId);
		Assert.notNull(tarjetaDeCredito);
		result = new ModelAndView("tarjetadecredito/edit");
		result.addObject("tarjetadecredito", tarjetaDeCredito);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final TarjetaDeCredito tarjetaDeCredito, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = new ModelAndView("tarjetadecredito/edit");
		else
			try {
				this.tarjetadecreditoService.save(tarjetaDeCredito);
				result = new ModelAndView("redirect:action-1.do"); //mirar
			} catch (final Throwable oops) {
				result = new ModelAndView("tarjetadecredito/edit");
				result.addObject("tarjetadecredito", tarjetaDeCredito);
			}
		return result;
	}
}
