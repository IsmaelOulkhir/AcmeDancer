
package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Comentario;
import repositories.ActorRepository;
import repositories.ComentarioRepository;

@Service
@Transactional
public class ComentarioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ComentarioRepository	ComentarioRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorRepository			actorRepository;


	// Constructors -----------------------------------------------------------
	public ComentarioService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comentario addComentario(final int actorId, final String content) {
		final Comentario Comentario = new Comentario();
		Comentario.setActores(this.actorRepository.findOne(actorId));
		Comentario.setTexto(content);
		final Date FechaAhora = new Date();
		Comentario.setfechaCom(FechaAhora);
		return this.ComentarioRepository.save(Comentario);
	}

	public void deleteComentario(final int ComentarioId) {
		this.ComentarioRepository.delete(ComentarioId);
	}

	// Business methods -------------------------------------------------------
	public List<Comentario> findByActoresId(final int actorId) {
		return this.ComentarioRepository.findByActoresId(actorId);
	}

	public List<Comentario> findAllByOrderByDateDesc() {
		return this.ComentarioRepository.findAllByOrderByfechaComDesc();
	}

}
