package asw.dbUpdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.model.SuggestionState;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Long> {
	List<Suggestion> findAll();
	List<Suggestion> findByEstadoOrderByPopularidadDesc(SuggestionState ss);
	List<Suggestion> findByTitulo(String titulo);
	List<Suggestion> findByEstado(SuggestionState estado);

}
