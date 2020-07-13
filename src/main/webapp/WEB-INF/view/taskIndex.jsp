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
<title>Project</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<h3>TASKS</h3>
		<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_LEADER')">
			<a class="btn btn-primary" href="/task/createTask"> <i
				class="fa fa-plus"></i> <span>ADD TASK</span>
			</a>
			<br />
		</security:authorize>
		<div class="box-item">
			<table id="example" class="table table-striped table-bordered"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>Project Title</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Assignee</th>
						<th>Task Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${tasks}">
						<tr>
							<td>${task.title}</td>
							<td>${task.description}</td>
							<td>${task.project.title}</td>
							<td>${task.startDate}</td>
							<td>${task.endDate}</td>
							<td>${task.assignee.username}</td>
							<td>${task.taskState}</td>
							<td><a class="btn btn-sm btn-primary"
								href="/task/editTask/${task.taskId}"
								id="+ 'task_id_' + ${task.taskId} + '_edit'"> <i
									class="fa fa-edit"></i>
							</a> <a class="btn btn-sm btn-primary"
								href="/task/viewTask/${task.taskId}"
								id="+ 'task_id_' + ${task.taskId} + '_view'"> <i
									class="fa fa-eye"></i>
							</a> <form:form action="/task/deleteTask" method="POST"
									name="someForm" modelAttribute="task">
									<input type="hidden" class="form-control " name="taskId"
										value="${task.taskId}" />
									<button type="submit" class="btn btn-sm btn-primary">
										<i class="fa fa-trash"></i>
									</button>
								</form:form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var element = document.getElementById("taskManagementHeader");
		element.classList.add("active");
	});
</script>
</html>
