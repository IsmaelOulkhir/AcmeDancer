package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Estilo extends DomainEntity {

    // Constructors -----------------------------------------------------------
    public Estilo() {
        super();
        // Inicializar la colección de cursos como un HashSet vacío
        this.curso = new HashSet<Curso>();
    }
    
    // Attributes -------------------------------------------------------------

    private String nombre;
    private String descripcion;
    private String imagenes;
    private String videos;

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

    @URL
    public String getImagenes() {
        return this.imagenes;
    }

    public void setImagenes(final String imagenes) {
        this.imagenes = imagenes;
    }

    @URL
    public String getVideos() {
        return this.videos;
    }

    public void setVideos(final String videos) {
        this.videos = videos;
    }

    // Relationships ----------------------------------------------------------
    private Collection<Curso> curso;

    @OneToMany(mappedBy = "estilo", cascade = CascadeType.ALL)
    public Collection<Curso> getCurso() {
        return this.curso;
    }

    public void setCurso(final Collection<Curso> curso) {
        this.curso = curso;
    }

    public void addCurso(final Curso curso) {
        this.curso.add(curso);
        curso.setEstilo(this);
    }

    public void removeCurso(final Curso curso) {
        this.curso.remove(curso);
        curso.setEstilo(null);
    }

}