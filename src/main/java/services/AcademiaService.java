
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Academia;
import repositories.AcademiaRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AcademiaService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AcademiaRepository academiaRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public AcademiaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Academia> findAll() {
		Collection<Academia> result;

		result = this.academiaRepository.findAll();

		return result;
	}

	// Other business methods -------------------------------------------------

	public Academia findByPrincipal() {
		Academia result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Academia findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Academia result;

		result = this.academiaRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Academia save(final Academia academy) {
		Assert.notNull(academy);
		Academia aca = null;

		if (this.exists(academy.getId())) {
			aca = this.findOne(academy.getId());

			aca.setNombre(academy.getNombre());
			aca.setApellidos(academy.getApellidos());
			aca.setCorreoElectronico(academy.getCorreoElectronico());
			aca.setnumeroTelefono(academy.getnumeroTelefono());
			aca.setdireccionPostal(academy.getdireccionPostal());
			aca.setComentario(academy.getComentario());
			aca.setnombreComercial(academy.getnombreComercial());
			aca.setCurso(academy.getCurso());
			aca.setSeguidores(academy.getSeguidores());
			aca.setTutorial(academy.getTutorial());

			aca = this.academiaRepository.save(aca);
		} else {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			academy.getUserAccount().setPassword(encoder.encodePassword(academy.getUserAccount().getPassword(), null));

			aca = this.academiaRepository.save(academy);
		}
		return aca;
	}

	public Academia findOne(final Integer academy) {
		Assert.notNull(academy);
		return this.academiaRepository.findOne(academy);
	}
	public boolean exists(final Integer academyID) {
		return this.academiaRepository.exists(academyID);
	}

}
