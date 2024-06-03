
package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curso extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Curso() {
		super();
		this.solicitud = new HashSet<Solicitud>();
	}
	// Attributes -------------------------------------------------------------


	String			Titulo;
	Date			fechaInicio, fechaFin;
	String			diaSemana;
	String			Hora;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Nivel	nivel;


	@NotBlank
	public String getTitulo() {
		return this.Titulo;
	}
	public void setTitulo(final String titulo) {
		this.Titulo = titulo;
	}

	@NotNull
	public Date getFechaInicio() {
		return this.fechaInicio;
	}
	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@NotNull
	public Date getFechaFin() {
		return this.fechaFin;
	}
	public void setFechaFin(final Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@NotNull
	public String getDiaSemana() {
		return this.diaSemana;
	}
	public void setDiaSemana(final String diaSemana) {
		this.diaSemana = diaSemana;
	}
	@NotNull
	public String getHora() {
		return this.Hora;
	}
	public void setHora(final String hora) {
		this.Hora = hora;
	}

	@NotNull
	public Nivel getNivel() {
		return this.nivel;
	}

	public void setNivel(final Object nivel) {
		if (nivel instanceof Nivel)
			this.nivel = (Nivel) nivel;
		else if (nivel instanceof String)
			try {
				final String nivelAux = (String) nivel;
				this.nivel = Nivel.valueOf(nivelAux.toUpperCase());
				System.out.println(this.nivel);
			} catch (final IllegalArgumentException e) {
				System.out.println("El valor proporcionado no coincide con ningún nivel.");
			}
		else
			System.out.println("El tipo del objeto proporcionado no es válido.");
	}


	// Relationships ----------------------------------------------------------
	Academia				academia;
	Estilo					estilo;
	Collection<Solicitud>	solicitud;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Academia getAcademia() {
		return this.academia;
	}

	public void setAcademia(final Academia academia) {
		this.academia = academia;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Estilo getEstilo() {
		return this.estilo;
	}

	public void setEstilo(final Estilo estilo) {
		this.estilo = estilo;
	}

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	public Collection<Solicitud> getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(final Collection<Solicitud> solicitud) {
		this.solicitud = solicitud;
	}

	public void addSolicitud(final Solicitud solicitud) {
		this.solicitud.add(solicitud);
		solicitud.setCurso(this);
	}

	public void removeSolicitud(final Solicitud solicitud) {
		this.solicitud.remove(solicitud);
		solicitud.setCurso(null);
	}

}
