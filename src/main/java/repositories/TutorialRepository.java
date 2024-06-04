
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {
	@Query("select t from Tutorial t where t.id = ?1")
	Collection<Tutorial> findByTutorialId(int cursoId);
	
	@Query("select t from Tutorial t where t.academia.id = ?1")
	Collection<Tutorial> findByAcademiaId(int academiaId);
	
    @Query("select t from Tutorial t order by t.visualizaciones desc")
    List<Tutorial> findTutorialsOrderedByViewsDesc();
	
	@Override
    @Query("select t from Tutorial t")
    List<Tutorial> findAll();
}
