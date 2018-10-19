<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Enquiry</title>
</head>
<body>
<form:form method="POST" modelAttribute="getEnquiry"
		action="/educational/enquiry/success">
		<table>

			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /><font color='red'><form:errors path='name' /></font></td>
			</tr>
			
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" /><font color='red'><form:errors path='email' /></font></td>
			</tr>
			
			<tr>
				<td><form:label path="address">Address</form:label></td>
				<td><form:input path="address" /><font color='red'><form:errors path='Address' /></font></td>
			</tr>
			
			<tr>
				<td><form:label path="contact">Contact</form:label></td>
				<td><form:input path="contact" /><font color='red'><form:errors path='contact' /></font></td>
			</tr>
			
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input path="description" /><font color='red'><form:errors path='description' /></font></td>
			</tr>
			
			
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>