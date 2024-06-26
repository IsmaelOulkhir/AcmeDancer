
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.TarjetaDeCredito;

@Repository
public interface TarjetaDeCreditoRepository extends JpaRepository<TarjetaDeCredito, Integer> {

}
