package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.model.SuggestionState;

public interface SuggestionService {
	
	List<Suggestion> getAllSuggestions();
	List<Suggestion> getVotables();
	List<Suggestion> getSuggestionByTitle(String titulo);
	List<Suggestion> getSuggestionByStatus(SuggestionState estado);
	Suggestion getSuggestionById(Long id);
	
	void saveSuggestion(Suggestion suggestion);
	void deleteSuggestion(Suggestion suggestion);
}
