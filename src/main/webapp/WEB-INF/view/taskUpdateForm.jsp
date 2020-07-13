<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Management</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="background: #e9f3f6; margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<div style="width: 50%;">
			<h3>TASK UPDATE FORM</h3>
			<form:form action="/task/updateTask" method="POST"
				modelAttribute="task">
				<form:input type="hidden" class="form-control " name="taskId"
					value="${task.taskId}" path="taskId" />
				<form:input type="hidden" class="form-control "
					name="project.projectId" path="project.projectId"
					value="${task.project.projectId}" />
				<div class="form-group">
					<label for="title">Title</label>
					<form:input type="text" class="form-control " name="title"
						path="title" value="${task.title}" required="required" />
				</div>
				<div class="form-group">
					<label for="description">Description</label>
					<form:input type="text" class="form-control" name="description"
						path="description" value="${task.description}" required="required" />
				</div>
				<div class="form-group">
					<label for="startDate">Start date</label>
					<form:input id="startDate" type="date" class="form-control"
						name="startDate" path="startDate" readonly="true" />
				</div>
				<div class="form-group">
					<label for="endDate">End date</label>
					<form:input id="endDate" type="date" class="form-control"
						name="endDate" path="endDate" readonly="true" />
				</div>
				<div id="taskState_section" class="form-group">
					<label for="leader">Task State</label>
					<form:select id="taskStateSelect" multiple="false" path="taskState"
						class="form-Control" required="required">
						<option value="PENDING">PENDING</option>
						<option value="IN_PROGRESS">IN_PROGRESS</option>
						<option value="COMPLETE">COMPLETE</option>
					</form:select>
				</div>
				<form:input type="hidden" class="form-control "
					name="assignee.userId" path="assignee.userId"
					value="${task.assignee.userId}" />
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
				<form:input type="submit" value="Submit" path="" />
			</form:form>
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

	<script src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
</body>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</html>

