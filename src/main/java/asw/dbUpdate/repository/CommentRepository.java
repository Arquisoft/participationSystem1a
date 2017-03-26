package asw.dbUpdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import asw.dbUpdate.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	void insertComment(Comment comment);
}
