<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Book Application</title>
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
					Library App - Consid
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Click here to get back to categories</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Categories &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Sorted by: Category Name</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/newcategory" class="btn btn-success">Add
					New Category</a>
				 <p>${err}</p>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Category</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="category" items="${listCategory}">
						<tr>
							<td><c:out value="${category.id}" /></td>
							<td><c:out value="${category.categoryname}" /></td>
							<td><a href="editcategory?id=<c:out value='${category.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="deletecategory?id=<c:out value='${category.id}' />">Delete</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="listbookwithid?idd=<c:out value='${category.id}' />">Show Libraryitems in <c:out value="${category.categoryname}" /></a>
							</td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>