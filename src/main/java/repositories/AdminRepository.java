
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	// Conteo de cursos por academia
    @Query("select a.id, count(c) from Curso c join c.academia a group by a.id")
    List<Object[]> findCourseCountsByAcademia();

    // Conteo de solicitudes por curso
    @Query("select c.id, count(s) from Solicitud s join s.curso c group by c.id")
    List<Object[]> findSolicitudCountsByCurso();
}
