package asw.dbUpdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.WordService;
import asw.dbUpdate.model.Word;
import asw.dbUpdate.repository.WordRepository;

@Service
public class WordServiceImpl implements WordService{

	private WordRepository repository;
	
	@Autowired
	public WordServiceImpl(WordRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Word> getAllWords() {
		return repository.findAll();
	}

	@Override
	public void saveWord(Word word) {
		repository.save(word);
	}

	@Override
	public void deleteWord(Word word) {
		repository.delete(word);	
	}

	@Override
	public Word getWordByName(String name) {
		return repository.findByName(name);
	}
}
