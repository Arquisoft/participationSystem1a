package asw.dbUpdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	List<Comment> findById_user(Long idUser);
	List<Comment> findById_suggestion(Long idSuggestion);
}
