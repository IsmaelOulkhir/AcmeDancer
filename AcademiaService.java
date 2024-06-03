package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AcademiaRepository;
import security.LoginService;
import security.UserAccount;
import domain.Academia;

@Service
@Transactional
public class AcademiaService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AcademiaRepository	academiaRepository;


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

}
