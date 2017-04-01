package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;

import asw.dbUpdate.CategoryService;
import asw.dbUpdate.model.Category;
import asw.dbUpdate.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}

	@Override
	public Category getCategoryById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Category getCategoryByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void saveCategory(Category category) {
		repository.save(category);		
	}

}
