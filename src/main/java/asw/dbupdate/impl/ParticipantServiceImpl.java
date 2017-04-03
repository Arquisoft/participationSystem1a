package asw.dbupdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import asw.dbupdate.ParticipantService;
import asw.dbupdate.model.Comment;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;
import asw.dbupdate.model.SuggestionState;
import asw.dbupdate.model.VoteComment;
import asw.dbupdate.model.VoteSuggestion;
import asw.dbupdate.model.keys.VoteCommentKey;
import asw.dbupdate.model.keys.VoteSuggestionKey;
import asw.dbupdate.repository.CommentRepository;
import asw.dbupdate.repository.ParticipantRepository;
import asw.dbupdate.repository.SuggestionRepository;
import asw.dbupdate.repository.VoteCommentRepository;
import asw.dbupdate.repository.VoteSuggestionRepository;

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
			VoteComment vote = new VoteComment(part, com, 1);
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
			VoteComment vote = new VoteComment(part, com, -1);
			vcr.save(vote);
			return true;
		}
		return false;
	}
}
