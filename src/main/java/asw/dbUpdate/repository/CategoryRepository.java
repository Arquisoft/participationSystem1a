package asw.dbUpdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbUpdate.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByName(String name);
	
}
