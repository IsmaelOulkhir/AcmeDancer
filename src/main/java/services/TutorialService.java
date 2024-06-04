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

import domain.Tutorial;
import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TutorialRepository tutorialRepository;

	// Supporting services ----------------------------------------------------


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

	public Tutorial save(final Tutorial tutorial) {
		assert tutorial != null;

		Tutorial result;

		result = this.tutorialRepository.save(tutorial);

		return result;
	}

	public void delete(final int tutorialId) {
		Assert.isTrue(tutorialId != 0, "El ID del tutorial no puede ser nulo o cero");

		Assert.isTrue(this.tutorialRepository.exists(tutorialId), "No se encontró ningún tutorial con el ID proporcionado");

		this.tutorialRepository.delete(tutorialId);
	}

	//---- Dashboard -------

	public Statistics calculateTutorialStatisticsByAcademia() {
		final List<Object[]> results = this.tutorialRepository.findTutorialCountsByAcademia();
		return this.calculateStatistics(results);
	}

	public Statistics calculateSStatisticsByTutorial() {
		final List<Object[]> results = this.tutorialRepository.findTutorialCounts();
		return this.calculateStatistics(results);
	}

	private Statistics calculateStatistics(final List<Object[]> results) {
		if (results.isEmpty())
			return new Statistics(0, 0, 0);

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		double sum = 0;
		final int count = results.size();

		for (final Object[] result : results) {
			final int value = ((Long) result[1]).intValue();
			if (value < min)
				min = value;
			if (value > max)
				max = value;
			sum += value;
		}

		final double mean = sum / count;

		return new Statistics(min, mean, max);
	}


	public static class Statistics {

		private final int		min;
		private final double	mean;
		private final int		max;


		public Statistics(final int min, final double mean, final int max) {
			this.min = min;
			this.mean = mean;
			this.max = max;
		}

		public int getMin() {
			return this.min;
		}

		public double getMean() {
			return this.mean;
		}

		public int getMax() {
			return this.max;
		}
	}


	public List<Object[]> getTutorialsOrderedByViews() {
		return this.tutorialRepository.findTutorialsOrderedByViews();
	}

}
