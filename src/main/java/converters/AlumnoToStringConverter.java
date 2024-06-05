/*
 * AlumnoToStringConverter.java
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

import domain.Alumno;

@Component
@Transactional
public class AlumnoToStringConverter implements Converter<Alumno, String> {

	@Override
	public String convert(final Alumno alumno) {
		String result;

		if (alumno == null)
			result = null;
		else
			result = String.valueOf(alumno.getId());

		return result;
	}

}
