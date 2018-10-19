<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to admin area</title>
</head>
<body>
<h4>Welcome ${username}</h4>
<h4><a href="/educational/logout">Logout</a></h4>
	<ul>
		
		<li><h4><a href="/educational/admin/courses/">Click here to manage courses</a></h4></li>
		<li><h4><a href="/educational/admin/tests/">Click here to manage tests</a></h4></li>
		<li><h4><a href="/educational/admin/viewEnquiry/">Click here to manage enquiries</a></h4></li>
	
	</ul>
</body>
</html>