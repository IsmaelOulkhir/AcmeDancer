
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Alumno extends Actores {

	// Constructors -----------------------------------------------------------

	public Alumno() {
		super();
	}

	// Attributes -------------------------------------------------------------


	// Relationships ----------------------------------------------------------

	Collection<Tarjeta_de_credito>	Tarjeta_de_credito;
	Collection<Solicitud>			Solicitud;


	@NotNull
	@OneToOne(optional = false)
	public Collection<Tarjeta_de_credito> getTarjeta_de_credito() {
		return this.Tarjeta_de_credito;
	}

	@OneToMany
	public Collection<Solicitud> getSolicitud() {

		return this.Solicitud;
	}

}
