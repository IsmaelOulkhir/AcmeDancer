
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
    
    // Conteo de comentarios por actor
    @Query("select count(c) from Comentario c join c.actores a group by a.id")
    List<Object> findCommentsByActor();
    
    // Conteo de suscripciones por actor
    @Query("select count(s) from Suscripcion s join s.suscriptor x group by x.id")
    List<Object> findSuscripcionesByActor();
    
    // Conteo de tutoriales por academia
    @Query("select a.id, count(t) from Tutorial t join t.academia a group by a.id")
    List<Object[]> findTutorialCountsByAcademia();
    
    // Conteo de visualizaciones de tutorial
    @Query("select t.visualizaciones from Tutorial t")
    List<Integer> findTutorialCountsByViews();
    
}
