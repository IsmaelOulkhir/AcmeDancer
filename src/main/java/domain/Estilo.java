
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Estilo {

	// Constructors -----------------------------------------------------------
	public Estilo() {
		super();
	}
	// Attributes -------------------------------------------------------------


	String	nombre;
	String	descripcion;
	String	imagenes;
	String	videos;

	// Relationships ----------------------------------------------------------
	Curso	curso;


	@OneToMany
	public Curso getCurso() {

		return this.curso;
	}

	@NotBlank
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@NotBlank
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	@NotBlank
	public String getImagenes() {
		return this.imagenes;
	}
	public void setImagenes(final String imagenes) {
		this.imagenes = imagenes;
	}

	@NotBlank
	@URL
	public String getVideos() {
		return this.videos;
	}
	public void setVideos(final String videos) {
		this.videos = videos;
	}
}
