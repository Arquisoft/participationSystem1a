package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.ParticipantService;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.repository.ParticipantRepository;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	private ParticipantRepository repository;

	@Autowired
	public ParticipantServiceImpl(ParticipantRepository repository) {
		this.repository = repository;
	}

	@Override
	public Participant getParticipant(String email, String password) {
		Participant participant = repository.findByEmailAndPassword(email, password);
		return participant;
	}
}
