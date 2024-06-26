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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TutorialRepository	tutorialRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AcademiaService			academiaService;


	// Constructors -----------------------------------------------------------

	public TutorialService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Tutorial create() {
		Tutorial result;

		result = new Tutorial();

		return result;
	}

	public Collection<Tutorial> findAll() {
		Collection<Tutorial> result;

		Assert.notNull(this.tutorialRepository);
		result = this.tutorialRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Tutorial findOne(final int tutorialId) {
		Tutorial result;

		result = this.tutorialRepository.findOne(tutorialId);

		return result;
	}

	public Tutorial save(final Tutorial tutorial ) {
		assert tutorial != null;

		Tutorial result;
		
		result = this.tutorialRepository.save(tutorial);

		return result;
	}

	public void delete(final Tutorial tutorial) {
		assert tutorial != null;
		assert tutorial.getId() != 0;

		Assert.isTrue(this.tutorialRepository.exists(tutorial.getId()));
		System.out.println("-------------ID: "+tutorial.getId());
		System.out.println("-------------Version: "+tutorial.getVersion());

		this.tutorialRepository.delete(tutorial);
	}

	// Other business methods -------------------------------------------------

	
	public Collection<Tutorial> findByAcademiaId(Integer id) {
		Assert.notNull(id);

		Collection<Tutorial> result;

		result = this.tutorialRepository.findByAcademiaId(id);

		return result;
	}
	

    public List<Tutorial> getTutorialOrder() {
        List<Tutorial> results = tutorialRepository.findTutorialsOrderedByViewsDesc();
        return results;
    }

	public Collection<Tutorial> findByAcademia() {
		return findByAcademiaId(academiaService.findByPrincipal().getId());
	}
	

}
