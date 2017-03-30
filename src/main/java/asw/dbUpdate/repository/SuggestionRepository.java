package asw.dbUpdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Suggestion;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Long>{
	List<Suggestion> findAllOrderByPopularidadDesc();
	List<Suggestion> findByTitulo(String titulo);
}
