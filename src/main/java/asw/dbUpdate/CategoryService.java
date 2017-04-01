package asw.dbUpdate;

import asw.dbUpdate.model.Category;

public interface CategoryService {
	Category getCategoryById(Long id);
	Category getCategoryByName(String name);
	
	void saveCategory(Category category);
}
