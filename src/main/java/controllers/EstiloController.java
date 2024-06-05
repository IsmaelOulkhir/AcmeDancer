package controllers;

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
	
		// Edition ----------------------------------------------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int estiloId, @RequestParam(required = false) final boolean showError) {
			ModelAndView result;
			Estilo estilo;

			estilo = this.estiloService.findOne(estiloId);
			Assert.notNull(estilo);
			result = new ModelAndView("estilo/edit");
			result.addObject("estilo", estilo);
			result.addObject("showError", showError);

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Estilo estilo, final BindingResult binding) {
			ModelAndView result;
			System.out.println("entra");
			if (binding.hasErrors()) {
				System.out.println("tiene error");
				result = new ModelAndView("estilo/edit");
				result.addObject("estilo", estilo);
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
					this.estiloService.save(estilo);
					result = new ModelAndView("redirect:list-admin.do");
				} catch (Exception e) {
					System.out.println("tiene error de guardado");
					System.out.println(e.getMessage());
					result = new ModelAndView("estilo/edit");
					result.addObject("estilo", estilo);
					result.addObject("showError", true);
				}
			}

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "edit")
		public ModelAndView edit(@Valid final Estilo estilo, final BindingResult binding) {
			ModelAndView result;
			System.out.println("entra");
			if (binding.hasErrors()) {
				System.out.println("tiene error");
				result = new ModelAndView("estilo/edit");
				result.addObject("estilo", estilo);
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
					this.estiloService.save(estilo);
					result = new ModelAndView("redirect:list-admin.do");
				} catch (Exception e) {
					System.out.println("tiene error de guardado");
					System.out.println(e.getMessage());
					result = new ModelAndView("estilo/edit");
					result.addObject("estilo", estilo);
					result.addObject("showError", true);
				}
			}

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(final Estilo estilo, final BindingResult binding) {
			ModelAndView result;
			try {
				this.estiloService.delete(estilo);
				result = new ModelAndView("redirect:list-admin.do");
			} catch (Exception e) {
				System.out.println("tiene error de eliminar");
				e.printStackTrace();
				result = new ModelAndView("estilo/edit");
				result.addObject("estilo", estilo);
				result.addObject("showError", true);
			}

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
		
		@RequestMapping(value = "/list-admin", method = RequestMethod.GET)
		public ModelAndView listAdmin() {
			ModelAndView result;
			Collection<Estilo> estilos;

			estilos = this.estiloService.findAll();

			result = new ModelAndView("estilo/list-admin");
			result.addObject("requestURI", "estilo/list-admin.do");
			result.addObject("estilos", estilos);

			return result;
		}

}
