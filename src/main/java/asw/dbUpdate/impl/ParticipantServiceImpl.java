package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import asw.dbUpdate.ParticipantService;
import asw.dbUpdate.model.Comment;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.model.SuggestionState;
import asw.dbUpdate.model.VoteComment;
import asw.dbUpdate.model.VoteSuggestion;
import asw.dbUpdate.model.keys.VoteCommentKey;
import asw.dbUpdate.model.keys.VoteSuggestionKey;
import asw.dbUpdate.repository.CommentRepository;
import asw.dbUpdate.repository.ParticipantRepository;
import asw.dbUpdate.repository.SuggestionRepository;
import asw.dbUpdate.repository.VoteCommentRepository;
import asw.dbUpdate.repository.VoteSuggestionRepository;

//CADA VEZ QUE SE CREE UN OBJETO NUEVO SE DEBE HACER DENTRO DE UN METODO TRANSACTIONAL DENTRO DE UN SERVICE 
//PARA PODER USAR LA CLASE ASSOCIATIONS

@Service
public class ParticipantServiceImpl implements ParticipantService {

	private ParticipantRepository pr;
	private VoteSuggestionRepository vsr;
	private SuggestionRepository sr;
	private VoteCommentRepository vcr;
	private CommentRepository cr;

	@Autowired
	public ParticipantServiceImpl(ParticipantRepository pr, VoteSuggestionRepository vsr, SuggestionRepository sr,
			CommentRepository cr, VoteCommentRepository vcr) {
		this.pr = pr;
		this.vsr = vsr;
		this.sr = sr;
		this.cr = cr;
		this.vcr = vcr;
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

	@Override
	@Transactional
	public boolean supportCommentPositive(Long participant, Long comment) {
		Participant part = pr.findOne(participant);
		Comment com = cr.findOne(comment);
		if (!vcr.exists(new VoteCommentKey(part.getId(), com.getId()))) {
			VoteComment vote = new VoteComment(part, com);
			com.incrementPositiveVotes();
			vcr.save(vote);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean supportCommentNegative(Long participant, Long comment) {
		Participant part = pr.findOne(participant);
		Comment com = cr.findOne(comment);
		if (!vcr.exists(new VoteCommentKey(part.getId(), com.getId()))) {
			VoteComment vote = new VoteComment(part, com);
			com.incrementateNegativeVotes();
			vcr.save(vote);
			return true;
		}
		return false;
	}
}
