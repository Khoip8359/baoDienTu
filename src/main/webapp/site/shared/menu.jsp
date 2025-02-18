<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class ="menu">
	<a href="${ctx }/home/index">Home</a>
	<c:forEach var="c" items="${cates }" varStatus="st">
		<a href="${ctx }/news/list-by-cates/${c.id}">${c.name }</a>
	</c:forEach>
	<a href="${ctx }/account/login">Đăng nhập</a>
	<hr>
</div>