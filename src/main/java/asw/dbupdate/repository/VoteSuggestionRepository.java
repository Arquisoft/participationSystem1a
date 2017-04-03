package asw.dbupdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbupdate.model.VoteSuggestion;
import asw.dbupdate.model.keys.VoteSuggestionKey;

@Repository
public interface VoteSuggestionRepository extends CrudRepository<VoteSuggestion, VoteSuggestionKey>{

}
