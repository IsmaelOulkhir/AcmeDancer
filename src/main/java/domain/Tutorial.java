
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Tutorial() {
		super();
	}
	// Attributes -------------------------------------------------------------


	String	titulo;
	String	descripcion;
	String	video;
	int visualizaciones;

	@NotBlank
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(final String titulo) {
		this.titulo = titulo;
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
	
	@NotNull
	public int getVisualizaciones() {
		return this.visualizaciones;
	}
	
	public void setVisualizaciones(final int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}


	// Relationships ----------------------------------------------------------
	Academia academia;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Academia getAcademia() {
		return this.academia;
	}

	public void setAcademia(final Academia academia) {
		this.academia = academia;
	}

}
