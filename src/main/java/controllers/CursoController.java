
package controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curso;
import services.AcademiaService;
import services.CursoService;
import services.EstiloService;

@Controller
@RequestMapping("/curso")
public class CursoController extends AbstractController {

	@Autowired
	private CursoService	cursoService;
	@Autowired
	private EstiloService	estiloService;
	@Autowired
	private AcademiaService	academiaService;


	// Constructors -----------------------------------------------------------

	public CursoController() {
		super();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	// Create -------------------------------------
	@RequestMapping("/create")
	public ModelAndView register(@Valid final Curso credentials, final BindingResult bindingResult, @RequestParam(required = false) final boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);

		ModelAndView result;

		result = new ModelAndView("curso/create");
		result.addObject("curso", credentials);
		result.addObject("estilos", estiloService.findAll());
		result.addObject("academia", academiaService.findByPrincipal());
		result.addObject("showError", showError);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int cursoId, @RequestParam(required = false) final boolean showError) {
		ModelAndView result;
		Curso curso;

		curso = this.cursoService.findOne(cursoId);
		Assert.notNull(curso);
		result = new ModelAndView("curso/edit");
		result.addObject("curso", curso);
		result.addObject("estilos", estiloService.findAll());
		result.addObject("academia", academiaService.findByPrincipal());
		result.addObject("showError", showError);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curso curso, final BindingResult binding) {
		ModelAndView result;
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("curso/edit");
			result.addObject("curso", curso);
			result.addObject("estilos", estiloService.findAll());
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
				this.cursoService.save(curso);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println("tiene error de guardado");
				result = new ModelAndView("curso/edit");
				result.addObject("curso", curso);
				result.addObject("estilos", estiloService.findAll());
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

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "edit")
	public ModelAndView edit(@Valid final Curso curso, final BindingResult binding) {
		ModelAndView result;
		System.out.println("entra");
		if (binding.hasErrors()) {
			System.out.println("tiene error");
			result = new ModelAndView("curso/edit");
			result.addObject("curso", curso);
			result.addObject("estilos", estiloService.findAll());
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
				this.cursoService.save(curso);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println("tiene error de guardado");
				result = new ModelAndView("curso/edit");
				result.addObject("curso", curso);
				result.addObject("estilos", estiloService.findAll());
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
	public ModelAndView delete(final Curso curso, final BindingResult binding) {
		ModelAndView result;

		try {
			this.cursoService.delete(curso);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("curso/edit");
			result.addObject("curso", curso);
			result.addObject("estilos", estiloService.findAll());
			result.addObject("academia", academiaService.findByPrincipal());
			result.addObject("showError", true);
		}

		return result;
	}

	// List ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "estiloId", required = false) Integer estiloId, @RequestParam(value = "academiaId", required = false) Integer academiaId) {
		ModelAndView result;
		Collection<Curso> cursos;

		if (academiaId == null && estiloId == null) {
			cursos = this.cursoService.findAll();
			System.out.println("Todos los cursos");
		} else {
			if (academiaId != null) {
				System.out.println("academiaid: " + academiaId);
				cursos = this.cursoService.findByAcademiaId(academiaId);
				System.out.println("Cursos de academia");
			} else {
				System.out.println("estiloid: " + estiloId);
				cursos = this.cursoService.findByEstiloId(estiloId);
				System.out.println("Cursos de estilo");
			}
		}

		result = new ModelAndView("curso/list");
		result.addObject("requestURI", "curso/list.do");
		result.addObject("cursos", cursos);

		return result;
	}

	// List ---------------------------------------------------------------

	@RequestMapping(value = "/list-academy", method = RequestMethod.GET)
	public ModelAndView list_academy() {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findByAcademia();

		result = new ModelAndView("curso/list-academy");
		result.addObject("requestURI", "curso/list-academy.do");
		result.addObject("cursos", cursos);

		return result;
	}

}
