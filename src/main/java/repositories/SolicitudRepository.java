
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
	@Query("select s from Solicitud s where s.curso.id = ?1")
	Collection<Solicitud> findByCursoId(int cursoId);

	@Query("select s from Solicitud s where s.alumno.id = ?1 and s.curso.id = ?2")
	Solicitud findByAlumnoIdAndCursoId(int alumnoId, int cursoId);

	@Query("select s from Solicitud s where s.alumno.id = ?1")
	Collection<Solicitud> findByAlumnoId(int alumnoId);

	@Query("select s from Solicitud s where s.curso.academia.id = ?1")
	Collection<Solicitud> findByAcademyId(int academyId);

}
