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
<title>Manage Sections</title>
</head>
<body>
	<a href="/educational/logout">Logout</a>
	<br>
	<h4><a href="/educational/admin/courses">Click here to return to course
		page</a></h4>
	<h4>
		<a href="/educational/admin/">Click here to return to main page</a>
	</h4>
	<form:form method="POST" modelAttribute="section"
		action="/educational/admin/courses/${courseid}/sectionsave">
		<h3>${coursename}</h3>
		<h4>Course Code-${courseid}</h4>

		<table>

			<tr>
				<td><form:label path="sectionid">SectionID</form:label></td>
				<c:if test="${not empty update}">
					<td><form:input path="sectionid" value="${sectionid}"
							readonly="true" /><font color='red'><form:errors
								path='sectionid' /></font></td>
				</c:if>

				<c:if test="${empty update}">
					<td><form:input path="sectionid" value="" /><font color='red'><form:errors
								path='sectionid' /></font></td>
				</c:if>
			</tr>


			<tr>
				<td><form:label path="name">Section Name</form:label></td>
				<td><form:input path="name" /><font color='red'><form:errors
							path='name' /></font></td>
			</tr>

			<tr>
				<td><form:label path="description">Section Description</form:label></td>
				<td><form:input path="description" /><font color='red'><form:errors
							path='description' /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<h4>${error}</h4>
	<a href="/educational/admin/courses/${courseid}/">Click here to add
		sections in this course</a>
	<h3>Current Sections in the Course :</h3>
	<table id="table2">
		<tr>
			<th>SectionID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Operations</th>
			<th>Manage topics</th>
		</tr>
		<c:forEach items="${sections}" var="section">

			<tr>
				<td>${section.sectionid}</td>
				<td>${section.name}</td>
				<td>${section.description}</td>
				<td><a
					href="/educational/admin/courses/${courseid}/sectiondelete/${section.sectionid}">Delete</a>
					| <a
					href="/educational/admin/courses/${courseid}/sectionupdate/${section.sectionid}">
						Update</a></td>
				<td><a
					href="/educational/admin/courses/${courseid}/${section.sectionid}/">Click
						here to manage topics</a></td>
			<tr>
		</c:forEach>

	</table>
</body>
</html>