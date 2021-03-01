package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.Category;
import app.model.book;

public class CategoryDAO { //my category data access object class, used to interact with the database
	
	private static String URL = "jdbc:mysql://localhost:3306/appconsid?useSSL=false"; //DB URL 
	private static String username = "root";
	private static String password = "password";
	
	private static final String STORE_CATEGORY_SQL = "INSERT INTO CATEGORY" + " (CATEGORYNAME) VALUES " + " (?);";
	private static final String SELECT_CATEGORY = "select ID,CATEGORYNAME from CATEGORY where ID=?;";
	private static final String SELECT_ALL_CATEGORY = "select * from CATEGORY";
	private static final String DELETE_CATEGORY = "delete from CATEGORY where ID=?;";
	private static final String UPDATE_CATEGORY = "update CATEGORY set CATEGORYNAME = ? WHERE ID = ?;";
	
	protected static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, username, password);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	
	
	public static void insertCategory(Category category) throws SQLException {
		try(Connection con = getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(STORE_CATEGORY_SQL)){
			preparedStatement.setString(1, category.getCategoryname());
			preparedStatement.executeUpdate();	
		}catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	
	public static boolean updateCategory(Category category) throws SQLException {
		boolean rowUpdate;
		try(Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement(UPDATE_CATEGORY)){
			statement.setString(1, category.getCategoryname());
			statement.setInt(2, category.getId());
					
			rowUpdate = statement.executeUpdate() > 0;	
			}
		return rowUpdate;	
		}
	
	
	public static Category selectCategory(int id) {
		Category category = null;
		
		try(Connection con = getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_CATEGORY);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement); //just to see if the query executes
			ResultSet rs = preparedStatement.executeQuery();
				
			while(rs.next()) {
				String categoryname = rs.getString("categoryname");
				
				category = new Category(id,categoryname);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return category;	
	}	
	
	
	public static List<Category> selectAllCategorys() {
		
		List<Category> category = new ArrayList<>();
		
		try(Connection con = getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CATEGORY);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String categoryname = rs.getString("categoryname");
				category.add(new Category(id,categoryname));	
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return category;	
	}	
	
	public static boolean deleteCategory(int id) throws SQLException{
		boolean rowDeleted;
		try (Connection con = getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_CATEGORY);){
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;		
			}
		return rowDeleted;
		}
}
