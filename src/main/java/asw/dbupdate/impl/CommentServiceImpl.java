package asw.dbupdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbupdate.CommentService;
import asw.dbupdate.model.Comment;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;
import asw.dbupdate.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository repository;

	@Autowired
	public CommentServiceImpl(CommentRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Comment> getAllComments() {
		return (List<Comment>) repository.findAll();
	}

	@Override
	public List<Comment> getCommentsByParticipant(Participant participant) {
		return repository.findByParticipant(participant);
	}

	@Override
	public List<Comment> getCommentsBySuggestion(Suggestion suggestion) {
		return repository.findBySuggestion(suggestion);
	}

	@Override
	public Comment saveComment(Comment comment) {
		return repository.save(comment);
	}
}
