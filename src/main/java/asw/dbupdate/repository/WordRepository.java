package asw.dbupdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbupdate.model.Word;

@Repository
public interface WordRepository extends CrudRepository<Word, Long>{
	List<Word> findAll();
	Word findByName(String name);
}
