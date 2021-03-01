package app.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.book;

public class bookDAO { //my book - data access objects (should have name it to libraryitemDAO)
	
	private static String URL = "jdbc:mysql://localhost:3306/appconsid?useSSL=false"; //DB URL 
	private static String username = "root";
	private static String password = "password";
	
	//all the necessary sql querys to manage my database 
	//used in functions 
	
	private static final String STORE_BOOK_SQL = "INSERT INTO LIBRARYITEM" + " (CATEGORYID, TITLE, AUTHOR, PAGES, RUNTIMEMINUTES, TYPE, ISBORROWABLE) VALUES " + " (?, ?, ?, ?, ?, ?, true);";
	private static final String SELECT_BOOK_SQL = "select ID,TITLE,AUTHOR,PAGES,RUNTIMEMINUTES,TYPE,ISBORROWABLE,BORROWER,BORROWDATE from LIBRARYITEM where ID=?;";
	private static final String SELECT_ALL_BOOKS_SQL = "select * from LIBRARYITEM where CATEGORYID=?;";
	private static final String DELETE_BOOK_SQL = "delete from LIBRARYITEM where ID=?;";
	private static final String UPDATE_BOOK_SQL = "update LIBRARYITEM set TITLE = ?, AUTHOR = ?, PAGES = ?, RUNTIMEMINUTES = ?, TYPE = ? WHERE ID = ?;";
	private static final String UPDATE_BOOK_BORROWER_SQL = "update LIBRARYITEM set ISBORROWABLE = ?, BORROWER = ?,BORROWDATE = current_date() WHERE ID = ?;";
	private static final String UNSET_BORROWER_SQL = "UPDATE LIBRARYITEM SET borrower = null, BORROWDATE = NULL, ISBORROWABLE = true WHERE ID=?;";
	
	
	//database connection
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
	
	
//skapa eller ta bort bok
	
	public static void insertBook(book book, int idcategory) throws SQLException {
		try(Connection con = getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(STORE_BOOK_SQL)){ //set each ? in my storebooksql statement by (index,variable)
			preparedStatement.setInt(1, idcategory);
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setInt(4, book.getPages());
			preparedStatement.setInt(5, book.getRuntimeminutes());
			preparedStatement.setString(6, book.getType());
		
			preparedStatement.executeUpdate();	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

		
//uppdatera bok
	
	public static boolean updateBook(book book) throws SQLException {
		if (book.getAuthor() == null && book.getTitle() == null && book.getPages() == 0) { //this if condition is to see if the user wants to input a new/edit borrower or just update a book
			boolean rowUpdate;
			try(Connection con = getConnection();
					PreparedStatement statement = con.prepareStatement(UPDATE_BOOK_BORROWER_SQL)){
				statement.setBoolean(1, book.isIsborrowable());
				statement.setString(2, book.getBorrower());
				
				statement.setInt(3, book.getId());
				rowUpdate = statement.executeUpdate() > 0;	
				}
			return rowUpdate;
		}	
		else {// if its not a update of the borrower then it is a update of the libraryitem
			boolean rowUpdate;
			try(Connection con = getConnection();
					PreparedStatement statement = con.prepareStatement(UPDATE_BOOK_SQL)){
				statement.setString(1, book.getTitle());
				statement.setString(2, book.getAuthor());
				statement.setInt(3, book.getPages());
				statement.setInt(4, book.getRuntimeminutes());
				statement.setString(5, book.getType());
				
				statement.setInt(6, book.getId());
						
				rowUpdate = statement.executeUpdate() > 0;	
				}
			return rowUpdate;	
		}
	}


//select book med id
	public static book selectBook(int id) {
		book book = null;
		//steg 1 = connection
		try(Connection con = getConnection();
				//steg2 = create statement with connection object
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_BOOK_SQL);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			//steg3 = exekvera sql:n eller uppdatera
			ResultSet rs = preparedStatement.executeQuery();
				
			//steg 4 process the resultset object
			while(rs.next()) {
				String title = rs.getString("title");
				String author = rs.getString("author");
				int pages = rs.getInt("pages");
				int runtimeminutes = rs.getInt("runtimeminutes");
				String type = rs.getString("type");
				Boolean isborrowable = rs.getBoolean("isborrowable");
				String borrower = rs.getString("borrower");
				Date borrowdate = rs.getDate("borrowdate");
				
				book = new book(id,title,author,pages,runtimeminutes,type,isborrowable,borrower,borrowdate);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return book;	
	}		
	
	
		
	//selecta alla o placera i en array
	public static List<book> selectAllBooks(int id2) {
			
		List<book> books = new ArrayList<>();
		//steg 1 = connection
		try(Connection con = getConnection();
				//steg2 = skapa statement med connection object
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_BOOKS_SQL);){
			preparedStatement.setInt(1, id2);
			System.out.println(preparedStatement);
			//steg3 = exekvera sql:n eller uppdatera
			ResultSet rs = preparedStatement.executeQuery();
				
			//steg 4 process the resultset object
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int pages = rs.getInt("pages");
				int runtimeminutes = rs.getInt("runtimeminutes");
				String type = rs.getString("type");
				Boolean isborrowable = rs.getBoolean("isborrowable");
				String borrower = rs.getString("borrower"); 
				Date borrowdate = rs.getDate("borrowdate");
				
				//if (isborrowable) {
					//books.add(new book(id,title,author,pages,type,isborrowable));
				//}else {
				books.add(new book(id,title,author,pages,runtimeminutes,type,isborrowable,borrower,borrowdate));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return books;	
	}		
		
	//ta bort en hel rad
	
	public static boolean deleteBook(int id) throws SQLException{
		boolean rowDeleted;
		try (Connection con = getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_BOOK_SQL);){
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;		
			}
		return rowDeleted;
	}
	
	//unset date and borrower variables if the book is borrowable 
	public static boolean unsetBorrower(int id) throws SQLException{
		boolean borrowerUpdate;
		try(Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement(UNSET_BORROWER_SQL)){
			statement.setInt(1, id);
			borrowerUpdate = statement.executeUpdate() > 0;	
			}
		return borrowerUpdate;
	}


}
