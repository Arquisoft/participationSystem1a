package asw.dbupdate;

import java.util.List;

import asw.dbupdate.model.Comment;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;

public interface CommentService {

	List<Comment> getAllComments();

	List<Comment> getCommentsByParticipant(Participant participant);

	List<Comment> getCommentsBySuggestion(Suggestion suggestion);

	Comment saveComment(Comment comment);

}
