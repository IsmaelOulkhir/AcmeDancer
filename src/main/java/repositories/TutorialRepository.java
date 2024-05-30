
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	@Query("select c from Tutorial c where c.id = ?1")
	Collection<Tutorial> findByTutorialId(int tutorialId);

	@Override
	@Query("select c from Tutorial c")
	List<Tutorial> findAll();

	//@Query("Select Count(id) from tutorial t where academiaID = ?1 group by id")
	//int tutorialsCountForAcademy(int academiaID);
}
