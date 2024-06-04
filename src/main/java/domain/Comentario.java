
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

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
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getfechaCom() {
		return this.fechaCom;
	}

	public void setfechaCom(final Date fCom) {
		this.fechaCom = fCom;
	}


	// Relationships ----------------------------------------------------------

	private Actores actor;


	@NotNull
	@ManyToOne(optional = false)
	public Actores getActor() {
		return this.actor;
	}

	public void setActor(final Actores actor) {
		this.actor = actor;
	}
}
