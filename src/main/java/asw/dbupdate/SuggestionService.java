package asw.dbupdate;

import java.util.List;

import asw.dbupdate.model.Suggestion;
import asw.dbupdate.model.SuggestionState;

public interface SuggestionService {
	
	List<Suggestion> getAllSuggestions();
	List<Suggestion> getVotables();
	List<Suggestion> getSuggestionByTitle(String titulo);
	List<Suggestion> getSuggestionByStatus(SuggestionState estado);
	Suggestion getSuggestionById(Long id);
	
	Suggestion saveSuggestion(Suggestion suggestion);
	void deleteSuggestion(Suggestion suggestion);
}
