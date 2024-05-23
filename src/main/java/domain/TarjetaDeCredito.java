
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class TarjetaDeCredito extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public TarjetaDeCredito() {
		super();
	}
	// Attributes -------------------------------------------------------------


	String	titular;
	String	marca;
	String	numeroValido;
	int		mesCaducidad;
	int		anyoCaducidad;
	int		cvv;


	public String getTitular() {
		return this.titular;
	}

	public void setTitular(final String titular) {
		this.titular = titular;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(final String marca) {
		this.marca = marca;
	}

	@CreditCardNumber
	@NotBlank
	public String getNumeroValido() {
		return this.numeroValido;
	}
	public void setNumeroValido(final String numeroValido) {
		this.numeroValido = numeroValido;
	}

	@Min(1)
	@Max(12)
	@NotNull
	public int getMesCaducidad() {
		return this.mesCaducidad;
	}
	public void setMesCaducidad(final int mesCaducidad) {
		this.mesCaducidad = mesCaducidad;
	}

	@Min(2024)
	@NotNull
	public int getAnyoCaducidad() {
		return this.anyoCaducidad;
	}
	public void setAnyoCaducidad(final int anyoCaducidad) {
		this.anyoCaducidad = anyoCaducidad;
	}

	@Min(100)
	@Max(999)
	@NotNull
	public int getCvv() {
		return this.cvv;
	}
	public void setCvv(final int cvv) {
		this.cvv = cvv;
	}


	// Relationships ----------------------------------------------------------
	Alumno alumno;


	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}

}
