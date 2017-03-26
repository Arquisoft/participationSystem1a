package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.dbUpdate.GetParticipant;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.repository.ParticipantRepository;

@Component
public class GetParticipantImpl implements GetParticipant{

	@Autowired
	private ParticipantRepository repository;
	@Override
	public Participant getParticipant(String email) {
		Participant participant = repository.findByEmail(email);
		return participant;
	}
}
