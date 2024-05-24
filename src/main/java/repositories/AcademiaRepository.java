
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Integer> {
	
	@Query("select a from Academia a where a.userAccount.id = ?1")
	Academia findByUserAccountId(int userAccountId);
	
	@Override
    @Query("select a from Academia a")
    List<Academia> findAll();
}
