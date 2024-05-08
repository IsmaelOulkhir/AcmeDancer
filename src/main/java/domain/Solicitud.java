
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Solicitud {

	// Constructors -----------------------------------------------------------
	public Solicitud() {
		super();
	}


	// Attributes -------------------------------------------------------------
	Date				fechaSolicitud;
	//Estado estado;

	// Relationships ----------------------------------------------------------
	Alumno				alumno;
	Collection<Curso>	cursos;


	@OneToMany
	public Alumno getAlumno() {

		return this.alumno;
	}
	@ManyToMany
	public Collection<Curso> getcursos() {
		return this.cursos;
	}

}
