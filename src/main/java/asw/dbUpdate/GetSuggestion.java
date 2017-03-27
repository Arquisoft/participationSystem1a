package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Suggestion;

public interface GetSuggestion {
	public List<Suggestion> getAllSuggestions(); //Este sería por popularidad, no está acabado
	public List<Suggestion> getSuggestionByTitle(String titulo);
	public Suggestion getSuggestionById(Long id);
}
