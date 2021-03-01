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
					Library App - consid
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
			<h3 class="text-center">List of Library Items &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Sorted by: Type</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Book</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author</th>
						<th>Pages</th>
						<th>Runtime(min)</th>
						<th>Type</th>
						<th>isborrowable</th>
						<th>Borrower</th>
						<th>Borrow Date</th>
						<th>Actions</th>
						
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="book" items="${listBook}">
						<tr>
							<td><c:out value="${book.id}" /></td>
							<td><c:out value="${book.title}" /></td>
							<td><c:out value="${book.author}" /></td>
							<td><c:out value="${book.pages}" /></td>
							<td><c:out value="${book.runtimeminutes}" /></td>
							<td><c:out value="${book.type}" /></td>
							<td><c:out value="${book.isborrowable}" /></td>
							<td><c:out value="${book.borrower}" /></td>
							<td><c:out value="${book.borrowdate}" /></td>
							<td><a href="edit?id=<c:out value='${book.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${book.id}' />">Delete</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<c:choose>
								  <c:when test="${book.type == 'reference book'}">
								    Not Borrowable!
								  </c:when>
								  <c:when test="${book.type != 'reference book'}">
								    <a href="adddeleteborrower?id=<c:out value='${book.id}' />">Check in/out</a>
								  </c:when>
								</c:choose>
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