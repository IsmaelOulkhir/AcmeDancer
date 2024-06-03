
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

	//conteo de tutoriales por academia
	@Query("select a.id, count (t) from  Tutorial t join t.academia a group by a.id")
	List<Object[]> findTutorialCountsByAcademia();

	//conteo de tutoriales
	@Query("select titulo, count(*) as veces_mostrados from Tutorial group by titulo")
	List<Object[]> findTutorialCounts();

	//@Query("SELECT t.titulo, COUNT(t) AS veces_mostrados FROM Tutorial t GROUP BY t.titulo ORDER BY COUNT(t) DESC")
	//List<Object[]> findTutorialsOrderedByViews();

}
