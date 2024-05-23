
package repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	@Query("select c from Curso c where c.id = ?1")
	Collection<Curso> findByCursoId(int cursoId);
	
	@Query("select c from Curso c where c.fechaInicio > ?1")
	Collection<Curso> findAllActive(Date currentMoment);
	
	@Query("select c from Curso c where c.academia.id = ?1")
	Collection<Curso> findByAcademiaId(int academiaId);

	@Query("select s.curso from Solicitud s where s.alumno.id = ?1")
	Collection<Curso> findByAlumnoId(int alumnoId);
	
	@Override
    @Query("select c from Curso c")
    List<Curso> findAll();

}
