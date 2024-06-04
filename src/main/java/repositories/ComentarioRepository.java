
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	@Query("select c from Comentario c where c.actor.id = ?1")
	List<Comentario> findByActoresId(int actorId);

	@Query("select c from Comentario c order by c.fechaCom desc")
	List<Comentario> findAllByOrderByfechaComDesc();

	//lista de Comentario de los suscripto de ese actor
	@Query("select c from Actores a join a.seguidores s join a.comentario c where a.id = ?1 order by c.fechaCom DESC")
	List<Comentario> listComentarioBySuscribe(int SuscribeId);
}
