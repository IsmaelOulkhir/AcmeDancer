/*
 * AnnouncementService.java
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

import repositories.CursoRepository;
import domain.Academia;
import domain.Alumno;
import domain.Curso;

@Service
@Transactional
public class CursoService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CursoRepository	cursoRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AlumnoService			alumnoService;
	@Autowired
	private SolicitudService		solicitudService;
	@Autowired
	private AcademiaService			academiaService;


	// Constructors -----------------------------------------------------------

	public CursoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Curso create() {
		Curso result;

		result = new Curso();

		return result;
	}

	public Collection<Curso> findAll() {
		Collection<Curso> result;

		Assert.notNull(this.cursoRepository);
		result = this.cursoRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Curso findOne(final int cursoId) {
		Curso result;

		result = this.cursoRepository.findOne(cursoId);

		return result;
	}

	public Curso save(final Curso curso ) {
		assert curso != null;

		Curso result;
		Date currentMoment;

		currentMoment = new Date();
		Assert.isTrue(curso.getFechaFin().after(currentMoment));

		result = this.cursoRepository.save(curso);

		return result;
	}

	public void delete(final Curso curso) {
		assert curso != null;
		assert curso.getId() != 0;

		Assert.isTrue(this.cursoRepository.exists(curso.getId()));
		Assert.isTrue(!this.solicitudService.existsRegistrationForCurso(curso));

		this.cursoRepository.delete(curso);
	}

	// Other business methods -------------------------------------------------

	public Collection<Curso> findAllActive() {
		Collection<Curso> result;
		Date currentMoment;

		currentMoment = new Date();
		result = this.cursoRepository.findAllActive(currentMoment);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findRegistered() {
		Collection<Curso> result;
		Alumno alumno;

		alumno = this.alumnoService.findByPrincipal();
		Assert.notNull(alumno);
		result = this.cursoRepository.findByAlumnoId(alumno.getId());

		return result;
	}

	public Collection<Curso> findToAcademia() {
		Collection<Curso> result;
		//Academia
		Academia academia;
		
		academia = this.academiaService.findByPrincipal();
		Assert.notNull(academia);
		result = this.cursoRepository.findByAcademiaId(academia.getId());
	
		return result;
	}

}
