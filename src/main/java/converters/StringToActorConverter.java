/*
 * StringToActoresConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;
import domain.Actores;

@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actores> {

	@Autowired
	ActorRepository	actoresRepository;


	@Override
	public Actores convert(final String text) {
		Actores result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.actoresRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
