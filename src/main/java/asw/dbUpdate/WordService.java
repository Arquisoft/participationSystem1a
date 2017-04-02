package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Word;

public interface WordService {
	List<Word> getAllWords();
	
	void saveWord(Word word);
	void deleteWord(Word word);
}
