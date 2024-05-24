package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Academia;
import domain.Shout;
import services.AcademiaService;
import services.ShoutService;

@Controller
@RequestMapping("/academia")
public class AcademiaController extends AbstractController {

	@Autowired
	private ShoutService shoutService;
	@Autowired
	private AcademiaService academiaService;


	// Constructors -----------------------------------------------------------

	public AcademiaController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Shout> shouts;

		shouts = this.shoutService.findAll();

		result = new ModelAndView("academia/action-1");
		result.addObject("shouts", shouts);

		return result;
	}
	
	// List ---------------------------------------------------------------

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Academia> academias;

			academias = this.academiaService.findAll();

			result = new ModelAndView("academia/list");
			result.addObject("requestURI", "academia/list.do");
			result.addObject("academias", academias);

			return result;
		}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping(value = "/action-2", method = RequestMethod.GET)
	public ModelAndView action2Get() {
		ModelAndView result;
		Shout shout;

		shout = this.shoutService.create();

		result = new ModelAndView("academia/action-2");
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
			result = new ModelAndView("academia/action-2");
			result.addObject("shout", shout);
		}

		return result;

	}
}
