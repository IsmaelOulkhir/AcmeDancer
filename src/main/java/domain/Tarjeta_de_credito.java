
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tarjeta_de_credito {

	// Constructors -----------------------------------------------------------
	public Tarjeta_de_credito() {
		super();
	}
	// Attributes -------------------------------------------------------------


	String	titular;
	String	marca;
	String	numeroValido;
	int		mesCaducidad;
	int		anyoCaducidad;
	int		cvv;

	// Relationships ----------------------------------------------------------
	Alumno	alumno;


	@OneToOne
	public Alumno getAlumno() {
		return this.alumno;
	}

	@CreditCardNumber
	@NotBlank
	public String getNumeroValido() {
		return this.numeroValido;
	}
	public void setNumeroValido(final String numeroValido) {
		this.numeroValido = numeroValido;
	}

	@NotBlank
	public int getMesCaducidad() {
		return this.mesCaducidad;
	}
	public void setMesCaducidad(final int mesCaducidad) {
		this.mesCaducidad = mesCaducidad;
	}

	@NotBlank
	public int getAnyoCaducidad() {
		return this.anyoCaducidad;
	}
	public void setAnyoCaducidad(final int anyoCaducidad) {
		this.anyoCaducidad = anyoCaducidad;
	}

	@NotBlank
	public int getCvv() {
		return this.cvv;
	}
	public void setCvv(final int cvv) {
		this.cvv = cvv;
	}

}
