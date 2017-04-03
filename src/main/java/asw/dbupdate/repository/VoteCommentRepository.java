package asw.dbupdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbupdate.model.VoteComment;
import asw.dbupdate.model.keys.VoteCommentKey;

@Repository
public interface VoteCommentRepository extends CrudRepository<VoteComment, VoteCommentKey>{

}
