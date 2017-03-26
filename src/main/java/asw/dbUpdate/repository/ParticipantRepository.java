package asw.dbUpdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	/**
	 * Método que devuelve el Participante el cual es buscado por email en la
	 * base de datos
	 * 
	 * @param email
	 *            del Partipante
	 * @return El Participante con dicho email
	 */
	public ParticipantRepository findByEmail(String email);
}