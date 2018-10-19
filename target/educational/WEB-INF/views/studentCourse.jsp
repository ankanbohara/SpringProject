<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoursePage</title>
</head>
<body>


	<form action="/educational/submit" method="post">
		<p>
			Course id: <input type="text" name="id" />
		</p>
		<p>
			Course name :<input type="text" name="name" />
		</p>
		<p>
			Active :<input type="text" name="active" />
		</p>
		<p>
			Description :<input type="text" name="description" />
		</p>
		<p>
			Price :<input type="text" name="price" />
		</p>
		<input type="submit" value="Submit this form" />
	</form>
</body>
</html>