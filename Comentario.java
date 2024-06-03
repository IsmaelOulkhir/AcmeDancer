
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Comentario extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Comentario() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	texto;
	private Date	fechaCom;


	@NotBlank
	@Size(min = 1, max = 140)

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}

	@NotNull
	public Date getfechaCom() {
		return this.fechaCom;
	}

	public void setfechaCom(final Date fCom) {
		this.fechaCom = fCom;
	}


	// Relationships ----------------------------------------------------------

	Actores actores;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actores getActores() {
		return this.actores;
	}

	public void setActores(final Actores actores) {
		this.actores = actores;
	}

}
