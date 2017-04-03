package asw.dbupdate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbupdate.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findByName(String name);
	List<Category> findAll();
}
