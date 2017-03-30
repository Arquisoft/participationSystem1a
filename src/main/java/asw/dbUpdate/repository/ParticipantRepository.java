package asw.dbUpdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Participant;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

	/**
	 * Método que devuelve el Participante el cual es buscado por email en la
	 * base de datos
	 * 
	 * @param email
	 *            del Partipante
	 * @return El Participante con dicho email
	 */
	public Participant findByEmailAndPassword(String email, String password);
}