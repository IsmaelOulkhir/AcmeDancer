/*
 * CustomerService.java
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

import domain.Alumno;
import domain.Curso;
import domain.Solicitud;
import repositories.AlumnoRepository;
import security.LoginService;
import security.UserAccount;
@Service
@Transactional
public class AlumnoService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AlumnoRepository	alumnoRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private SolicitudService	solicitudService;
	@Autowired
	private CursoService	cursoService;

	// Constructors -----------------------------------------------------------

	public AlumnoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Alumno create() {
		Alumno result;

		result = new Alumno();

		return result;
	}

	public Collection<Alumno> findAll() {
		Collection<Alumno> result;

		result = alumnoRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Alumno findOne(int alumnoId) {
		Alumno result;

		result = alumnoRepository.findOne(alumnoId);
		Assert.notNull(result);

		return result;
	}

	public Alumno save(Alumno alumno) {
		Assert.notNull(alumno);

		Alumno result;

		result = alumnoRepository.save(alumno);

		return result;
	}

	public void delete(Alumno alumno) {
		Assert.notNull(alumno);
		Assert.isTrue(alumno.getId() != 0);

		alumnoRepository.delete(alumno);
	}

	// Other business methods -------------------------------------------------

	public Alumno findByPrincipal() {
		Alumno result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Alumno findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Alumno result;

		result = alumnoRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
	public void solicitarCurso(int cursoId) {
		Assert.isTrue(cursoId != 0);

		Alumno alumno;
		Curso curso;
		Solicitud solicitud;
		Date currentMoment;

		alumno = findByPrincipal();
		Assert.notNull(alumno);
		curso = cursoService.findOne(cursoId);
		Assert.notNull(curso);
		currentMoment = new Date();
		Assert.isTrue(curso.getFechaInicio().after(currentMoment));
		solicitud = solicitudService.findByAlumnoAndCurso(alumno, curso);
		Assert.isNull(solicitud);

		solicitud = solicitudService.createSolicitud(curso);
		alumno.addSolicitud(solicitud);
		curso.addSolicitud(solicitud);

		alumnoRepository.save(alumno);
		cursoService.save(curso);
		solicitudService.save(solicitud);
	}

	public void cancelarSolicitud(int cursoId) {
		Assert.isTrue(cursoId != 0);

		Alumno alumno;
		Curso curso;
		Solicitud solicitud;
		Date currentDate;

		alumno = findByPrincipal();
		Assert.notNull(alumno);
		curso = cursoService.findOne(cursoId);
		Assert.notNull(curso);
		solicitud = solicitudService.findByAlumnoAndCurso(alumno, curso);
		Assert.notNull(solicitud);

		currentDate = new Date();
		Assert.isTrue(currentDate.before(curso.getFechaInicio()));

		alumno.removeSolicitud(solicitud);
		curso.removeSolicitud(solicitud);

		alumnoRepository.save(alumno);
		cursoService.save(curso);
		solicitudService.save(solicitud);
	}

}
