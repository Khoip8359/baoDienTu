package assignment.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assignment.dao.CategoryDAO;
import assignment.entity.Category;
import assignment.entity.News;
import assignment.utils.XJdbc;

public class CategoryDAOImpl implements CategoryDAO {
	private Category readEntity(ResultSet rs) throws SQLException {
		Category entity = new Category();
		entity.setId(rs.getString("id"));
		entity.setName(rs.getString("name"));
		return entity;
	}
	
	@Override
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_Categories";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				Category entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Category findById(String id) {
		try {
			String sql = "SELECT * FROM J3_Categories WHERE Id=?";
			Object[] values = { id };
			ResultSet rs = XJdbc.executeQuery(sql, values);
			if (rs.next()) {
				Category entity = this.readEntity(rs);
				return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM J3_Categories WHERE Id=?";
		Object[] values = { id };
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void update(Category entity) {
		String sql = "UPDATE J3_Categories SET Name=? WHERE Id=?";
		Object[] values = { entity.getName(), entity.getId()};
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void create(Category entity) {
		String sql = "INSERT INTO J3_Categories(Id, Name) VALUES(?, ?)";
		Object[] values = { entity.getId(), entity.getName() };
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public List<Category> findByCategoryId(String categoryID) {
		// TODO Auto-generated method stub
		return null;
	}
}