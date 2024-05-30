
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Alumno;
import domain.TarjetaDeCredito;
import repositories.TarjetaDeCreditoRepository;

@Service
@Transactional
public class TarjetaDeCreditoService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TarjetaDeCreditoRepository tarjetaDeCreditoRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public TarjetaDeCreditoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public TarjetaDeCredito create() {
		TarjetaDeCredito result;

		result = new TarjetaDeCredito();

		return result;
	}

	public Collection<TarjetaDeCredito> findAll() {
		Collection<TarjetaDeCredito> result;

		Assert.notNull(this.tarjetaDeCreditoRepository);
		result = this.tarjetaDeCreditoRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public TarjetaDeCredito findOne(final int tarjetaDeCreditoId) {
		TarjetaDeCredito result;

		result = this.tarjetaDeCreditoRepository.findOne(tarjetaDeCreditoId);

		return result;
	}

	public TarjetaDeCredito save(final TarjetaDeCredito tarjetaDeCredito) {
		Assert.notNull(tarjetaDeCredito);

		TarjetaDeCredito result;

		result = this.tarjetaDeCreditoRepository.save(tarjetaDeCredito);

		return result;
	}

	public void delete(final TarjetaDeCredito tarjetaDeCredito) {
		Assert.notNull(tarjetaDeCredito);
		Assert.isTrue(tarjetaDeCredito.getId() != 0);
		Assert.isTrue(this.tarjetaDeCreditoRepository.exists(tarjetaDeCredito.getId()));

		this.tarjetaDeCreditoRepository.delete(tarjetaDeCredito);
	}

	// Additional method to link a credit card to an Alumno
	public TarjetaDeCredito linkToAlumno(final TarjetaDeCredito tarjetaDeCredito, final Alumno alumno) {
		Assert.notNull(tarjetaDeCredito, "La tarjeta de crédito no puede ser nula");
		Assert.notNull(alumno, "El alumno no puede ser nulo");

		tarjetaDeCredito.setAlumno(alumno);
		return this.save(tarjetaDeCredito);
	}
}
