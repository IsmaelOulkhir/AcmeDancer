
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	@Query("select c from Comentario c where c.actores.id = ?1")
	List<Comentario> findByActoresId(int actorId);

	@Query("select c from Comentario c order by c.fechaCom desc")
	List<Comentario> findAllByOrderByfechaComDesc();
}
