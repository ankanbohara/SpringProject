<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${course.name}</title>
</head>
<body>
	<a href="/educational/logout">Logout</a>
	<h4>${error}</h4>
	<h3>Description</h3>
	<p>${course.description}</p>
	<h3>Sections</h3>
	<ul>
		<c:forEach items="${section}" var="element">
			<li><a
				href="/educational/${userid}/${courseid}/${element.sectionid}">${element.name}</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>