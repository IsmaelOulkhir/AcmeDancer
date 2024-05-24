
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Academia extends Actores {

	// Constructors -----------------------------------------------------------

	public Academia() {
		super();

		this.tutorial = new HashSet<Tutorial>();
		this.curso = new HashSet<Curso>();
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

	Collection<Tutorial>	tutorial;
	Collection<Curso>		curso;


	@OneToMany(mappedBy = "academia", cascade = CascadeType.ALL)
	public Collection<Tutorial> getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final Collection<Tutorial> tutorial) {
		this.tutorial = tutorial;
	}

	@OneToMany(mappedBy = "academia", cascade = CascadeType.ALL)
	public Collection<Curso> getCurso() {
		return this.curso;
	}

	public void setCurso(final Collection<Curso> curso) {
		this.curso = curso;
	}

}
