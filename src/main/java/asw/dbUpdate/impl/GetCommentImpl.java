package asw.dbUpdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.GetComment;
import asw.dbUpdate.model.Comment;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.repository.CommentRepository;

@Service
public class GetCommentImpl implements GetComment {

	@Autowired
	private CommentRepository repository;

	@Override
	public List<Comment> getAllComments() {
		return (List<Comment>) repository.findAll();
	}

	@Override
	public List<Comment> getCommentsByUser(Participant user) {
		return repository.findByUser(user);
	}

	@Override
	public List<Comment> getCommentsBySuggestion(Suggestion suggestion) {
		return repository.findBySuggestion(suggestion);
	}

}
