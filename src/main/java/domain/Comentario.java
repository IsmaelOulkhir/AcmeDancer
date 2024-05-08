
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Comentario {

	// Constructors -----------------------------------------------------------

	public Comentario() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	texto;
	private Date	fechaCom;


	@NotBlank
	@Size(min = 1, max = 140)

	public String getText() {
		return this.texto;
	}

	public void setText(final String text) {
		this.texto = text;
	}

	@NotBlank
	public Date getfechaCom() {
		return this.fechaCom;
	}

	public void setfechaCom(final Date fechaCom) {
		this.fechaCom = fechaCom;
	}


	// Relationships ----------------------------------------------------------

	Collection<Actores> Actores;


	@ManyToOne(optional = false)
	public Collection<Actores> getActores() {
		return this.Actores;
	}

}
