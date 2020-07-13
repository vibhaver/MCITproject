<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<style>
body {
	margin: 2em;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<h3>USERS</h3>
		<a class="btn btn-primary" href="/user/createUser"> <i
			class="fa fa-plus"></i> <span>ADD USER</span>
		</a> <br />
		<div class="box-item">
			<table id="example" class="table table-striped table-bordered"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Role</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.userRole}</td>
							<td><a class="btn btn-sm btn-primary"
								href="/user/editUser/${user.userId}"
								id="${user.username} + '_edit'"> <i class="fa fa-edit"></i>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>



</body>
<script type="text/javascript">
	$(document).ready(function() {
		var element = document.getElementById("userManagementHeader");
		element.classList.add("active");
	});
</script>
</html>
