<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>ABC News</title>
	
	<link href="${ctx}/css/abcnews.css" rel="stylesheet"/>
	<script src="${ctx}/js/abcnews.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
	<header class="abc-header">
		<jsp:include page="/site/shared/header.jsp"/>
	</header>
	<nav class="abc-menu">
		<c:import url="/category/menu"></c:import>
	</nav>
	<main class="abc-main">
		<article class="abc-center">
			<jsp:include page="${view }"></jsp:include>
		</article>
		<aside class="abc-right">
			<c:import url="/layout/top5-news"></c:import>
		</aside>
	</main>
	<footer class="abc-footer">
		<jsp:include page="/site/shared/footer.jsp"/>
	</footer>
</body>
</html>