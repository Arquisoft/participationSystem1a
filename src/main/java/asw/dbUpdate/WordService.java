package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Word;

public interface WordService {
	List<Word> getAllWords();
	Word getWordByName(String word);
	
	void saveWord(Word word);
	void deleteWord(Word word);
}
