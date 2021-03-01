<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Library Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				Library App
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Click here to get back to categories</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${book != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${book == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${book != null}">
            			Edit Library item
            		</c:if>
						<c:if test="${book == null}">
            			Add New Library item
            		</c:if>
					</h2>
				</caption>

				<c:if test="${book != null}">
					<input type="hidden" name="id" value="<c:out value='${book.id}' />" />
				</c:if>
				

				<fieldset class="form-group">
					<label>Book Title</label> <input type="text"
						value="<c:out value='${book.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Author</label> <input type="text"
						value="<c:out value='${book.author}' />" class="form-control"
						name="author">
				</fieldset>

				<fieldset class="form-group">
					<label>Pages</label> <input type="number"
						value="<c:out value='${book.pages}' />" class="form-control"
						name="pages">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Runtime in minutes(Only necessary if it is a DVD or Audio Book)</label> <input type="number"
						value="<c:out value='${book.runtimeminutes}' />" class="form-control"
						name="runtimeminutes">
				</fieldset>
				
				
				<fieldset class="form-group">
					<label>What type of library item? </label>
						<div class="radio">
						  <label>
						    <input type="radio" name="type" id="optionsRadios1" value="book" checked>
						    Book
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <input type="radio" name="type" id="optionsRadios2" value="dvd">
						    DVD
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <input type="radio" name="type" id="optionsRadios3" value="audio book">
						    Audio Book
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <input type="radio" name="type" id="optionsRadios4" value="reference book">
						    Reference Book
						  </label>
						</div>
				</fieldset>	    
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>





















