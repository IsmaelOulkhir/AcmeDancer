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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CursoRepository;
import repositories.EstiloRepository;
import domain.Estilo;

@Service
@Transactional
public class EstiloService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private EstiloRepository	estiloRepository;
	@Autowired
	private CursoRepository	cursoRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public EstiloService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Estilo create() {
		Estilo result;

		result = new Estilo();

		return result;
	}

	public Collection<Estilo> findAll() {
		Collection<Estilo> result;

		Assert.notNull(this.estiloRepository);
		result = this.estiloRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Estilo findOne(final int estiloId) {
		Estilo result;

		result = this.estiloRepository.findOne(estiloId);

		return result;
	}

	public Estilo save(final Estilo estilo ) {
		assert estilo != null;

		Estilo result;

		result = this.estiloRepository.save(estilo);

		return result;
	}

	public void delete(final Estilo estilo) {
		assert estilo != null;
		assert estilo.getId() != 0;

		Assert.isTrue(this.estiloRepository.exists(estilo.getId()));
		//ver si no tiene cursos con este estilo
		if(this.cursoRepository.findByEstiloId(estilo.getId()).size() == 0) {
			this.estiloRepository.delete(estilo);
		}else {
			Assert.isTrue(false);
		}
		
	}

	

}
