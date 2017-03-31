package asw.dbUpdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.VoteSuggestion;
import asw.dbUpdate.model.keys.VoteSuggestionKey;

@Repository
public interface VoteSuggestionRepository extends CrudRepository<VoteSuggestion, VoteSuggestionKey>{

}
