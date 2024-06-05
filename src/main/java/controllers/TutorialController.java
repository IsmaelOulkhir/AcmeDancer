
package controllers;

import java.util.ArrayList;
import java.util.Collection;

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

import domain.Tutorial;
import services.AcademiaService;
import services.TutorialService;

@Controller
@RequestMapping("/tutorial")
public class TutorialController extends AbstractController {

	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private AcademiaService		academiaService;


	// Constructors -----------------------------------------------------------

	public TutorialController() {
		super();
	}

	// Create -------------------------------------
	@RequestMapping("/create")
	public ModelAndView register(@Valid final Tutorial credentials, final BindingResult bindingResult, @RequestParam(required = false) final boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);

		ModelAndView result;

		result = new ModelAndView("tutorial/create");
		result.addObject("tutorial", credentials);
		result.addObject("academia", academiaService.findByPrincipal());
		result.addObject("showError", showError);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId, @RequestParam(required = false) final boolean showError) {
		ModelAndView result;
		Tutorial tutorial;

		tutorial = this.tutorialService.findOne(tutorialId);
		Assert.notNull(tutorial);
		result = new ModelAndView("tutorial/edit");
		result.addObject("tutorial", tutorial);
		result.addObject("academia", academiaService.findByPrincipal());
		result.addObject("showError", showError);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("tutorial/edit");
			result.addObject("tutorial", tutorial);
			result.addObject("academia", academiaService.findByPrincipal());
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
				this.tutorialService.save(tutorial);
				result = new ModelAndView("redirect:list-academy.do");
			} catch (Exception e) {
				System.out.println("tiene error de guardado");
				System.out.println(e.getMessage());
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", tutorial);
				result.addObject("academia", academiaService.findByPrincipal());
				result.addObject("showError", true);
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "edit")
	public ModelAndView edit(@Valid final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("tutorial/edit");
			result.addObject("tutorial", tutorial);
			result.addObject("academia", academiaService.findByPrincipal());
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
				this.tutorialService.save(tutorial);
				result = new ModelAndView("redirect:list-academy.do");
			} catch (Exception e) {
				System.out.println("tiene error de editar");
				e.printStackTrace();
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", tutorial);
				result.addObject("academia", academiaService.findByPrincipal());
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
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		try {
			this.tutorialService.delete(tutorial);
			result = new ModelAndView("redirect:list-academy.do");
		} catch (Exception e) {
			System.out.println("tiene error de eliminar");
			e.printStackTrace();
			result = new ModelAndView("tutorial/edit");
			result.addObject("tutorial", tutorial);
			result.addObject("academia", academiaService.findByPrincipal());
			result.addObject("showError", true);
		}

		return result;
	}

	// List ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "academiaId", required = false) Integer academiaId) {
		ModelAndView result;
		Collection<Tutorial> tutoriales = new ArrayList<Tutorial>();;

		if (academiaId == null) {
			tutoriales = this.tutorialService.findAll();
			System.out.println("Todos los tutoriales");
		} else {
			if (academiaId != null) {
				System.out.println("academiaid: " + academiaId);
				tutoriales = this.tutorialService.findByAcademiaId(academiaId);
				System.out.println("Tutorials de academia");
			}
		}

		result = new ModelAndView("tutorial/list");
		result.addObject("requestURI", "tutorial/list.do");
		result.addObject("tutoriales", tutoriales);

		return result;
	}

	// List ---------------------------------------------------------------

	@RequestMapping(value = "/list-academy", method = RequestMethod.GET)
	public ModelAndView list_academy() {
		ModelAndView result;
		Collection<Tutorial> tutoriales;

		tutoriales = this.tutorialService.findByAcademia();

		result = new ModelAndView("tutorial/list-academy");
		result.addObject("requestURI", "tutorial/list-academy.do");
		result.addObject("tutoriales", tutoriales);

		return result;
	}
	@RequestMapping(value = "/list-admin", method = RequestMethod.GET)
	public ModelAndView list_admin() {
		ModelAndView result;
		Collection<Tutorial> tutoriales;

		tutoriales = this.tutorialService.getTutorialOrder();

		result = new ModelAndView("tutorial/list-admin");
		result.addObject("requestURI", "tutorial/list-admin.do");
		result.addObject("tutoriales", tutoriales);

		return result;
	}

	// Visualizar ----------------------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView solicitar(@RequestParam final int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;

		tutorial = this.tutorialService.findOne(tutorialId);
		if(tutorial != null) {
			tutorial.setVisualizaciones(tutorial.getVisualizaciones()+1);
			this.tutorialService.save(tutorial);
			result = new ModelAndView("tutorial/view");
			result.addObject("tutorial", tutorial);
			result.addObject("academia", tutorial.getAcademia());

			return result;
		}
		return null;
	}

}
