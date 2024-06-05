package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministradorService;
import services.AdministradorService.Statistics;

@Controller
@RequestMapping("/administrador")
public class AdministradorController extends AbstractController {

	@Autowired
	private AdministradorService adminService;


	// Constructors -----------------------------------------------------------

	public AdministradorController() {
		super();
	}

	// Dashboard ---------------------------------------------------------------

	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		Statistics estadisticasCursoAcademia = adminService.calculateCourseStatisticsByAcademia();
		Statistics estadisticasSolicitudCurso  = adminService.calculateSolicitudStatisticsByCurso();
		Statistics estadisticasTutorialAcademia  = adminService.calculateTutorialStatisticsByAcademia();
		Statistics estadisticasTutorialVisualizacion  = adminService.calculateTutorialStatisticsByVisualizacion();
		Statistics estadisticasComentarioActor  = adminService.calculateComentarioStatisticsByActores();
		Statistics estadisticasSuscripcionActor  = adminService.calculateSuscripcionStatisticsByActores();

		result = new ModelAndView("administrador/action-1");
		result.addObject("minimoCursoAcademia", estadisticasCursoAcademia.getMin());
		result.addObject("mediaCursoAcademia", estadisticasCursoAcademia.getMean());
		result.addObject("desviacionCursoAcademia", estadisticasCursoAcademia.getStddev());
		result.addObject("maximoCursoAcademia", estadisticasCursoAcademia.getMax());
		result.addObject("minimoSolicitudCurso", estadisticasSolicitudCurso.getMin());
		result.addObject("mediaSolicitudCurso", estadisticasSolicitudCurso.getMean());
		result.addObject("desviacionSolicitudCurso", estadisticasSolicitudCurso.getStddev());
		result.addObject("maximoSolicitudCurso", estadisticasSolicitudCurso.getMax());
		result.addObject("minimoTutorialAcademia", estadisticasTutorialAcademia.getMin());
		result.addObject("mediaTutorialAcademia", estadisticasTutorialAcademia.getMean());
		result.addObject("maximoTutorialAcademia", estadisticasTutorialAcademia.getMax());
		result.addObject("minimoTutorialVisualizacion", estadisticasTutorialVisualizacion.getMin());
		result.addObject("mediaTutorialVisualizacion", estadisticasTutorialVisualizacion.getMean());
		result.addObject("maximoTutorialVisualizacion", estadisticasTutorialVisualizacion.getMax());
		result.addObject("mediaComentarioActor", estadisticasComentarioActor.getMean());
		result.addObject("mediaSuscripcionActor", estadisticasSuscripcionActor.getMean());
		
		return result;
	}

}
