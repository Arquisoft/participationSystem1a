package asw.dbupdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbupdate.model.Comment;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByParticipant(Participant participant);
	List<Comment> findBySuggestion(Suggestion Suggestion);
	
}
