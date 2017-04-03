package asw.dbupdate;

import java.util.List;

import asw.dbupdate.model.Word;

public interface WordService {
	List<Word> getAllWords();
	Word getWordByName(String word);
	
	void saveWord(Word word);
	void deleteWord(Word word);
}
