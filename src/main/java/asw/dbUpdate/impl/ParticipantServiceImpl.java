package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import asw.dbUpdate.ParticipantService;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.model.SuggestionState;
import asw.dbUpdate.model.VoteSuggestion;
import asw.dbUpdate.model.keys.VoteSuggestionKey;
import asw.dbUpdate.repository.ParticipantRepository;
import asw.dbUpdate.repository.SuggestionRepository;
import asw.dbUpdate.repository.VoteSuggestionRepository;

//CADA VEZ QUE SE CREE UN OBJETO NUEVO SE DEBE HACER DENTRO DE UN METODO TRANSACTIONAL DENTRO DE UN SERVICE 
//PARA PODER USAR LA CLASE ASSOCIATIONS

@Service
public class ParticipantServiceImpl implements ParticipantService {

	private ParticipantRepository pr;
	private VoteSuggestionRepository vsr;
	private SuggestionRepository sr;

	@Autowired
	public ParticipantServiceImpl(ParticipantRepository pr, VoteSuggestionRepository vsr, SuggestionRepository sr) {
		this.pr = pr;
		this.vsr = vsr;
		this.sr = sr;
	}

	@Override
	public Participant getParticipant(String email, String password) {
		Participant participant = pr.findByEmailAndPassword(email, password);
		return participant;
	}

	@Override
	@Transactional
	public boolean supportSuggestion(Long participant, Long suggestion) {
		Participant part = pr.findOne(participant);
		Suggestion sugg = sr.findOne(suggestion);
		if (!vsr.exists(new VoteSuggestionKey(part.getId(), sugg.getId()))) {
			VoteSuggestion vote = new VoteSuggestion(part, sugg);
			vsr.save(vote);
			if (sugg.getPopularidad() >= sugg.getMinVotos())
				sugg.setEstado(SuggestionState.EnVotacion);
			return true;
		}
		return false;
	}
}
