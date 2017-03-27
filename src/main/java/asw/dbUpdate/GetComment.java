package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Comment;

public interface GetComment {

	public List<Comment> getAllComments();
	public Comment getCommentsByUser(Long idUser);
	public Comment getCommentsBySuggestion(Long idSuggestion);

}
