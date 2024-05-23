
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Alumno extends Actores {

	// Constructors -----------------------------------------------------------

	public Alumno() {
		super();
		this.solicitud = new HashSet<Solicitud>();
	}

	// Attributes -------------------------------------------------------------


	// Relationships ----------------------------------------------------------

	TarjetaDeCredito		tarjetaDeCredito;
	Collection<Solicitud>	solicitud;


	@OneToOne(optional = true)
	public TarjetaDeCredito getTarjetaDeCredito() {
		return this.tarjetaDeCredito;
	}

	public void setTarjetaDeCredito(final TarjetaDeCredito tarjetaDeCredito) {
		this.tarjetaDeCredito = tarjetaDeCredito;
	}

	@OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
	public Collection<Solicitud> getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(final Collection<Solicitud> solicitud) {
		this.solicitud = solicitud;
	}

	public void addSolicitud(final Solicitud solicitud) {
		this.solicitud.add(solicitud);
		solicitud.setAlumno(this);
	}

	public void removeSolicitud(final Solicitud solicitud) {
		this.solicitud.remove(solicitud);
		solicitud.setAlumno(null);
	}

}
