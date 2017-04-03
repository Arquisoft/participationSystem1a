package asw.dbUpdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbUpdate.CategoryService;
import asw.dbUpdate.model.Category;
import asw.dbUpdate.repository.CategoryRepository;

@Service
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
	public List<Category> getAllCategories(){
		return repository.findAll();
	}

	@Override
	public Category saveCategory(Category category) {
		return repository.save(category);
	}

	@Override
	public void deleteCategory(Category categoria) {
		repository.delete(categoria);		
	}
}
