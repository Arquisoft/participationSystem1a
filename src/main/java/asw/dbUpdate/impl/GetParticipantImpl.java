package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.GetParticipant;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.repository.ParticipantRepository;

@Service
public class GetParticipantImpl implements GetParticipant {

	@Autowired
	private ParticipantRepository repository;

	@Override
	public Participant getParticipant(String email) {
		Participant participant = repository.findByEmail(email);
		return participant;
	}
}
