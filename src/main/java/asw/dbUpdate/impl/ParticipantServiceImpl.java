package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import asw.dbUpdate.ParticipantService;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.model.VoteSuggestion;
import asw.dbUpdate.repository.ParticipantRepository;
import asw.dbUpdate.repository.SuggestionRepository;
import asw.dbUpdate.repository.VoteCommentRepository;
import asw.dbUpdate.repository.VoteSuggestionRepository;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	private ParticipantRepository pr;
	private VoteCommentRepository vcr; // Lo añado ya aqui para el que haga lo
										// comentarios y tenerlo todo unificado
	private VoteSuggestionRepository vsr;
	private SuggestionRepository sr;

	@Autowired
	public ParticipantServiceImpl(ParticipantRepository pr, VoteCommentRepository vcr, VoteSuggestionRepository vsr,
			SuggestionRepository sr) {
		this.pr = pr;
		this.vcr = vcr;
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
	public void supportSuggestion(Long participant, Long suggestion) {
		Participant part = pr.findOne(participant);
		Suggestion sugg = sr.findOne(suggestion);
		VoteSuggestion vote = new VoteSuggestion(part, sugg);
		vsr.save(vote);
	}
}
