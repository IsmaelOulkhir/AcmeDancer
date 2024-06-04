
package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actores;
import domain.Comentario;
import security.LoginService;
import services.ComentarioService;

@Controller
@RequestMapping("/Comentario")
public class ComentarioController extends AbstractController {

	// Services

	@Autowired
	private ComentarioService	comentarioService;

	@Autowired
	private LoginService		loginService;


	// Constructors -----------------------------------------------------------
	public ComentarioController() {
		super();
	}

	// Actions

	@RequestMapping("/mylist.do")
	public ModelAndView mylist() {
		ModelAndView result;

		final Actores actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		final List<Comentario> comentarios = this.comentarioService.findByActoresId(actor.getId());

		result = new ModelAndView("Comentario/list");
		result.addObject("Comentario", comentarios);
		result.addObject("a", 1);

		return result;
	}

	@RequestMapping("/create.do")
	public ModelAndView create() {
		ModelAndView result;

		final Comentario comentario = this.comentarioService.create();

		result = new ModelAndView("Comentario/create");
		result.addObject("Comentario", comentario);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comentario comentario, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("Comentario/create");
			result.addObject("Comentario", comentario);
		} else
			try {
				this.comentarioService.save(comentario);
				result = new ModelAndView("redirect:/Comentario/mylist.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("Comentario/create");
				result.addObject("Comentario", comentario);
				result.addObject("message", "Comentario.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final Comentario q) {
		ModelAndView result;

		final Actores a = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		if (a != null) {
			if (a.getComentario().contains(q)) {
				this.comentarioService.delete(q);
				result = new ModelAndView("redirect:/Comentario/mylist.do");
			} else
				result = this.list();
		} else
			return this.list();

		return result;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		final List<Comentario> comentarios = this.comentarioService.findAllByOrderByDateDesc();

		final Actores actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		result = new ModelAndView("Comentario/list");
		result.addObject("Comentario", comentarios);
		result.addObject("Seguidores", actor.getSeguidores());
		result.addObject("a", 2);

		return result;
	}

	@RequestMapping("/myListSubscribe.do")
	public ModelAndView listComentarioSuscribe() {
		ModelAndView result;

		final Integer actorId = this.loginService.findActorByUsername(LoginService.getPrincipal().getId()).getId();
		final List<Comentario> comentarios = this.comentarioService.listComentarioBySuscribe(actorId);

		result = new ModelAndView("Comentario/list");
		result.addObject("Comentario", comentarios);
		result.addObject("a", 3);

		return result;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(@RequestParam final Comentario q) {
		ModelAndView result;

		final Actores actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if ((!actor.getSeguidores().contains(q.getActor())) && q.getActor() != actor)
			this.comentarioService.suscribe(q.getActor());
		result = new ModelAndView("redirect:/Comentario/myListSubscribe.do");
		return result;
	}
}
