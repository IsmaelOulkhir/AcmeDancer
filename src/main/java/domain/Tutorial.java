
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tutorial {

	// Constructors -----------------------------------------------------------
	public Tutorial() {
		super();
	}
	// Attributes -------------------------------------------------------------


	String		titulos;
	String		descripcion;
	String		video;

	// Relationships ----------------------------------------------------------
	Academia	academia;


	@ManyToOne
	public Academia getAcademia() {

		return this.academia;
	}

	@NotBlank
	public String getTitulos() {
		return this.titulos;
	}
	public void setTitulos(final String titulos) {
		this.titulos = titulos;
	}

	@NotBlank
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	@NotBlank
	public String getVideo() {
		return this.video;
	}
	public void setVideo(final String video) {
		this.video = video;
	}

}
