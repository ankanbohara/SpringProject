<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<h4>Welcome ${username}</h4>
	<a href="/educational/logout">Logout</a>
	<a href="/educational">Click here to return to home page</a>
	<h1>Available courses</h1>
	<ul>
		<c:forEach items="${listCourse}" var="element">
			<li>
				<h2>${element.name}</h2>
				<p>About Course : ${element.description}</p>
				<p>Price : ${element.price}</p>
				<p><a href = "/educational/enrollment/${userid}/${element.id}/">Click here to enroll</a></p>
			</li>
		</c:forEach>
	</ul>
	<h1>Enrolled courses</h1>
	<c:choose>
		<c:when test="${course}">
			<ul>
				<c:forEach items="${listEnrolled}" var="element">
					<li>
						<h2><a href="/educational/${userid}/${element.id}">${element.name}</a></h2>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<h2>No courses enrolled</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>