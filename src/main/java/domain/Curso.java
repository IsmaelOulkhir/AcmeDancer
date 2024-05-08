
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curso {

	// Constructors -----------------------------------------------------------
	public Curso() {
		super();
	}
	// Attributes -------------------------------------------------------------


	String					Titulo;
	Date					fechaInicio, fechaFin;
	String					diaSemana;
	String					Hora;
	//Nivel nivel;

	// Relationships ----------------------------------------------------------
	Academia				academia;
	Estilo					estilo;
	Collection<Solicitud>	Solicitud;


	@ManyToOne
	public Academia getAcademia() {
		return this.academia;
	}

	@ManyToOne
	public Estilo getEstilo() {
		return this.estilo;
	}

	@ManyToMany
	public Collection<Solicitud> getSolicitud() {
		return this.Solicitud;
	}

	@NotBlank
	public String getTitulo() {
		return this.Titulo;
	}
	public void setTitulo(final String titulo) {
		this.Titulo = titulo;
	}

	@NotBlank
	public Date getFechaInicio() {
		return this.fechaInicio;
	}
	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@NotBlank
	public Date getFechaFin() {
		return this.fechaFin;
	}
	public void setFechaFin(final Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@NotBlank
	public String getDiaSemana() {
		return this.diaSemana;
	}
	public void setDiaSemana(final String diaSemana) {
		this.diaSemana = diaSemana;
	}
	@NotBlank
	public String getHora() {
		return this.Hora;
	}
	public void setHora(final String hora) {
		this.Hora = hora;
	}
}
