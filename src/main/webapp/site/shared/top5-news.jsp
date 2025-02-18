<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="top5-panel">
	<h3 class="top5-title">Bản tin hot nhất</h3>
	<ul class="top5-items">
	<c:forEach var="n" items="${hotnews}">
		<li><a class="top5-news" href="${ctx}/news/detail/${n.id}">${n.title }</a></li>
		<br>
	</c:forEach>
	</ul>
	<hr>
	<h3 class="top5-title">Bản mới mới nhất</h3>
	<ul class="top5-items">
	<c:forEach var="n" items="${lastnews}">
		<li><a class="top5-news" href="${ctx}/news/detail/${n.id}">${n.title }</a></li>
		<br>
	</c:forEach>
	</ul>
	<hr>
	<form action="${ctx }/home/index" method="post">
		<label>Nhận tin mới : </label><br>
		<input type="text" name="email" ><button>Đăng ký</button>
	</form>
</div>