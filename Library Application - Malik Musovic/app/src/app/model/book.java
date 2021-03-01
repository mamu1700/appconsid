package app.model;

import java.sql.Date;

public class book { //my java book class, should be named to libraryitems insteed sence a dvd is not a book :)
	
	//all the required variables for a book
	
	protected int id;
	protected String title;
	protected String author;
	protected int pages;
	protected int runtimeminutes;
	protected String type;
	protected boolean isborrowable;
	protected String borrower;
	protected Date borrowdate;
	
	
	
	//constructors
	
	public book(int id, String title, String author, int pages, int runtimeminutes, String type) { //this constructor is called when updating a libraryitem 
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.runtimeminutes = runtimeminutes;
		this.type = type;
	}
	
	public book(String title, String author, int pages, int runtimeminutes, String type) { //this constructor is called when inserting a libraryitem
		super();
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.runtimeminutes = runtimeminutes;
		this.type = type;	
	}
	
	

	public book(int id, Boolean isborrowable, String borrower) { //this one is called when updating a borrower
		super();
		this.id = id;
		this.isborrowable = isborrowable;
		this.borrower = borrower;
	}

	

	public book(int id, String title, String author, int pages, int runtimeminutes, String type, Boolean isborrowable,
			String borrower, Date borrowdate) { // this constuct is called when we want to list all the libraryitems in the application
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.runtimeminutes = runtimeminutes;
		this.type = type;
		this.isborrowable = isborrowable;
		this.borrower = borrower;
		this.borrowdate = borrowdate;
	}
	
	//getters and setters

	public Date getBorrowdate() {
		return borrowdate;
	}
	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {    
		return title;
	}
	public void setTitle(String title) {		
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getRuntimeminutes() {
		return runtimeminutes;
	}
	public void setRuntimeminutes(int runtimeminutes) {
		this.runtimeminutes = runtimeminutes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isIsborrowable() {
		return isborrowable;
	}
	public void setIsborrowable(boolean isborrowable) {
		this.isborrowable = isborrowable;
	}
}
