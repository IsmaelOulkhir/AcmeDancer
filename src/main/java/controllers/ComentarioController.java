
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ComentarioService;

@Controller
@RequestMapping("/Comentario")
public class ComentarioController extends AbstractController {

	@Autowired
	private ComentarioService ComentarioService;


	// Constructors -----------------------------------------------------------

	public ComentarioController() {
		super();
	}

	//listar
	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		result = new ModelAndView("Comentario/action-1");
		result.addObject("Comentario", this.ComentarioService.findAllByOrderByDateDesc());

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	//borrar
	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("Comentario/action-2");

		return result;
	}

	/*
	 * //crear
	 *
	 * @RequestMapping("/action-3")
	 * public ModelAndView action3() {
	 * ModelAndView result;
	 *
	 * result = new ModelAndView("Comentarios/action-3");
	 *
	 * return result;
	 * }
	 */
}
