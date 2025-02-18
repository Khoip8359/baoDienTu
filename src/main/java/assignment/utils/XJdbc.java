package assignment.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class XJdbc {
	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dburl="jdbc:sqlserver://localhost:1433;encrypt=false;database=ASM_Java3";
	private static String username="sa";
	private static String password="khoi9988";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static int executeUpdate(String sql, Object... values) {
		try {
			PreparedStatement statement = getPreparedStatement(sql, values);
			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ResultSet executeQuery(String sql, Object... values) {
		try {
			PreparedStatement statement = getPreparedStatement(sql, values);
			ResultSet resultSet = statement.executeQuery();
			return resultSet;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static PreparedStatement getPreparedStatement(String sql, Object... values) {
		try {
			Connection connection = DriverManager.getConnection(dburl, username, password);
			PreparedStatement statement = connection.prepareStatement(sql);
			for(int i=0;i<values.length;i++) {
				statement.setObject(i+1, values[i]);
			}
			return statement;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}