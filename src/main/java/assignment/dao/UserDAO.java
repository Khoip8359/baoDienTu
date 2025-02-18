package assignment.dao;

import java.util.List;

import assignment.entity.User;

public interface UserDAO {
	List<User> findAll();
	User findById(String id);
	void deleteById(String id);
	void update(User entity);
	void create(User entity);
}
