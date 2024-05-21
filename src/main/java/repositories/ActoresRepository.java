
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Actores;

@Repository
public interface ActoresRepository extends JpaRepository<Actores, Integer> {

}
