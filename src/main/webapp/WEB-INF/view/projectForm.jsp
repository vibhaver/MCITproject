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
	<div style="background: #e9f3f6; margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<div style="width: 50%;">
			<h3>PROJECT FORM</h3>
			<form:form action="/project/saveProject" method="POST"
				modelAttribute="project">
				<div class="form-group">
					<label for="title">Title</label> <form:input type="text"
						class="form-control " name="title" path="title" value="${project.title}" required="required" />
				</div>
				<div class="form-group">
					<label for="description">Description</label> <form:input type="text"
						class="form-control" name="description" path="description"
						value="${project.description}" required="required"/>
				</div>
				<div class="form-group">
					<label for="startDate">Start date</label> <form:input id="startDate"
						type="date" class="form-control" name="startDate" path="startDate" required="required" value="2020-07-01"/>
				</div>
				<div class="form-group">
					<label for="endDate">End date</label> <form:input id="endDate"
						type="date" class="form-control" name="endDate" path="endDate" required="required" min="2020-07-01" value="2020-07-01"/>
				</div>
				<div id="leader_section" class="form-group">
					<label for="leader">Project Leader</label>
					<form:select items="${leaders}" itemValue="userId"
						itemLabel="username" multiple="false" path="leader.userId"
						class="form-Control" required="required"/>
				</div>
				<div id="members_section" class="form-group">
					<label for="projectMembers">Project Members</label>
					<form:select items="${members}" itemValue="userId"
						itemLabel="username" multiple="true" path="projectMembers"
						class="form-Control" required="required"/>
				</div>
				<form:input type="submit" value="Submit" path=""/>
			</form:form>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var element = document.getElementById("projectManagementHeader");
			element.classList.add("active");
		});
		$("#startDate").change(function() {
			$("#endDate").attr("min", $("#startDate").val());
			$("#endDate").attr("value", $("#startDate").val());
		});
	</script>
</body>
</html>
