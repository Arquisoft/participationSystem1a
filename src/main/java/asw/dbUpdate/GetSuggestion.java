package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Suggestion;

public interface GetSuggestion {
	public List<Suggestion> getSuggestion(); //Este sería por popularidad, no está acabado
	public Suggestion getSuggestionByTitle(String titulo);
}
