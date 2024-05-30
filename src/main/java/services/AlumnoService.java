
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
	private CursoService		cursoService;


	// Constructors -----------------------------------------------------------

	public AlumnoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Alumno create() {
		return new Alumno();
	}

	public Collection<Alumno> findAll() {
		final Collection<Alumno> result = this.alumnoRepository.findAll();
		Assert.notNull(result, "La lista de alumnos no puede ser nula");
		return result;
	}

	public Alumno findOne(final int alumnoId) {
		final Alumno result = this.alumnoRepository.findOne(alumnoId);
		Assert.notNull(result, "El alumno no puede ser nulo");
		return result;
	}

	public Alumno save(final Alumno alumno) {
		Assert.notNull(alumno, "El alumno no puede ser nulo");
		return this.alumnoRepository.save(alumno);
	}

	public void delete(final Alumno alumno) {
		Assert.notNull(alumno, "El alumno no puede ser nulo");
		Assert.isTrue(alumno.getId() != 0, "El ID del alumno debe ser válido");
		this.alumnoRepository.delete(alumno);
	}

	// Other business methods -------------------------------------------------

	public Alumno findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount, "La cuenta de usuario no puede ser nula");
		final Alumno result = this.findByUserAccount(userAccount);
		Assert.notNull(result, "El alumno no puede ser nulo");
		return result;
	}

	public Alumno findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount, "La cuenta de usuario no puede ser nula");
		final Alumno result = this.alumnoRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result, "Alumno no encontrado para la cuenta de usuario proporcionada");
		return result;
	}

	public void solicitarCurso(final int cursoId) {
		Assert.isTrue(cursoId != 0, "El ID del curso debe ser válido");

		final Alumno alumno = this.findByPrincipal();
		Assert.notNull(alumno, "El alumno no puede ser nulo");
		final Curso curso = this.cursoService.findOne(cursoId);
		Assert.notNull(curso, "El curso no puede ser nulo");

		final Date currentMoment = new Date();
		Assert.isTrue(curso.getFechaInicio().after(currentMoment), "El curso ya ha comenzado");
		Solicitud solicitud = this.solicitudService.findByAlumnoAndCurso(alumno, curso);
		Assert.isNull(solicitud, "El alumno ya ha solicitado este curso");

		solicitud = this.solicitudService.createSolicitud(curso);
		alumno.addSolicitud(solicitud);
		curso.addSolicitud(solicitud);

		this.alumnoRepository.save(alumno);
		this.cursoService.save(curso);
		this.solicitudService.save(solicitud);
	}

	public void cancelarSolicitud(final int cursoId) {
		Assert.isTrue(cursoId != 0, "El ID del curso debe ser válido");

		final Alumno alumno = this.findByPrincipal();
		Assert.notNull(alumno, "El alumno no puede ser nulo");
		final Curso curso = this.cursoService.findOne(cursoId);
		Assert.notNull(curso, "El curso no puede ser nulo");
		final Solicitud solicitud = this.solicitudService.findByAlumnoAndCurso(alumno, curso);
		Assert.notNull(solicitud, "El alumno no ha solicitado este curso");

		final Date currentDate = new Date();
		Assert.isTrue(currentDate.before(curso.getFechaInicio()), "El curso ya ha comenzado");

		alumno.removeSolicitud(solicitud);
		curso.removeSolicitud(solicitud);

		this.alumnoRepository.save(alumno);
		this.cursoService.save(curso);
		this.solicitudService.save(solicitud);
	}
}
