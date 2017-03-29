package asw.dbUpdate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	@Query("select p from Comment p where id_user=?1")
	List<Comment> findById_user(Long id_User);
	@Query("select p from Comment p where id_suggestion=?1")
	List<Comment> findById_suggestion(Long id_Suggestion);
}
