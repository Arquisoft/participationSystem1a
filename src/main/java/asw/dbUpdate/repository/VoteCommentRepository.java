package asw.dbUpdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import asw.dbUpdate.model.VoteComment;
import asw.dbUpdate.model.keys.VoteCommentKey;

@Repository
public interface VoteCommentRepository extends CrudRepository<VoteComment, VoteCommentKey>{

}
