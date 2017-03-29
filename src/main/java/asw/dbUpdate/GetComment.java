package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Comment;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;

public interface GetComment {

	List<Comment> getAllComments();
	List<Comment> getCommentsByParticipant(Participant participant);
	List<Comment> getCommentsBySuggestion(Suggestion suggestion);

}
