package asw.dbUpdate;

import java.util.List;

import asw.dbUpdate.model.Category;

public interface CategoryService {
	Category getCategoryById(Long id);
	Category getCategoryByName(String name);
	List<Category> getAllCategories();
	
	void saveCategory(Category category);
	void deleteCategory(Category categoria);
}
