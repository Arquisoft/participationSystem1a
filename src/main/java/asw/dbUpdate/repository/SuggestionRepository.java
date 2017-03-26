package asw.dbUpdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Suggestion;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Long>{
	Suggestion save(Suggestion suggestion);
	List<Suggestion> findAll();
	List<Suggestion> findByTitulo(String titulo);
}
