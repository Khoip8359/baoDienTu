package assignment.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assignment.dao.NewsletterDAO;
import assignment.entity.Newsletter;
import assignment.utils.XJdbc;

public class NewsletterDAOImpl implements NewsletterDAO {
	private Newsletter readEntity(ResultSet rs) throws SQLException {
		Newsletter entity = new Newsletter();
		entity.setEmail(rs.getString("email"));
		entity.setEnable(rs.getBoolean("enabled"));
		return entity;
	}
	
	@Override
	public List<Newsletter> findAll() {
		List<Newsletter> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_Newsletters";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				Newsletter entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Newsletter findByEmail(String email) {
		try {
			String sql = "SELECT * FROM J3_Newsletters WHERE email=?";
			Object[] values = { email };
			ResultSet rs = XJdbc.executeQuery(sql, values);
			if (rs.next()) {
				Newsletter entity = this.readEntity(rs);
				return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM J3_Newsletters WHERE Id=?";
		Object[] values = { id };
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void update(Newsletter entity) {
		String sql = "UPDATE J3_Newsletters SET Enanled=? WHERE Email=?";
		Object[] values = { entity.isEnable(), entity.getEmail()};
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void create(Newsletter entity) {
		String sql = "INSERT INTO J3_Newsletters(Email, Enabled) VALUES(?, ?)";
		Object[] values = { entity.getEmail(), entity.isEnable()};
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public List<String> findAllEnable() {
		List<String> list = new ArrayList<>();
		String sql = "select * from J3_Newsletters where enabled =1";
		Object[] values = {};
		ResultSet rs = XJdbc.executeQuery(sql,values);
		try {
			while(rs.next()) {
				String temp = rs.getString("email");
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}