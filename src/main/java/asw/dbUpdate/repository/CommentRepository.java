package asw.dbUpdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Comment;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByUser(Participant user);
	List<Comment> findBySuggestion(Suggestion Suggestion);
}
