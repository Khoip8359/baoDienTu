<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý loại tin</title>
</head>
<body>
	<form method="post">
		<input class="id" name="id" placeholder="Id?" value="${form.id }"><br>
		<input class="name" name="name" placeholder="Name?" value="${form.name }">
		<hr>
		<button formaction="/assignment/admin/category/create">Create</button>
		<button formaction="/assignment/admin/category/update">Update</button>
		<button formaction="/assignment/admin/category/delete">Delete</button>
		<button formaction="/assignment/admin/category/reset">Reset</button>
	</form>
	<hr>
	<table border="1" style="width: 100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach var="u" items="${list }">
			<tbody>
				<tr>
					<td>${u.id }</td>
					<td>${u.name }</td>
					<td><a href="/assignment/admin/category/edit/${u.id }">Edit</a></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>