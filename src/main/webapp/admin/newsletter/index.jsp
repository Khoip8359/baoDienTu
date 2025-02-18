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
		<input class="email" name="email" placeholder="Email?" value="${form.email }"><br>
		<input class="enable" type="radio" name="enable" value="true" ${form.enable?'checked':''}>Enable 
		<input class="enable" type="radio" name="enable" value="false" ${form.enable?'':'checked' }>Disable<br>
		<hr>
		<button formaction="/assignment/admin/newsletter/create">Create</button>
		<button formaction="/assignment/admin/newsletter/update">Update</button>
		<button formaction="/assignment/admin/newsletter/delete">Delete</button>
		<button formaction="/assignment/admin/newsletter/reset">Reset</button>
	</form>
	<hr>
	<table border="1" style="width: 100%">
		<thead>
			<tr>
				<th>Email</th>
				<th>Enable</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach var="u" items="${list }">
			<tbody>
				<tr>
					<td>${u.email }</td>
					<td>${u.enable }</td>
					<td><a href="/assignment/admin/newsletter/edit/${u.email }">Edit</a></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>