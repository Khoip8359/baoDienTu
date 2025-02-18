package assignment.dao;

import java.util.List;

import assignment.entity.Newsletter;

public interface NewsletterDAO {
	List<Newsletter> findAll();
	Newsletter findByEmail(String email);
	List<String> findAllEnable();
	void deleteById(String id);
	void update(Newsletter Newsletter);
	void create(Newsletter Newsletter);
}
