<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="abc-view">
	<h1>Bản tin mới nhất :</h1>
	<hr>
	<br>
	<c:forEach var="n" items="${news }">
		<a href="${ctx }/news/detail/${n.id}">
			<div class="rol1">
				<img alt="" src="${ctx }/images/${n.image}">
				<div>
					<p class="rol1-TieuDe">${n.title }</p>
					<br>
					<p class="rol1-TacGia">
						<b>-${n.author }-</b> Esports | 04/10/2024 18:59
					</p>
				</div>
			</div>
		</a>
		<br>
		<br>
	</c:forEach>
</div>