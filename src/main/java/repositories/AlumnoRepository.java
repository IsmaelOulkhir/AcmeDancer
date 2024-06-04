
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Alumno;
import domain.TarjetaDeCredito;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	@Query("select c from Alumno c where c.userAccount.id = ?1")
	Alumno findByUserAccountId(int userAccountId);

	@Query("select c.tarjetaDeCredito from Alumno c where c.userAccount.id = ?1")
	TarjetaDeCredito findTarjetaByUserAccountId(int userAccountId);
}
