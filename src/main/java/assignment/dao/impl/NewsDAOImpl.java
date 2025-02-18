package assignment.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assignment.dao.NewsDAO;
import assignment.entity.News;
import assignment.utils.XJdbc;

public class NewsDAOImpl implements NewsDAO {
	private News readEntity(ResultSet rs) throws SQLException {
		News entity = new News();
		entity.setId(rs.getString("id"));
		entity.setTitle(rs.getString("title"));
		entity.setContent(rs.getString("content"));
		entity.setImage(rs.getString("image"));
		entity.setPostedDate(rs.getDate("postedDate"));
		entity.setAuthor(rs.getString("author"));
		entity.setViewCount(rs.getInt("viewCount"));
		entity.setCategoryId(rs.getString("categoryId"));
		entity.setHome(rs.getBoolean("home"));
		return entity;
	}
	
	private News readEntity100Char(ResultSet rs) throws SQLException {
		News entity = new News();
		entity.setId(rs.getString("id"));
		entity.setTitle(rs.getString("title"));
		entity.setContent(rs.getString("content").substring(0,100)+"...");
		entity.setImage(rs.getString("image"));
		entity.setPostedDate(rs.getDate("postedDate"));
		entity.setAuthor(rs.getString("author"));
		entity.setViewCount(rs.getInt("viewCount"));
		entity.setCategoryId(rs.getString("categoryId"));
		entity.setHome(rs.getBoolean("home"));
		return entity;
	}
	
	@Override
	public List<News> findAll() {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_News";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public News findById(String id) {
		try {
			String sql = "SELECT * FROM J3_News WHERE Id=?";
			Object[] values = { id };
			ResultSet rs = XJdbc.executeQuery(sql, values);
			if (rs.next()) {
				News entity = this.readEntity(rs);
				return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM J3_News WHERE Id=?";
		Object[] values = { id };
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void update(News entity) {
		String sql = "UPDATE J3_News " 
				+ " SET Title=?, Content=?, Image=?, PostedDate=?, Author=?, ViewCount=?, CategoryId=?, Home=?"
				+ " WHERE Id=?";
		Object[] values = { 
				entity.getTitle(), 
				entity.getContent(), 
				entity.getImage(), 
				entity.getPostedDate(), 
				entity.getAuthor(),
				entity.getViewCount(), 
				entity.getCategoryId(), 
				entity.isHome(), 
				entity.getId() 
		};
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public void create(News entity) {
		String sql = "INSERT INTO J3_News(Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) "
				+ "    VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] values = { 
				entity.getId(),
				entity.getTitle(), 
				entity.getContent(), 
				entity.getImage(), 
				entity.getPostedDate(), 
				entity.getAuthor(),
				entity.getViewCount(), 
				entity.getCategoryId(), 
				entity.isHome()
		};
		XJdbc.executeUpdate(sql, values);
	}

	@Override
	public List<News> findByCategoryID(String cateId) {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_News where CategoryId=?";
			Object[] values = {cateId};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> findTop5HotNews() {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT top(5) *  FROM J3_News order by viewCount DESC";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> findTop5LastestNews() {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT top(5) *  FROM J3_News order by PostedDate DESC";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> findHomeNews() {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT *  FROM J3_News where home =?";
			Object[] values = {true};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> findAll100Char() {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_News";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity100Char(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> findByAuthorId(String id) {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_News where author=?";
			Object[] values = {id};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity100Char(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> findByIds(String[] ids) {
		List<News> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM J3_News where id in('"+String.join("','", ids)+"')";
			Object[] values = {};
			ResultSet rs = XJdbc.executeQuery(sql, values);
			while (rs.next()) {
				News entity = this.readEntity(rs);
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
