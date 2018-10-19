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
<title>Manage Video Material</title>
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
	<c:if test="${material=='Video Links'}">
		<form:form method="POST"
			action="/educational/admin/courses/${courseid}/${sectionid}/${topicid}/videomaterial/materialsave">
			<h3>${courseName}</h3>
			<h4>Course Code-${courseid}</h4>
			<h4>${sectionName}</h4>
			<h4>${topicName}</h4>

			<table>
				<tr>
					<td><label>Enter Video Link</label></td>
					<td><input type="text" name="link"></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
		<a href="/educational/admin/courses/${courseid}/${sectionid}">Click
			here to go to topic page</a>
		<table id="table2">
			<tr>
				<th>Link</th>
				<th>Operation</th>
			</tr>
			<c:forEach items="${videolinks}" var="link">

				<tr>
					<td><a href="${link}">YouTube Video</a></td>
					<td><a
						href="/educational/admin/courses/${courseid}/${sectionid}/${topicid}/videomaterial/materialdelete/${link}">Delete</a>
				<tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${material == 'Tests'}">

		<form:form method="POST"
			action="/educational/admin/courses/${courseid}/${sectionid}/${topicid}/videomaterial/materialsave">
			<h3>${courseName}</h3>
			<h4>Course Code-${courseid}</h4>
			<h4>${sectionName}</h4>
			<h4>${topicName}</h4>

			<table>
				<tr>
					<td><label>Enter Video Link</label></td>
					<td><input type="text" name="link"></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
		<a href="/educational/admin/courses/${courseid}/${sectionid}">Click
			here to go to topic page</a>
		<table id="table2">
			<tr>
				<th>Link</th>
				<th>Operation</th>
			</tr>
			<c:forEach items="${videolinks}" var="link">

				<tr>
					<td><a href="${link}">YouTube Video</a></td>
					<td><a
						href="/educational/admin/courses/${courseid}/${sectionid}/${topicid}/videomaterial/materialdelete/${link}">Delete</a>
				<tr>
			</c:forEach>
		</table>

	</c:if>



</body>
</html>