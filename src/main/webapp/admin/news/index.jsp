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
		<input class="id" name="id" placeholder="Id" value="${form.id }"><br>
		<input class="title" name="title" placeholder="title" value="${form.title }"><br>
		<textarea rows="10" cols="150" class="content" name="content" value="${form.content }"></textarea> <br>
		<input class="image" type="file" name="image" placeholder="image" id="image"> <label for="image" value ="${form.image }">${form.image }</label> <br>
		<input class="postedDate" name="postedDate" placeholder="Posted Date" value="${form.postedDate }"><br>
		<input class="author" name="author" placeholder="author" value="${form.author }"><br>
		<input class="viewCount" name="viewCount" placeholder="View" value="${form.viewCount }"><br>
		<input class="categoryId" name="categoryId" placeholder="Category" value="${form.categoryId }"><br>
		<input class="home" type="radio" name="home" value="true" ${form.home?'checked':''}>Có hiện 
		<input class="home" type="radio" name="home" value="false" ${form.home?'':'checked' }>Không hiện<br>
		
		<hr>
		<button formaction="/assignment/admin/news/create">Create</button>
		<button formaction="/assignment/admin/news/update">Update</button>
		<button formaction="/assignment/admin/news/delete">Delete</button>
		<button formaction="/assignment/admin/news/reset">Reset</button>
	</form>
	<hr>
	<table border="1" style="width: 100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Content</th>
				<th>Image</th>
				<th>Posted Date</th>
				<th>Author</th>
				<th>View</th>
				<th>Category</th>
				<th>Home</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach var="u" items="${list }">
			<tbody>
				<tr>
					<td>${u.id }</td>
					<td>${u.title }</td>
					<td>${u.content }</td>
					<td>${u.image }</td>
					<td>${u.postedDate }</td>
					<td>${u.author }</td>
					<td>${u.viewCount }</td>
					<td>${u.categoryId }</td>
					<td>${u.home }</td>
					<td><a href="/assignment/admin/news/edit/${u.id }">Edit</a></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>