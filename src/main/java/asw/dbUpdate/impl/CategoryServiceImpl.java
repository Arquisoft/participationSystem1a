package asw.dbUpdate.impl;

import org.springframework.beans.factory.annotation.Autowired;

import asw.dbUpdate.CategoryService;
import asw.dbUpdate.model.Category;
import asw.dbUpdate.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public Category getCategoryById(Long id) {
		return repository.findOne(id);
	}

}
