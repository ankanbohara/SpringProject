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
<title>Manage Course</title>
</head>
<body>
<h4><a href = "/educational/admin/">Click here to return to main page</a></h4>
	<form:form method="POST" modelAttribute="course"
		action="/educational/admin/coursesave/">
		<table>

			<tr>
				<td><form:label path="id">CourseID</form:label></td>
				<c:if test="${not empty update}">
				<td><form:input path="id" value="${courseid}" readonly="true"/><font color='red'><form:errors
							path='id' /></font></td>
				</c:if>
				
				<c:if test="${empty update}">
				<td><form:input path="id" value=""/><font color='red'><form:errors
							path='id' /></font></td>
				</c:if>
			</tr>


			<tr>
				<td><form:label path="name">Course Name</form:label></td>
				<td><form:input path="name" /><font color='red'><form:errors
							path='name' /></font></td>
			</tr>

			<tr>
				<td><form:label path="description">Course Description</form:label></td>
				<td><form:input path="description" /><font color='red'><form:errors
							path='description' /></font></td>
			</tr>

			<tr>

				<td>Active</td>
				<td><form:select path="active">
						<form:option value="1" label="--- Select ---" />
						<form:option value="1" label="Active" />
						<form:option value="0" label="Incative" />
					</form:select><font color='red'><form:errors path='active' /></font></td>
			</tr>

			<tr>
				<td><form:label path="price">Price</form:label></td>
				<td><form:input path="price" /><font color='red'><form:errors
							path='price' /></font></td>
			</tr>


			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<h4>${error}</h4>
	<a href="/educational/admin/courses">Click here to add more courses</a>
	<h3>Current Courses :</h3>
	<table id="table2">
		<tr>
			<th>CourseId</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Number of Students Enrolled</th>
			<th>Operations</th>
			<th>Sections</th>
		</tr>
		<c:forEach items="${courses}" var="course">

			<tr>
				<td>${course.id}</td>
				<td>${course.name}</td>
				<td>${course.description}</td>
				<td>${course.price}</td>
				<td>${course.noOfEnrolled}</td>
				<td><a href="/educational/admin/coursedelete/${course.id}">Delete</a>
					| <a href="/educational/admin/courseupdate/${course.id}"> Update</a></td>
				<td><a href="/educational/admin/courses/${course.id}">Click here to manage sections</a></td>
			<tr>
		</c:forEach>

	</table>
</body>
</html>