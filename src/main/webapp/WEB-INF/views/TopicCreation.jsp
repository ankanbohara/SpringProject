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
<title>Manage Topics</title>
</head>
<body>
<a href="/educational/logout">Logout</a>
	<br>
	<h4><a href="/educational/admin/courses">Click here to return to course
		page</a></h4>
	<h4>
		<a href="/educational/admin/">Click here to return to main page</a>
	</h4>
	<h4><a href="/educational/admin/courses/${courseid}/">Click here to return to section page</a></h4>
	<form:form method="POST" modelAttribute="topic"
		action="/educational/admin/courses/${courseid}/${sectionid}/topicsave">
		<h3>${courseName}</h3>
		<h4>Course Code-${courseid}</h4>
		<h4>${sectionName}</h4>

		<table>

			<!--  tr>
				<td><form:label path="id">TopicID</form:label></td>
				<td><form:input path="id" value="${tid}"
						readonly="true" /><font color='red'><form:errors
							path='id' /></font></td>
			</tr-->


			<tr>
				<td><form:label path="id">TopicID</form:label></td>
				<c:if test="${not empty update}">
					<td><form:input path="id" value="${tid}" readonly="true" /><font
						color='red'><form:errors path='id' /></font></td>
				</c:if>

				<c:if test="${empty update}">
					<td><form:input path="id" value="-1" readonly="true" /><font
						color='red'><form:errors path='id' /></font></td>
				</c:if>
			</tr>


			<tr>
				<td><form:label path="name">Topic Name</form:label></td>
				<td><form:input path="name" /><font color='red'><form:errors
							path='name' /></font></td>
			</tr>

			<tr>
				<td><form:label path="description">Topic Description</form:label></td>
				<td><form:input path="description" /><font color='red'><form:errors
							path='description' /></font></td>
			</tr>


			<tr>
				<td><form:label path="sectionid">Section ID</form:label></td>
				<td><form:input path="sectionid" value="${sectionid}"
						readonly="true" /><font color='red'><form:errors
							path='sectionid' /></font></td>
			</tr>

			<tr>
				<td><form:label path="courseid">Course ID</form:label></td>
				<td><form:input path="sectionid" value="${courseid}"
						readonly="true" /><font color='red'><form:errors
							path='sectionid' /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<h4>${error}</h4>
	<h4><a href="/educational/admin/tests/">Test modification page</a></h4>
	<h4><a href="/educational/admin/courses/${courseid}/${sectionid}/">Click here to add more topics in the section</a></h4>
	<h3>Current Topics in the Section :</h3>
	<table id="table2">
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Operations</th>
			<th>Questions</th>
			<th>Material</th>
		</tr>
		<c:forEach items="${topics}" var="topic">

			<tr>
				<td>${topic.name}</td>
				<td>${topic.description}</td>
				<td><a
					href="/educational/admin/courses/${courseid}/${sectionid}/topicdelete/${topic.id}">Delete</a>
					| <a
					href="/educational/admin/courses/${courseid}/${sectionid}/topicupdate/${topic.id}">
						Update</a></td>
				<td><a href="/educational/admin/courses/${courseid}/${sectionid}/${topic.id}">Click here to manage questions</a></td>
				<td><a href="/educational/admin/courses/${courseid}/${sectionid}/${topic.id}/videomaterial/">Click here to manage video material</a>
			<tr>
		</c:forEach>

	</table>
</body>
</html>