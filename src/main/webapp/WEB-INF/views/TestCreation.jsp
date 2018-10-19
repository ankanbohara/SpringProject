<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<style>
<
style>#table2 {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#table2 td, #table2 th {
	border: 1px solid #ddd;
	padding: 8px;
}

#table2 tr:nth-child(even) {
	background-color: #f2f2f2;
}

#table2 tr:hover {
	background-color: #ddd;
}

#table2 th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}
</style>
<title>Manage Tests</title>
</head>
<body>
	<a href="/educational/logout">Logout</a>
	<h4>
		<a href="/educational/admin/">Click here to return to main page</a>
	</h4>

	<form:form method="POST" command="${update}"
		action="/educational/admin/tests/">
		<table>

			<tr>
				<c:if test="${not empty update}">
					<td><input name="id" value="${test.id}" type="hidden" /></td>
				</c:if>

				<c:if test="${empty update}">
					<td><input name="id" value="-1" type="hidden" /></td>
				</c:if>
			</tr>

			<tr>
				<td><label>Test Description</label></td>
				<td><select name="description">
						<option value="Beginner">Beginner</option>
						<option value="Intermediate">Intermediate</option>
						<option value="Advanced">Advanced</option>
				</select></td>
			</tr>


			<tr>
				<td><label>Test Duration</label></td>
				<td><input name="duration" value="${test.duration }" /></td>
			</tr>

			<tr>
				<td><label>Max Score</label></td>
				<td><input name="maxScore" value="10" readonly /></td>
			</tr>

			<tr>
				<td><label>Topic</label></td>
				<td><select name="topicid">
						<c:if test="${!empty topics}">
							<c:forEach items="${topics}" var="topic" varStatus="loop">
								<option value="${topic.id}">${topic.name}-${topic.description}</option>
							</c:forEach>
						</c:if>
						<c:if test="${empty topics}">
							<option value="NIL">First Add Topics</option>
						</c:if>
				</select></td>

			</tr>

			<tr>
				<c:if test="${!empty topics}">
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</c:if>
			</tr>
		</table>
	</form:form>
	<h4>${error}</h4>
	<a href="/educational/admin/tests">Click here to add more tests</a>
	<br>
	<br>
	<a href="/educational/admin/courses">Update your topics through
		this page</a>
	<h4>${error}</h4>
	<h3>Current Tests</h3>
	<table id="table2">
		<tr>
			<th>Description</th>
			<th>Duration</th>
			<th>MaxScore</th>
			<th>Operation</th>
		</tr>
		<c:forEach items="${tests}" var="test">

			<tr>
				<td>${test.description}</td>
				<td>${test.duration}</td>
				<td>${test.maxScore}</td>
				<td><a href="/educational/admin/tests/delete/${test.id}">Delete</a>
					| <a href="/educational/admin/tests/update/${test.id}"> Update</a></td>
			<tr>
		</c:forEach>

	</table>
</body>
</html>