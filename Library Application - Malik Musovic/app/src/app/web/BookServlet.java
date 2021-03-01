package app.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.dao.CategoryDAO;
import app.dao.bookDAO;
import app.model.Category;
import app.model.book;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private bookDAO BookDAO; //need a global variable for the bookDAO class so we can access functions from bookDAO
	public int idcategory; //category id needed to be global sence we want to save the value of the variable when leaving a function so we keep track of which category the user is looking at
	private List<Category> listCategory;
      
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
    	this.BookDAO = new bookDAO();
      
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();
		
		try {
			switch(action) {
			case "/new":
				showNewForm(request,response);
				break;
			case "/insert":
				try {
					insertBook(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/delete":
				try {
					deleteBook(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/edit":
				try {
					showEditForm(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/adddeleteborrower":
				try {
					showBorrowerForm(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;	
			case "/update":
				try {
					updateBook(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/updateborrower":
				try {
					updateborrower(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/newcategory":
					showNewCategoryForm(request,response);
				break;
			case "/editcategory":
				try {
					showCategoryEditForm(request,response);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			break;
			case "/insertcategory":
				try {
					insertCategory(request,response);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			break;
			case "/deletecategory":
				try {
					deleteCategory(request,response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			break;
			case "/updatecategory":
				try {
					updateCategory(request,response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			break;
			case "/listbook":
				try {
					listBook(request,response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			break;
			case "/listbookwithid":
				try {
					listBookWithId(request,response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			break;
			default:
				try {
					listCategory(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//list
				break;
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Bellow is all my functions for category view-----------------------------
	
	private void listCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException{
		
		listCategory = CategoryDAO.selectAllCategorys();
		
		List<Category> listCategorySorted = listCategory.stream() //sorts by categoryname
				  .sorted(Comparator.comparing(Category::getCategoryname))
				  .collect(Collectors.toList());
		
		request.setAttribute("listCategory", listCategorySorted); //send the sorted list to servlet and name it to listbook
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewCategoryForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("categoryForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertCategory(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException{
			//no duplicates allowed 
			String categoryname = request.getParameter("categoryname");
			//categoryList = new ArrayList<Category>(11);
			if(duplicate(categoryname)) { //returnerar true / false om det finns en av samma element
				//response.sendRedirect("list");
				
				String err = "Error: You can not add a category that already exist (No duplicates allowed)";
				request.setAttribute("err", err);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("list");
				try {
					requestDispatcher.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {//if the input is not a duplicate then insert the category to the database
				Category category = new Category(categoryname);
				CategoryDAO.insertCategory(category);
				response.sendRedirect("list");
			}
	}
	
	
	private boolean duplicate(String name) {
		String strLower=name.toLowerCase();//skiljer inte po sm] och stora bokstaver 
		for (Category category : listCategory) {
		    if (category.getCategoryname().toLowerCase().equals(strLower)) {
		    	return true;
		    }
		}
		return false;
	}
	
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException{
				
				int id = Integer.parseInt(request.getParameter("id"));
				List<book> Libraryitems = bookDAO.selectAllBooks(id);
				
				if(Libraryitems.isEmpty()) { //om den ar tom kan du ta bort kategorin
					CategoryDAO.deleteCategory(id);
					response.sendRedirect("list");
				}
				else { //om den inte ar tom kan du ej ta bort den(sql error, cant delete foreign key constraint)
					String err = "You can not delete a category that has library items in it! If you want to delete the category you need to delete the library items in it first.";
					request.setAttribute("err", err);
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("list");
					try {
						requestDispatcher.forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
		}
		
	private void showCategoryEditForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, SQLException, IOException{
			int id = Integer.parseInt(request.getParameter("id"));
			Category existingCategory = CategoryDAO.selectCategory(id);
					
			RequestDispatcher dispatcher = request.getRequestDispatcher("categoryForm.jsp");
			request.setAttribute("category", existingCategory);//gets the id of the category, dispatch to categoryform.jsp and send the selected category variables
			dispatcher.forward(request, response);				
	}
		
	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException{
			
			int id = Integer.parseInt(request.getParameter("id"));
			String categoryname = request.getParameter("categoryname");
	
			Category category = new Category(id,categoryname); 
			CategoryDAO.updateCategory(category);//update category columns
			
			response.sendRedirect("list");
	}
		
	//all my functions for libraryitem view----------------------------

	
	private void listBook(HttpServletRequest request, HttpServletResponse response) //this function is called when the user wants to redirect to the book of lists
			throws SQLException, ServletException, IOException{		
			
		List<book> listBook = bookDAO.selectAllBooks(idcategory);
		List<book> sortedList = sortbooklist(listBook);
			
		request.setAttribute("listBook", sortedList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private List<book> sortbooklist(List<book> listBook) { //this function is called when we want to list the libraryitems and sort the list by type
		List<book> listBookSorted = listBook.stream()
				  .sorted(Comparator.comparing(book::getType))
				  .collect(Collectors.toList());
		return listBookSorted;
	}


	
	private void listBookWithId(HttpServletRequest request, HttpServletResponse response)//this function is called when the user wants to view libraryitems in a specific category
			throws SQLException, ServletException, IOException{
		
		idcategory = Integer.parseInt(request.getParameter("idd"));
		List<book> listBook = bookDAO.selectAllBooks(idcategory);
		List<book> sortedList = sortbooklist(listBook);
		
		request.setAttribute("listBook", sortedList); //send the sortedlist to book-list.jsp and name it "listbook"
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)//function to redirect to bookform.jsp(when the user wants to add/edit a libraryitem)
			throws ServletException, IOException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
		dispatcher.forward(request, response); 
	}
	
	private void insertBook(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException{
		
		String titleToAcronym = request.getParameter("title");
			
		if (titleToAcronym.contains(" ")) {
			 String str = ""; //acronym placeholder
			 str = request.getParameter("title").replaceAll("\\B.|\\P{L}", "").toUpperCase(); //placing the acronym to str. All the char inputs are 
			 titleToAcronym = titleToAcronym +" (" +str+ ")";
		}
		
		String title = titleToAcronym;
		String author = request.getParameter("author");
		int pages = Integer.parseInt("0" + request.getParameter("pages"));
		int runtimeminutes = Integer.parseInt("0" + request.getParameter("runtimeminutes"));
		String type = request.getParameter("type");
			
		book newBook = new book(title,author,pages,runtimeminutes,type);
		bookDAO.insertBook(newBook,idcategory);//skickar med categoryid 
			
		response.sendRedirect("listbook");
	}
	
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
		throws SQLException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		bookDAO.deleteBook(id);
		response.sendRedirect("listbook");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException{
				
		int id = Integer.parseInt(request.getParameter("id"));
		book existingBook = bookDAO.selectBook(id);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);
				
	}
	
	private void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException{
		
		String titleToAcronym = request.getParameter("title");
		
		if (titleToAcronym.contains(" ")) {
		    String str = ""; //acronym placeholder
		    str = request.getParameter("title").replaceAll("\\B.|\\P{L}", "").toUpperCase(); //placing the acronym to str
		    titleToAcronym = titleToAcronym +" (" +str+ ")";
		}

		int id = Integer.parseInt(request.getParameter("id"));
		String title = titleToAcronym;
		String author = request.getParameter("author");
		int pages = Integer.parseInt("0" + request.getParameter("pages"));
		int runtimeminutes = Integer.parseInt("0" + request.getParameter("runtimeminutes"));
		String type = request.getParameter("type");
		
		
		book book = new book(id,title,author,pages,runtimeminutes,type);
		bookDAO.updateBook(book);
		response.sendRedirect("listbook");	
	}
	
	private void showBorrowerForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException{
				
		int id = Integer.parseInt(request.getParameter("id"));
		book existingBook = bookDAO.selectBook(id);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowerForm.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);
				
	}
	
	private void updateborrower(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException{
		//all the variables i want to reach from borrowerform.jsp
		int id = Integer.parseInt(request.getParameter("id"));
		Boolean isborrowable = Boolean.parseBoolean(request.getParameter("isborrowable"));
		String borrower = request.getParameter("borrower");
		
		if(!isborrowable) {//if false then send borrower name to db  
			if(borrower.isEmpty()) { borrower = "Anonymous"; }//if borrowername has no input from user set it as "anonymous"
			book book = new book(id,isborrowable,borrower);
			bookDAO.updateBook(book);
			response.sendRedirect("listbook");
		}
		else if(isborrowable) {
			//set isborrowable true in db
			bookDAO.unsetBorrower(id);
			//delete the rest variables
			
			//send redirect
			response.sendRedirect("listbook");
		}
	}	
}
		
	
	
	
















