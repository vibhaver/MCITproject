<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project Management</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="background: #e9f3f6;  margin-left: 30px; margin-right: 30px; margin-top: 30px;">
		<div style="width: 50%;">
			<h3>PROJECT UPDATE FORM</h3>
			<form:form action="/project/updateProject" method="POST"
				modelAttribute="project">
				<form:input type="hidden" class="form-control " name="projectId"
					value="${project.projectId}" path="projectId"/>
				<div class="form-group">
					<label for="title">Title</label> <form:input type="text"
						class="form-control " name="title" value="${project.title}" path="title" required="required"/>
				</div>
				<div class="form-group">
					<label for="description">Description</label> <form:input type="text"
						class="form-control" name="description" path="description" 
						value="${project.description}" required="required"/>
				</div>
				<form:input type="hidden" class="form-control " name="creator.userId"
					value="${project.creator.userId}" path="creator.userId"/>
				<div class="form-group">
					<label for="creatorUsername">Created By</label> <form:input type="text"
						class="form-control" value="${project.creator.username}" path="" readonly="true" />
				</div>
				<div class="form-group">
					<label for="startDate">Start date</label> <form:input id="startDate"
						type="date" class="form-control" name="startDate" path="startDate" readonly="true" />
				</div>
				<div class="form-group">
					<label for="endDate">End date</label> <form:input id="endDate"
						type="date" class="form-control" name="endDate" path="endDate" readonly="true" />
				</div>
				<div id="leader_section" class="form-group">
					<label for="leader">Project Leader</label>
					<form:select id="leaderSelectedSelect" items="${leaders}"
						itemValue="userId" itemLabel="username" multiple="false"
						path="leader.userId" class="form-Control" required="required"/>
				</div>
				<div id="members_section" class="form-group">
					<label for="projectMembers">Project Members</label>
					<form:select multiple="true" path="projectMembers"
						class="form-Control" required="required">
						<c:forEach items="${members}" var="member">
							<fmt:parseNumber var="id_value" type="number"
								value="${member.userId}" />
							<c:choose>
								<c:when test="${fn:contains(membersSelected, id_value)}">
									<option value="${member.userId}" selected="selected">${member.username}</option>
								</c:when>
								<c:otherwise>
									<option value="${member.userId}">${member.username}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</form:select>
				</div>
				<form:input type="submit" value="Submit" path=""/>
			</form:form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var element = document.getElementById("projectManagementHeader");
			element.classList.add("active");
			var startDate = "${project.startDate}";
			if (startDate != null)
				$("#startDate").val(startDate.toString().substring(0, 10));
			var endDate = "${project.endDate}";
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

