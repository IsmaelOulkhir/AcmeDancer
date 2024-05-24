
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
	"nombre", "apellidos"
}))

public abstract class Actores extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Actores() {
		super();
		this.comentario = new HashSet<Comentario>();
	}


	// Attributes -------------------------------------------------------------

	private String	nombre;
	private String	apellidos;
	private String	correoElectronico;
	private String	numeroTelefono;
	private String	direccionPostal;


	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@NotBlank
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	@Email
	@NotBlank
	@Column(unique = true)
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Column(unique = true)
	@Pattern(regexp = "^([+-]\\d+\\s+)?(\\([0-9]+\\)\\s+)?([\\d\\w\\s-]+)$")
	public String getnumeroTelefono() {
		return this.numeroTelefono;
	}

	public void setnumeroTelefono(final String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getdireccionPostal() {
		return this.direccionPostal;
	}

	public void setdireccionPostal(final String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}


	// Relationships ----------------------------------------------------------

	private UserAccount		userAccount;
	Collection<Comentario>	comentario;


	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@OneToMany(mappedBy = "actores", cascade = CascadeType.ALL)
	public Collection<Comentario> getComentario() {
		return this.comentario;
	}

	public void setComentario(final Collection<Comentario> comentario) {
		this.comentario = comentario;
	}

	public void addCometario(final Comentario comentario) {
		this.comentario.add(comentario);
		comentario.setActores(this);
	}

	public void removeCometario(final Comentario comentario) {
		this.comentario.remove(comentario);
		comentario.setActores(null);
	}

	//Equality ----------------------------------------

	@Override
	public boolean equals(final Object other) {
		// TODO Auto-generated method stub
		boolean result = super.equals(other);

		if (!result)
			return false;

		final String nombreApellidosAux = this.nombre + this.apellidos;
		final Actores actOtherAux = ((Actores) other);
		final String nombreApellidosOther = actOtherAux.getNombre() + actOtherAux.getApellidos();

		if (nombreApellidosAux.equals(nombreApellidosOther))
			result = true;
		else if (actOtherAux.getCorreoElectronico().equals(this.correoElectronico))
			result = true;
		else if (actOtherAux.getnumeroTelefono().equals(this.numeroTelefono))
			result = true;

		return result;

	}

}
