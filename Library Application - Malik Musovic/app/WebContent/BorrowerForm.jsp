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
					<form action="updateborrower" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${book != null}">
            			Add/Delete borrower
            		</c:if>
					</h2>
				</caption>

				<c:if test="${book != null}">
					<input type="hidden" name="id" value="<c:out value='${book.id}' />" />
				</c:if>
				
				<fieldset class="form-group">
					<label>Is it borrowable? true/false </label>
						<div class="radio">
						  <label>
						    <input type="radio" name="isborrowable" id="optionsRadios1" value="true" checked>
						    True
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <input type="radio" name="isborrowable" id="optionsRadios2" value="false">
						    False
						  </label>
						</div>
				</fieldset>
				<fieldset class="form-group">
					<label>Borrower(Type the name of the borrower or leave blank if it is a anonymous borrower)</label> <input type="text"
						value="<c:out value='${book.borrower}' />" 
						class="form-control"
						name="borrower">
				</fieldset>			    
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
