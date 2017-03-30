package asw.dbUpdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.CommentService;
import asw.dbUpdate.model.Comment;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.repository.CommentRepository;

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
	public void saveComment(Comment comment) {
		repository.save(comment);
	}
}
