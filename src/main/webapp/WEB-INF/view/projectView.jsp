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
			<h3>PROJECT VIEW</h3>
			<form:form action="/user/saveOrUpdateProject" method="POST"
				modelAttribute="project">
				<div class="form-group">
					<label for="title">Title</label> <input type="text"
						class="form-control " name="title" value="${project.title}"
						readonly />
				</div>
				<div class="form-group">
					<label for="description">Description</label> <input type="text"
						class="form-control" name="description"
						value="${project.description}" readonly />
				</div>
				<div class="form-group">
					<label for="startDate">Start date</label> <input id="startDate"
						type="date" class="form-control" name="startDate" readonly />
				</div>
				<div class="form-group">
					<label for="endDate">End date</label> <input id="endDate"
						type="date" class="form-control" name="endDate" readonly />
				</div>
			</form:form>
		</div>
		<h4>PROJECT MEMBERS</h4>
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
		</div>
		<h4>PROJECT TASKS</h4>
		<div class="box-item">
			<table id="example" class="table table-striped table-bordered"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>Assignee</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Task State</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${project.tasks}">
						<tr>
							<td>${task.title}</td>
							<td>${task.description}</td>
							<td>${task.assignee.username}</td>
							<td>${task.startDate}</td>
							<td>${task.endDate}</td>
							<td>${task.taskState}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var startDate = "${project.startDate}";
			if (startDate != null)
				$("#startDate").val(startDate.toString().substring(0, 10));
			var endDate = "${project.endDate}";
			if (endDate != null)
				$("#endDate").val(endDate.toString().substring(0, 10));
		});
	</script>
</body>
</html>
