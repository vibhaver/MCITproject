<!-- Latest compiled and minified CSS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">MCIT</a>
		</div>
		<ul class="nav navbar-nav">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li id="userManagementHeader"><a href="/user/index">User
						Management</a></li>
			</security:authorize>
			<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_LEADER')">
				<li id="projectManagementHeader"><a href="/project/index">Project
						Management</a></li>
			</security:authorize>
			<security:authorize
				access="hasAnyRole('ROLE_ADMIN', 'ROLE_LEADER', 'ROLE_MEMBER')">
				<li id="taskManagementHeader"><a href="/task/index">Task
						Management</a></li>
			</security:authorize>
		</ul>
		<ul class="nav navbar-nav floatright">
			<li><a href="javascript:callLogout()">Logout</a></li>
		</ul>
	</div>
</nav>
<form action="/logout" method="post">
	<input type="hidden" id="logoutBtn" value="Logout" type="submit" />
</form>

<style>
body {
  font: 13px/20px "Lucida Grande", Tahoma, Verdana, sans-serif;
  color: #404040;
  background: #e9f3f6;
}
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.floatright {
	float: right;
}
</style>
<script>
function callLogout(){
	console.log("hiiii");
	var url = "/logout";
	$.post(url, null, function(data) {
		console.log("logout");
		location.replace("http://localhost:8082/login")
	});
}
</script>