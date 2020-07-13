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
	<div
		style="background: #e9f3f6; width: 50%; margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<h2>USER CREATION</h2>
		<form:form action="/user/updateUser" method="POST"
			modelAttribute="user">
			<form:input type="hidden" class="form-control " name="userId"
				value="${user.userId}" path="userId" />
			<div class="form-group">
				<label for="username">Username</label>
				<form:input type="text" class="form-control " name="username"
					value="${user.username}" readonly="true" path="username" />
			</div>
			<div class="form-group">
				<label for="firstName">First Name</label>
				<form:input type="text" class="form-control" name="firstName"
					value="${user.firstName}" required="required" path="firstName" />
			</div>
			<div class="form-group">
				<label for="lastName">Last Name</label>
				<form:input type="text" class="form-control" name="lastName"
					value="${user.lastName}" required="required" path="lastName" />
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<form:input type="password" class="form-control" name="password"
					value="${user.password}" readonly="true" path="password" />
			</div>
			<div id="role_section" class="form-group">
				<label for="userRole">User Role</label>
				<form:select id="roleSelected" class="form-control" name="userRole"
					required="required" path="userRole">
					<option value="ROLE_ADMIN">ADMIN</option>
					<option value="ROLE_LEADER">LEADER</option>
					<option value="ROLE_MEMBER">MEMBER</option>
				</form:select>
			</div>
			<form:input type="submit" value="Submit" path="" />
		</form:form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var element = document.getElementById("userManagementHeader");
			element.classList.add("active");
			var role = "${user.userRole}";
			if (role != null)
				$("#roleSelected").val(role);
		});
	</script>
</body>
</html>
