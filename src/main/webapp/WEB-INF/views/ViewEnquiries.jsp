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
<title>Manage Enquiries</title>
</head>
<body>
	<h4>
		<a href="/educational/logout">Logout</a>
	</h4>
	<h4>
		<a href="/educational/admin/">Click here to return to home page</a>
	</h4>
	<c:if test="${empty enquiries}">
		<h4>No enquiries</h4>
	</c:if>
	<c:if test="${not empty enquiries}">

		<h3>Enquiries :</h3>
		<table id="table2">
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Address</th>
				<th>Contact</th>
				<th>Description</th>
				<th>Responded</th>
				<th>Toggle</th>
				<th>Remove</th>
			</tr>
			<c:forEach items="${enquiries}" var="enquiry">

				<tr>
					<td>${enquiry.name}</td>
					<td>${enquiry.email}</td>
					<td>${enquiry.address}</td>
					<td>${enquiry.contact}</td>
					<td>${enquiry.description }</td>
					<td><c:if test="${enquiry.responded==true}">
							<c:out value="Yes"></c:out>
						</c:if> <c:if test="${enquiry.responded==false}">
							<c:out value="No"></c:out>
						</c:if></td>
					<td><a
						href="/educational/admin/viewEnquiry/update/${enquiry.id}">Responded</a></td>
					<td><a
						href="/educational/admin/viewEnquiry/delete/${enquiry.id}">Remove</a></td>
				<tr>
			</c:forEach>

		</table>
	</c:if>
</body>
</html>