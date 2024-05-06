
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Academia extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Academia() {
		super();

		this.Tutorial = new HashSet<Tutorial>();
	}


	// Attributes -------------------------------------------------------------

	private String nombreComercial;


	public String getnombreComercial() {
		return this.nombreComercial;
	}

	public void setnombreComercial(final String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}


	// Relationships ----------------------------------------------------------

	Collection<Tutorial>	Tutorial;
	Collection<Curso>		Curso;


	@OneToMany
	public Collection<Tutorial> getTutotial() {
		return this.Tutorial;
	}

	@OneToMany
	public Collection<Curso> getCurso() {

		return this.Curso;
	}

}
