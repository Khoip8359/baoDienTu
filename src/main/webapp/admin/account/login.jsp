<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="abc-view">
	<h3>Login</h3>
	<i style="color: red">${message }</i>
	<form action="${ctx }/account/login" method="post">
		<input name="username" placeholder="Username?"><br>
		<input name="password" placeholder="Password?"><br> <hr>
		<button>Login</button>
	</form>
</div>