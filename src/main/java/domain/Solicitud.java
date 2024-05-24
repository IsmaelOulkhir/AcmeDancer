
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Solicitud extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Solicitud() {
		super();
	}


	// Attributes -------------------------------------------------------------
	Date fechaSolicitud;


	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(final Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}


	@Enumerated(EnumType.STRING)
	private Estado estado;


	@NotNull
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(final Object estado) {
		if (estado instanceof Estado)
			this.estado = (Estado) estado;
		else if (estado instanceof String)
			try {
				final String estadoAux = (String) estado;
				this.estado = Estado.valueOf(estadoAux.toUpperCase());
				System.out.println(this.estado);
			} catch (final IllegalArgumentException e) {
				System.out.println("El valor proporcionado no coincide con ningún nivel.");
			}
		else
			System.out.println("El tipo del objeto proporcionado no es válido.");
	}


	// Relationships ----------------------------------------------------------
	Alumno	alumno;
	Curso	curso;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
}
