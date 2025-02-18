<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"
	scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ABC News</title>

<link href="${ctx}/css/abcAdmin.css" rel="stylesheet" />
<script src="${ctx}/js/abcnews.js"></script>
</head>
<body>
	<header class="abc-header">
		<h1>Công cụ quản trị</h1>
	</header>
	<nav class="abc-menu">
		<hr>
		<a href="${ctx }/home/index">Trang Chủ</a> <a
			href="${ctx }/admin/news/index">Tin tức</a> <a
			href="${ctx }/admin/category/index">Loại tin</a> <a
			href="${ctx }/admin/user/index">Người dùng</a> <a
			href="${ctx }/admin/newsletter/index">Newsletter</a>
		<hr>
	</nav>
	<main class="abc-main">
		<jsp:include page="${view }"></jsp:include>
	</main>
	<footer class="abc-footer"> WELCOME ${user.fullname}</footer>
</body>
</html>