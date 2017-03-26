package asw.dbUpdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.dbUpdate.GetSuggestion;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.repository.SuggestionRepository;

public class GetSuggestionImpl implements GetSuggestion{

	@Autowired
	private SuggestionRepository repository;
	@Override
	public List<Suggestion> getSuggestion() {
		return repository.findAll();
	}
	@Override
	public Suggestion getSuggestionByTitle(String titulo) {
		return repository.findByTitulo(titulo);
	}
	
}
