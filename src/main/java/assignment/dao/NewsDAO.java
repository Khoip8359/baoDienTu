package assignment.dao;

import java.util.List;

import assignment.entity.News;


public interface NewsDAO {
	List<News> findAll();
	List<News> findAll100Char();
	News findById(String id);
	void deleteById(String id);
	void update(News News);
	void create(News News);
	List<News> findByCategoryID(String cateId);
	List<News> findTop5HotNews();
	List<News> findTop5LastestNews();
	List<News> findHomeNews();
	List<News> findByAuthorId(String id);
	List<News> findByIds(String[] ids);
}
