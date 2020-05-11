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
				<li><a href="/user/index">User Management</a></li>
				<li class="active"><a href="/project/index">Project
						Management</a></li>
				<li><a href="/task/index">Task Management</a></li>
			</ul>
		</div>
	</nav>
	<div style="margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<div style="width: 50%;">
			<h3>PROJECT FORM</h3>
			<form:form action="/project/saveOrUpdateProject" method="POST"
				modelAttribute="project">
				<div class="form-group">
					<label for="title">Title</label> <input type="text"
						class="form-control " name="title" value="${project.title}" />
				</div>
				<div class="form-group">
					<label for="description">Description</label> <input type="text"
						class="form-control" name="description"
						value="${project.description}" />
				</div>
				<div class="form-group">
					<label for="startDate">Start date</label> <input id="startDate"
						type="date" class="form-control" name="startDate" />
				</div>
				<div class="form-group">
					<label for="endDate">End date</label> <input id="endDate"
						type="date" class="form-control" name="endDate" />
				</div>
				<div id="leader_section" class="form-group">
					<label for="leader">Project Leader</label> <%-- <select
						id="leaderSelected" class="form-control" name="leader">
						<c:forEach var="user" items="${leaders}">
							<option value="${user.userId}">${user.username}</option>
						</c:forEach>
					</select> --%>
					<form:select items="${leaders}" itemValue="userId"
						itemLabel="username" multiple="false" path="leader"
						class="form-Control" />
				</div>
				<div id="members_section" class="form-group">
					<label for="projectMembers">Project Members</label>
					<%-- <select
						id="membersSelected" class="form-control" name="projectMembers"
						multiple>
						<c:forEach var="user" items="${members}">
							<option value="${user.userId}">${user.username}</option>
						</c:forEach>
					</select> --%>
					<form:select items="${members}" itemValue="userId"
						itemLabel="username" multiple="true" path="projectMembers"
						class="form-Control" />
				</div>
				<input type="submit" value="Submit" />
			</form:form>
		</div>

		<%-- <h4>PROJECT MEMBERS</h4>
		<div class="box-item">
			<table id="example" class="table table-striped table-bordered"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Project Role</th>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>CREATOR</td>
						<td>${project.creator.username}</td>
						<td>${project.creator.firstName}</td>
						<td>${project.creator.lastName}</td>
					</tr>
					<tr>
						<td>LEADER</td>
						<td>${project.leader.username}</td>
						<td>${project.leader.firstName}</td>
						<td>${project.leader.lastName}</td>
					</tr>
					<c:forEach var="user" items="${project.projectMembers}">
						<tr>
							<td>MEMBER</td>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div> --%>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>
