package asw.dbUpdate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import asw.dbUpdate.model.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long>{
	void insertSuggestion(Suggestion suggestion);
	SuggestionRepository listRepository();
}
