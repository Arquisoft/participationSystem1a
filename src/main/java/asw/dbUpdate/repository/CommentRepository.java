package asw.dbUpdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	Comment save(Comment comment);
	Comment getAll();
	Comment findById_user(Long idUser);
	Comment findById_suggestion(Long idSuggestion);
}
