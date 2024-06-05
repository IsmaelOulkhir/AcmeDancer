
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Suscripcion extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Suscripcion() {
		super();
	}

	// Relationships ----------------------------------------------------------
	Actores	suscriptor;
	Actores	creador;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Actores getSuscriptor() {
		return this.suscriptor;
	}

	public void setSuscriptor(final Actores suscriptor) {
		this.suscriptor = suscriptor;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Actores getCreador() {
		return this.suscriptor;
	}

	public void setCreador(final Actores creador) {
		this.creador = creador;
	}
}
