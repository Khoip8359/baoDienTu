<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý người dùng</title>
</head>
<body>
	<form method="post">
		<input class="id" name="id" placeholder="Id?" value="${form.id }"><br>
		<input class="password" name="password" placeholder="Password?"
			value="${form.password }"><br> <input class="fullname" name="fullname"
			placeholder="Fullname?" value="${form.fullname }"><br> <input
			name="birthday" placeholder="Birthday?" value="${form.birthday }"><br>
		<input class="gender" type="radio" name="gender" value="true"
			${form.gender?'checked':''}>Nam <input class="gender" type="radio"
			name="gender" value="false" ${form.gender?'':'checked' }>Nữ<br>
		<input class="mobile" name="mobile" placeholder="Mobile?" value="${form.mobile }"><br>
		<input class="email" name="email" placeholder="Email?" value="${form.email }"><br>
		<input class="role" type="radio" name="role" value="true"
			${form.role?'':'checked' }>Admin <input class="role" type="radio"
			name="role" value="false" ${form.role?'':'checked' }>User<br>
		<hr>
		<button formaction="/assignment/admin/user/create">Create</button>
		<button formaction="/assignment/admin/user/update">Update</button>
		<button formaction="/assignment/admin/user/delete">Delete</button>
		<button formaction="/assignment/admin/user/reset">Reset</button>
	</form>
	<hr>
	<table border="1" style="width: 100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Fullname</th>
				<th>Birthday</th>
				<th>Gender</th>
				<th>Role</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach var="u" items="${list }">
			<tbody>
				<tr>
					<td>${u.id }</td>
					<td>${u.fullname }</td>
					<td>${u.birthday }</td>
					<td>${u.gender?'Nam':'Nữ' }</td>
					<td>${u.role }</td>
					<td><a href="/assignment/admin/user/edit/${u.id }">Edit</a></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>