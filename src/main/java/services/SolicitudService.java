/*
 * RegistrationService.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Academia;
import domain.Alumno;
import domain.Curso;
import domain.Estado;
import domain.Solicitud;
import repositories.SolicitudRepository;

@Service
@Transactional
public class SolicitudService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SolicitudRepository	solicitudRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AlumnoService			alumnoService;
	@Autowired
	private AcademiaService			academiaService;


	// Constructors -----------------------------------------------------------

	public SolicitudService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Solicitud createSolicitud(Curso curso) {
		Assert.notNull(curso);

		Solicitud result;
		Date moment;
		Alumno alumno;

		moment = new Date(System.currentTimeMillis() - 1);
		alumno = alumnoService.findByPrincipal();

		result = new Solicitud();
		result.setCurso(curso);
		result.setFechaSolicitud(moment);
		result.setEstado(Estado.PENDIENTE);
		result.setAlumno(alumno);

		return result;
	}

	public Solicitud save(Solicitud solicitud) {
		Assert.notNull(solicitud);

		Solicitud result;

		result = solicitudRepository.save(solicitud);

		return result;
	}
	
	public Solicitud findOne(final int solicitudId) {
		Solicitud result;

		result = this.solicitudRepository.findOne(solicitudId);

		return result;
	}

	public void delete(Solicitud solicitud) {
		Assert.notNull(solicitud);
		Assert.isTrue(solicitud.getId() != 0);

		solicitudRepository.delete(solicitud);
	}

	// Business methods -------------------------------------------------------

	public Collection<Solicitud> findByPrincipal() {
		Collection<Solicitud> result;
		Alumno alumno;

		alumno = alumnoService.findByPrincipal();
		result = alumno.getSolicitud();

		return result;
	}

	public boolean existsRegistrationForCurso(Curso curso) {
		boolean result;
		Collection<Solicitud> solicitudes;

		solicitudes = solicitudRepository.findByCursoId(curso.getId());
		result = !solicitudes.isEmpty();

		return result;
	}

	public Solicitud findByAlumnoAndCurso(Alumno alumno, Curso curso) {
		Assert.notNull(alumno);
		Assert.notNull(curso);

		Solicitud result;

		result = solicitudRepository.findByAlumnoIdAndCursoId(alumno.getId(), curso.getId());

		return result;
	}


	public Solicitud findByAlumnoAndCurso(Curso curso) {
		return findByAlumnoAndCurso(alumnoService.findByPrincipal(), curso);
	}

	public Collection<Solicitud> findByAlumno(Alumno alumno) {
		Assert.notNull(alumno);

		Collection<Solicitud> result;

		result = solicitudRepository.findByAlumnoId(alumno.getId());

		return result;
	}

	public Collection<Solicitud> findByAlumno() {
		return findByAlumno(alumnoService.findByPrincipal());
	}

	public Collection<Solicitud> findByAcademia(Academia alumno) {
		Assert.notNull(alumno);

		Collection<Solicitud> result;

		result = solicitudRepository.findByAcademyId(alumno.getId());

		return result;
	}

	public Collection<Solicitud> findByAcademia() {
		return findByAcademia(academiaService.findByPrincipal());
	}

}
