
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Suscripcion extends DomainEntity {

	private Comentario		comentario;
	private Actores			actor;
	private List<Actores>	Suscripcion;


	@OneToMany
	public List<Actores> getSuscripcion() {
		return this.Suscripcion;
	}

	public void setSuscripcion(final List<Actores> suscripcion) {
		this.Suscripcion = suscripcion;
	}

	@ManyToOne
	public Comentario getComentario() {
		return this.comentario;
	}

	public void setComentario(final Comentario comentario) {
		this.comentario = comentario;
	}

	@ManyToOne
	public Actores getActor() {
		return this.actor;
	}

	public void setActor(final Actores actor) {
		this.actor = actor;
	}
}
