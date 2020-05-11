<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Management</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">MCIT Project</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/user/index">User Management</a></li>
				<li><a href="/project/index">Project Management</a></li>
				<li><a href="/task/index">Task Management</a></li>
			</ul>
		</div>
	</nav>
	<div
		style="width: 50%; margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<h2>USER CREATION</h2>
		<form:form action="/user/saveOrUpdateUser" method="POST"
			modelAttribute="user">
			<input type="hidden" class="form-control " name="userId"
				value="${user.userId}" />
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control " name="username" value="${user.username}" />
			</div>
			<div class="form-group">
				<label for="firstName">First Name</label> <input type="text"
					class="form-control" name="firstName" value="${user.firstName}" />
			</div>
			<div class="form-group">
				<label for="lastName">Last Name</label> <input type="text"
					class="form-control" name="lastName" value="${user.lastName}" />
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" name="password" value="${user.password}" />
			</div>
			<div id="role_section" class="form-group">
				<label for="userRole">User Role</label> <select id="roleSelected"
					class="form-control" name="userRole">
					<option value="ADMIN">ADMIN</option>
					<option value="LEADER">LEADER</option>
					<option value="MEMBER">MEMBER</option>
				</select>
			</div>
			<input type="submit" value="Submit" />

		</form:form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var role = "${user.userRole}";
			if (role != null)
				$("#roleSelected").val(role);
		});
	</script>
</body>
</html>
