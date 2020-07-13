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
	<div style="background: #e9f3f6; margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<div style="width: 50%;">
			<h3>TASK FORM</h3>
			<form:form action="/task/saveTask" method="POST"
				modelAttribute="task">
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
				<div id="project_section" class="form-group">
					<label for="project">Project</label>
					<form:select id="projectSelection" items="${projects}"
						itemValue="projectId" itemLabel="title" multiple="false"
						path="project.projectId" class="form-Control" required="required" />
				</div>
				<div class="form-group">
					<label for="startDate">Start date</label>
					<form:input id="startDate" type="date" class="form-control"
						name="startDate" required="required" path="startDate" />
				</div>
				<div class="form-group">
					<label for="endDate">End date</label>
					<form:input id="endDate" type="date" class="form-control"
						name="endDate" required="required" path="endDate" />
				</div>
				<div id="member_section" class="form-group">
					<label for="assignee">Assignee</label>
					<form:select items="${members}" itemValue="userId"
						itemLabel="username" multiple="false" path="assignee.userId"
						class="form-Control" required="required" />
				</div>
				<form:input type="submit" value="Submit" path="" />
			</form:form>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var element = document.getElementById("taskManagementHeader");
			element.classList.add("active");
			updateStartEndDate();
		});
		$("#projectSelection").change(function() {
			updateStartEndDate();
		});
		function updateStartEndDate() {
			var projectSelected = $("#projectSelection").val();
			var url = "/task/getDatesForProject/" + projectSelected;
			$.get(url, null, function(data) {
				var dates = data.split(",");
				var start = dates[0];
				var end = dates[1];
				$("#startDate").attr("min", start);
				$("#startDate").attr("max", end);
				$("#startDate").attr("value", start);
				$("#endDate").attr("min", start);
				$("#endDate").attr("max", end);
				$("#endDate").attr("value", start);
			});
		}
		$("#startDate").change(function() {
			$("#endDate").attr("min", $("#startDate").val());
			$("#endDate").attr("value", $("#startDate").val());
		});
	</script>
</body>
</html>
