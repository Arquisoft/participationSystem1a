package asw.dbUpdate.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.GetSuggestion;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.repository.SuggestionRepository;

@Service
public class GetSuggestionImpl implements GetSuggestion {

	@Autowired
	private SuggestionRepository repository;

	@Override
	public List<Suggestion> getAllSuggestions() {
		return repository.findAll();
	}

	@Override
	public List<Suggestion> getSuggestionByTitle(String titulo) {
		return repository.findByTitulo(titulo);
	}

	@Override
	public Suggestion getSuggestionById(Long id) {
		return repository.findOne(id);
	}

}
