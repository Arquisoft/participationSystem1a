package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Comment;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;

public interface GetComment {

	List<Comment> getAllComments();
	List<Comment> getCommentsByUser(Participant user);
	List<Comment> getCommentsBySuggestion(Suggestion suggestion);

}
