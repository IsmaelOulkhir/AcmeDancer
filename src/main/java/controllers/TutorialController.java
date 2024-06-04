
package controllers;

import java.util.Collection;
import java.util.List;

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

import domain.Tutorial;
import services.TutorialService;
import services.TutorialService.Statistics;

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
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Tutorial tutorial = new Tutorial(); // Crear nuevo tutorial
		result = new ModelAndView("tutorial/create");
		result.addObject("tutorial", tutorial); // Añadir tutorial al modelo
		return result;
	}

	@Secured("ROLE_ACADEMY")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView save1(@Valid final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = new ModelAndView("tutorial/create");
			result.addObject("tutorial", tutorial); // Asegurarse de que el tutorial está en el modelo
		} else
			try {
				this.tutorialService.save(tutorial); // Guardar el tutorial
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("tutorial/create");
				result.addObject("tutorial", tutorial); // Asegurarse de que el tutorial está en el modelo
				result.addObject("message", "tutorial.save.error"); // Agregar mensaje de error
			}
		return result;
	}

	@Secured("ROLE_ACADEMY")
	//editar
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;
		tutorial = this.tutorialService.findOne(tutorialId);
		Assert.notNull(tutorial);
		result = new ModelAndView("tutorial/edit");
		result.addObject("tutorial", tutorial);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = new ModelAndView("tutorial/edit");
		else
			try {
				this.tutorialService.save(tutorial);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", tutorial);
			}
		return result;
	}

	@Secured("ROLE_ACADEMY")
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam("id") final int tutorialId) {
		ModelAndView result;
		try {
			this.tutorialService.delete(tutorialId);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("tutorial/edit");
			result.addObject("message", "tutorial.delete.error");
		}
		return result;
	}

	// ---- Dashboard -----

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		final Statistics estadisticasTutorialAcademia = this.tutorialService.calculateTutorialStatisticsByAcademia();
		final Statistics estadisticasTutorial = this.tutorialService.calculateSStatisticsByTutorial();

		final List<Object[]> tutorialsOrderedByViews = this.tutorialService.getTutorialsOrderedByViews();

		result = new ModelAndView("tutorial/dashboard");
		result.addObject("minimoTutorialAcademia", estadisticasTutorialAcademia.getMin());
		result.addObject("mediaTutorialAcademia", estadisticasTutorialAcademia.getMean());
		result.addObject("maximoTutorialAcademia", estadisticasTutorialAcademia.getMax());
		result.addObject("minimoTutorial", estadisticasTutorial.getMin());
		result.addObject("mediaTutorial", estadisticasTutorial.getMean());
		result.addObject("maximoTutorial", estadisticasTutorial.getMax());
		result.addObject("tutorialsOrderedByViews", tutorialsOrderedByViews);

		return result;
	}

}
