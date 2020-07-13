<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Management</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style=" background: #e9f3f6; margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<div style="width: 50%;">
			<h3>TASK VIEW</h3>
			<form:form modelAttribute="task">
				<div class="form-group">
					<label for="title">Title</label> <input type="text"
						class="form-control " name="title" value="${task.title}" readonly />
				</div>
				<div class="form-group">
					<label for="description">Description</label> <input type="text"
						class="form-control" name="description"
						value="${task.description}" readonly />
				</div>
				<div class="form-group">
					<label for="startDate">Start date</label> <input id="startDate"
						type="date" class="form-control" name="startDate" readonly />
				</div>
				<div class="form-group">
					<label for="endDate">End date</label> <input id="endDate"
						type="date" class="form-control" name="endDate" readonly />
				</div>
				<div class="form-group">
					<label for="taskState">Task State</label> <input type="text"
						class="form-control" name="taskState" value="${task.taskState}"
						readonly />
				</div>
			</form:form>
		</div>
		<h4>ASSIGNEE</h4>
		<div class="box-item">
			<table id="example" class="table table-striped table-bordered"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${task.assignee.username}</td>
						<td>${task.assignee.firstName}</td>
						<td>${task.assignee.lastName}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4>PROJECT</h4>
		<div class="box-item">
			<table id="example" class="table table-striped table-bordered"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>Leader</th>
						<th>Start Date</th>
						<th>End Date</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${task.project.title}</td>
						<td>${task.project.description}</td>
						<td>${task.project.leader.username}</td>
						<td>${task.project.startDate}</td>
						<td>${task.project.endDate}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var element = document.getElementById("taskManagementHeader");
			element.classList.add("active");
			var startDate = "${task.startDate}";
			if (startDate != null)
				$("#startDate").val(startDate.toString().substring(0, 10));
			var endDate = "${task.endDate}";
			if (endDate != null)
				$("#endDate").val(endDate.toString().substring(0, 10));
		});
	</script>
</body>
</html>
