<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="abc-view">
	<div>
		<h1 class="td">${item.title }</h1>
		<hr>
		<br> <img class="AnhTieuDe" alt="" src="${ctx }/images/${item.image}">
		<p class="NoiDung">${item.content }</p>
	</div>
	
</div>