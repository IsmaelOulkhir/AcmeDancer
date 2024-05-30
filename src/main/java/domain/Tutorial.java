
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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

	//Método para arreglar enlace youtube -----------------------------------------

	// Método para obtener el código de incrustación del video

	@Transient
	public String getEmbedCode() {
		final String videoId = this.extractVideoId(this.video);
		if (videoId != null)
			return "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		else
			return "Invalid video URL";
	}

	// Método auxiliar para extraer el ID del video de la URL
	private String extractVideoId(final String videoUrl) {
		final String pattern = "^(https?://)?(www\\.)?(youtube\\.com|youtu\\.?be)/.+$";
		if (videoUrl.matches(pattern)) {
			final String[] split = videoUrl.split("v=");
			if (split.length > 1)
				return split[1].split("&")[0];
		}
		return null;
	}

}
