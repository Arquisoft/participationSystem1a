package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.SaveSuggestion;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.repository.SuggestionRepository;

@Service
public class SaveSuggestionImpl implements SaveSuggestion {
	@Autowired
	SuggestionRepository repository;

	@Override
	public void saveSuggestion(Suggestion suggestion) {
		repository.save(suggestion);
	}
}
