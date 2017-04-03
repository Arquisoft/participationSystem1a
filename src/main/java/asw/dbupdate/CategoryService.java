package asw.dbupdate;

import java.util.List;

import asw.dbupdate.model.Category;

public interface CategoryService {
	Category getCategoryById(Long id);
	Category getCategoryByName(String name);
	List<Category> getAllCategories();
	
	Category saveCategory(Category category);
	void deleteCategory(Category categoria);
}
