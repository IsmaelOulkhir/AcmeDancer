
package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Academia;
import domain.Actores;
import domain.Alumno;
import domain.Comentario;
import repositories.ActorRepository;
import repositories.ComentarioRepository;
import security.Authority;
import security.LoginService;

@Service
@Transactional
public class ComentarioService {

	@Autowired
	private AcademiaService			academyService;

	@Autowired
	private ActoresService			actoresService;

	@Autowired
	private LoginService			loginService;

	@Autowired
	private AlumnoService			alumnoService;

	// Managed repository -----------------------------------------------------
	@Autowired
	private ComentarioRepository	ComentarioRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorRepository			actorRepository;


	// Constructors -----------------------------------------------------------
	public ComentarioService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comentario create() {
		final Comentario Comentario = new Comentario();

		final Actores a = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		Comentario.setTexto(new String());
		Comentario.setActor(a);
		Comentario.setfechaCom(new Date());

		return Comentario;
	}
	public void delete(final Comentario Comentario) {
		Assert.notNull(Comentario);
		final Actores a = Comentario.getActor();
		Assert.notNull(a);
		final List<Comentario> Comentarios = a.getComentario();
		Comentarios.remove(Comentario);
		a.setComentario(Comentarios);

		if (a.getUserAccount().getAuthorities().contains(Authority.ACADEMY))
			this.academyService.save((Academia) a);
		else if (a.getUserAccount().getAuthorities().contains(Authority.ALUMN))
			this.alumnoService.save((Alumno) a);
		else if (a.getUserAccount().getAuthorities().contains(Authority.ADMIN))
			this.actorRepository.save(a);

		this.ComentarioRepository.delete(Comentario);
	}

	public List<Comentario> findAll() {
		return this.ComentarioRepository.findAll();
	}

	public Comentario findOne(final int id) {
		return this.ComentarioRepository.findOne(id);
	}

	public Comentario save(Comentario Comentario) {
		Assert.notNull(Comentario);

		final Actores a = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		Comentario = this.ComentarioRepository.save(Comentario);

		final List<Comentario> ComentarioActor = a.getComentario();
		ComentarioActor.add(Comentario);
		a.setComentario(ComentarioActor);

		if (a.getUserAccount().getAuthorities().contains(Authority.ACADEMY))
			this.academyService.save((Academia) a);
		else if (a.getUserAccount().getAuthorities().contains(Authority.ALUMN))
			this.alumnoService.save((Alumno) a);
		else if (a.getUserAccount().getAuthorities().contains(Authority.ADMIN))
			this.actoresService.save(a);

		return this.ComentarioRepository.save(Comentario);
	}

	public Actores suscribe(final Actores actor) {
		Assert.notNull(actor);

		final Actores ac = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		ac.getSeguidores().add(actor);

		if (actor.getUserAccount().getAuthorities().contains(Authority.ACADEMY))
			this.academyService.save((Academia) actor);
		else if (actor.getUserAccount().getAuthorities().contains(Authority.ALUMN))
			this.alumnoService.save((Alumno) actor);
		else if (actor.getUserAccount().getAuthorities().contains(Authority.ADMIN))
			this.actoresService.save(actor);

		return actor;
	}
	// Business methods -------------------------------------------------------
	public List<Comentario> findByActoresId(final int actorId) {
		return this.ComentarioRepository.findByActoresId(actorId);
	}

	public List<Comentario> findAllByOrderByDateDesc() {
		return this.ComentarioRepository.findAllByOrderByfechaComDesc();
	}
	public List<Comentario> listComentarioBySuscribe(final int SuscribeId) {
		return this.ComentarioRepository.listComentarioBySuscribe(SuscribeId);
	}

}
