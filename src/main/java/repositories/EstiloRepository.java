
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Integer> {

}
