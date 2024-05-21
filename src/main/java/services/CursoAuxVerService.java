package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curso;
import repositories.CursoRepository;

@Service
@Transactional
public class CursoAuxVerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CursoRepository	cursoRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CursoAuxVerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	public Collection<Curso> getCursos(int certificationId) {
		Collection<Curso> result;

		result = cursoRepository.findByCursoId(certificationId);
		Assert.notNull(result);
		Assert.isTrue(certificationId == 0 || !result.isEmpty());
		Assert.isTrue(certificationId != 0 || result.isEmpty());

		return result;
	}

}
