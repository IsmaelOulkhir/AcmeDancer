/*
 * ActoresToStringConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Actores;

@Component
@Transactional
public class ActorToStringConverter implements Converter<Actores, String> {

	@Override
	public String convert(final Actores actores) {
		String result;

		if (actores == null)
			result = null;
		else
			result = String.valueOf(actores.getId());

		return result;
	}

}
