package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;

import asw.dbUpdate.SaveComment;
import asw.dbUpdate.model.Comment;
import asw.dbUpdate.repository.CommentRepository;

public class SaveCommentImpl implements SaveComment{

	@Autowired
	CommentRepository repository;
	@Override
	public void saveComment(Comment comment) {
		repository.save(comment);
	}
}
