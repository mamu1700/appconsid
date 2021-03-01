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
				<c:if test="${category != null}">
					<form action="updatecategory" method="post">
				</c:if>
				<c:if test="${category == null}">
					<form action="insertcategory" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${category != null}">
            			Edit Category
            		</c:if>
						<c:if test="${category == null}">
            			Add New Category
            		</c:if>
					</h2>
				</caption>

				<c:if test="${category != null}">
					<input type="hidden" name="id" value="<c:out value='${category.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Category Name</label> <input type="text"
						value="<c:out value='${category.categoryname}' />" class="form-control"
						name="categoryname" required="required">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
