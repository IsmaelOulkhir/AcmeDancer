
package controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curso;
import domain.Solicitud;
import services.AcademiaService;
import services.CursoService;
import services.EstiloService;
import services.SolicitudService;

@Controller
@RequestMapping("/curso")
public class CursoController extends AbstractController {

	@Autowired
	private CursoService		cursoService;
	@Autowired
	private EstiloService		estiloService;
	@Autowired
	private AcademiaService		academiaService;
	@Autowired
	private SolicitudService	solicitudService;


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
				result = new ModelAndView("redirect:list-academy.do");
			} catch (Exception e) {
				System.out.println("tiene error de guardado");
				System.out.println(e.getMessage());
				result = new ModelAndView("curso/edit");
				result.addObject("curso", curso);
				result.addObject("estilos", estiloService.findAll());
				result.addObject("academia", academiaService.findByPrincipal());
				result.addObject("showError", true);
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
				result = new ModelAndView("redirect:list-academy.do");
			} catch (Exception e) {
				System.out.println("tiene error de editar");
				e.printStackTrace();
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
			result = new ModelAndView("redirect:list-academy.do");
		} catch (Exception e) {
			System.out.println("tiene error de eliminar");
			e.printStackTrace();
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
		result.addObject("busqueda", "");

		return result;
	}
	
// Método para manejar la búsqueda de cursos
	
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView buscar(@RequestBody String busqueda, BindingResult binding) {
        ModelAndView result;
        
        if (busqueda.startsWith("busqueda=")) {
        	busqueda = busqueda.substring("busqueda=".length());
        }
        
        System.out.println(busqueda);
        
        // Validar que el parámetro de búsqueda no esté vacío
        if (binding.hasErrors() || busqueda == null || busqueda.isEmpty()) {
            result = new ModelAndView("curso/list");
    		result.addObject("requestURI", "curso/list.do");
    		result.addObject("cursos", new ArrayList<Curso>());
    		result.addObject("busqueda", "");
        } else {
            // Buscar cursos por el término de búsqueda
            Collection<Curso> cursos = cursoService.findByTerm(busqueda);
            result = new ModelAndView("curso/list");
    		result.addObject("requestURI", "curso/list.do");
    		result.addObject("cursos", cursos);
    		result.addObject("busqueda", "");
        }
        
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

	// Solicitud ----------------------------------------------------------------

	@RequestMapping(value = "/solicitar", method = RequestMethod.POST)
	public ModelAndView solicitar(@RequestParam final int cursoId) {
		ModelAndView result;
		Curso curso;

		curso = this.cursoService.findOne(cursoId);
		if (this.solicitudService.findByAlumnoAndCurso(curso) == null) {

			Solicitud sol = this.solicitudService.createSolicitud(curso);
			this.solicitudService.save(sol);
			result = new ModelAndView("redirect:/solicitud/list-alumn.do");

			return result;
		}
		return null;
	}
	
	

}
