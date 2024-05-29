package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Estilo;
import services.EstiloService;

@Controller
@RequestMapping("/estilo")
public class EstiloController extends AbstractController {

	@Autowired
	private EstiloService estiloService;


	// Constructors -----------------------------------------------------------

	public EstiloController() {
		super();
	}
	
	// Create -------------------------------------
		@RequestMapping("/create")
		public ModelAndView register(@Valid final Estilo credentials, final BindingResult bindingResult, @RequestParam(required = false) final boolean showError) {
			Assert.notNull(credentials);
			Assert.notNull(bindingResult);

			ModelAndView result;
			
			result = new ModelAndView("estilo/create");
			result.addObject("estilo", credentials);
			result.addObject("showError", showError);

			return result;
		}
		
		@RequestMapping("/createFailure")
		public ModelAndView failure() {
			ModelAndView result;

			result = new ModelAndView("redirect:create.do?showError=true");

			return result;
		}

	// List ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Estilo> estilos;

		estilos = this.estiloService.findAll();

		result = new ModelAndView("estilo/list");
		result.addObject("requestURI", "estilo/list.do");
		result.addObject("estilos", estilos);

		return result;
	}

}
