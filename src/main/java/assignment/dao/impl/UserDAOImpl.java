package assignment.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assignment.dao.UserDAO;
import assignment.entity.User;
import assignment.utils.XJdbc;

public class UserDAOImpl implements UserDAO {
	private User readEntity(ResultSet rs) throws SQLException {
		User entity = new User();
		entity.setId(rs.getString("id"));
		entity.setPassword(rs.getString("password"));
		entity.setFullname(rs.getString("fullname"));
		entity.setBirthday(rs.getDate("birthday"));
		entity.setGender(rs.getBoolean("gender"));
		entity.setMobile(rs.getString("mobile"));
		entity.setEmail(rs.getString("email"));
		entity.setRole(rs.getBoolean("role"));
		return entity;
	}
	
	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_Users";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				User user = this.readEntity(rs);
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User findById(String id) {
		try {
			String sql = "SELECT * FROM J3_Users WHERE Id=?";
			Object[] values = { id };
			ResultSet rs = XJdbc.executeQuery(sql, values);
			if (rs.next()) {
				User user = this.readEntity(rs);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM J3_Users WHERE Id=?";
		Object[] values = { id };
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void update(User entity) {
		String sql = "UPDATE J3_Users " + " SET Password=?, Fullname=?, Birthday=?, Gender=?, Mobile=?, Email=?, Role=?"
				+ " WHERE Id=?";
		Object[] values = { 
				entity.getPassword(), 
				entity.getFullname(), 
				entity.getBirthday(), 
				entity.isGender(),
				entity.getMobile(), 
				entity.getEmail(), 
				entity.isRole(), 
				entity.getId() 
		};
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void create(User entity) {
		String sql = "INSERT INTO J3_Users(Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) "
				+ "    VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] values = { 
				entity.getId(), 
				entity.getPassword(), 
				entity.getFullname(), 
				entity.getBirthday(), 
				entity.isGender(),
				entity.getMobile(), 
				entity.getEmail(), 
				entity.isRole() 
		};
		XJdbc.executeUpdate(sql, values);
	}
}
