
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)

public abstract class Actores extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Actores() {
		super();
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
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectroncio(final String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

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

	private UserAccount userAccount;


	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
