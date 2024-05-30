
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Tutorial;
import services.TutorialService;

@Controller
@RequestMapping("/tutorial")
public class TutorialController {

	@Autowired
	private TutorialService tutorialService;


	// List
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Tutorial> tutoriales;

		tutoriales = this.tutorialService.findAll();

		result = new ModelAndView("tutorial/list");
		result.addObject("requestURI", "tutorial/list.do");
		result.addObject("tutoriales", tutoriales);

		return result;
	}

	@Secured("ROLE_ACADEMY")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute final Tutorial tutorial, final BindingResult bindingResult) {
		final ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("tutorial/form");
			modelAndView.addObject("tutorial", tutorial);
		} else {
			this.tutorialService.create();
			modelAndView.setViewName("redirect:/tutorial/list");
		}
		return modelAndView;
	}

	@Secured("ROLE_ACADEMY")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;

		try {
			tutorial = this.tutorialService.findOne(tutorialId);
			result = new ModelAndView("tutorial/form");
			result.addObject("tutorial", tutorial);
		} catch (final Exception e) {
			// Manejar la excepción si el tutorial no se encuentra
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@Secured("ROLE_ACADEMY")
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("tutorial/form");
			result.addObject("tutorial", tutorial);
		} else
			try {
				this.tutorialService.save(tutorial);
				result = new ModelAndView("redirect:list.do");
			} catch (final Exception e) {
				// Manejar la excepción si ocurre un error al guardar el tutorial
				result = new ModelAndView("tutorial/form");
				result.addObject("tutorial", tutorial);
				result.addObject("error", "Error al guardar el tutorial");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("tutorialId") final Tutorial tutorialId) {
		final ModelAndView modelAndView = new ModelAndView();
		this.tutorialService.delete(tutorialId);
		modelAndView.setViewName("redirect:/tutorial/list");
		return modelAndView;
	}
}
