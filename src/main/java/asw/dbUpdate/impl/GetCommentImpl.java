package asw.dbUpdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.GetComment;
import asw.dbUpdate.model.Comment;
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
	public List<Comment> getCommentsByUser(Long idUser) {
		return repository.findById_user(idUser);
	}

	@Override
	public List<Comment> getCommentsBySuggestion(Long idSuggestion) {
		return repository.findById_suggestion(idSuggestion);
	}

}
