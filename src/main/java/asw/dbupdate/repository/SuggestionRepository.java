package asw.dbupdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbupdate.model.Suggestion;
import asw.dbupdate.model.SuggestionState;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Long> {
	List<Suggestion> findAll();
	List<Suggestion> findByEstadoOrderByPopularidadDesc(SuggestionState ss);
	List<Suggestion> findByTitulo(String titulo);
	List<Suggestion> findByEstado(SuggestionState estado);

}
