package asw.dbUpdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import asw.dbUpdate.model.Word;

public interface WordRepository extends CrudRepository<Word, Long>{
	List<Word> findAll();
}
