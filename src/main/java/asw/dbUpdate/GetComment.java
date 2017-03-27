package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Comment;

public interface GetComment {

	public List<Comment> getAllComments();
	public List<Comment> getCommentsByUser(Long idUser);
	public List<Comment> getCommentsBySuggestion(Long idSuggestion);

}
