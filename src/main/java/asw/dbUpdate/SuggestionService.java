package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Suggestion;

public interface SuggestionService {
	
	List<Suggestion> getAllSuggestions(); //Este sería por popularidad, no está acabado
	List<Suggestion> getSuggestionByTitle(String titulo);
	Suggestion getSuggestionById(Long id);
	
	void saveSuggestion(Suggestion suggestion);
}
