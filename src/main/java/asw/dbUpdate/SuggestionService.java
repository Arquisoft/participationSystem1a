package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Suggestion;

public interface SuggestionService {
	
	List<Suggestion> getAllSuggestions();
	List<Suggestion> getVotables();
	List<Suggestion> getSuggestionByTitle(String titulo);
	Suggestion getSuggestionById(Long id);
	
	void saveSuggestion(Suggestion suggestion);
}
