package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Academia;
import domain.Actores;
import domain.Alumno;

@Service
@Transactional
public class ActoresService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository		actorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private AcademiaService	academiaService;
	@Autowired
	private AlumnoService	alumnoService;


	// Constructors -----------------------------------------------------------

	public ActoresService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Actores> findAll() {
		Collection<Actores> result;

		result = actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actores findOne(int actorId) {
		Assert.isTrue(actorId != 0);

		Actores result;

		result = actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Actores save(Actores actor) {
		Assert.notNull(actor);

		Actores result;

		result = actorRepository.save(actor);

		return result;
	}

	public void delete(Actores actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(actorRepository.exists(actor.getId()));

		actorRepository.delete(actor);
	}

	// Other business methods -------------------------------------------------

	public UserAccount findUserAccount(Actores actor) {
		Assert.notNull(actor);

		UserAccount result;

		result = userAccountService.findByActor(actor);

		return result;
	}
	
	public Academia findByPrincipalAcademy() {
		Academia result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = academiaService.findByUserAccount(userAccount);

		return result;
	}
	
	public Alumno findByPrincipalAlumn() {
		Alumno result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = alumnoService.findByUserAccount(userAccount);

		return result;
	}
}