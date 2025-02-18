package assignment.dao;

import java.util.List;

import assignment.entity.Category;
import assignment.entity.News;

public interface CategoryDAO {
	List<Category> findAll();
	Category findById(String id);
	void deleteById(String id);
	void update(Category Category);
	void create(Category Category);
	List<Category> findByCategoryId(String categoryID);
}
