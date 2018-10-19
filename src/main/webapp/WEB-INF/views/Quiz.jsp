<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz</title>
</head>
<body>
	<form:form action="/educational/${userid}/${courseid}/${topicid}/${testid}/result" method="POST">
		<h2>Details</h2>
		<ul>
			<li>Test is of : ${test.duration} minutes</li>
			<li>Description : ${test.description}</li>
			<li>Max Score : ${test.maxScore}</li>
		</ul>
		<table>
			<c:if test="${!empty listquestions}">
				<%
					int i = 0;
				%>
				<c:forEach var="q" items="${listquestions}">
					<tr>
						<td><c:out value="<%=i + 1%>"></c:out> <c:out value="."></c:out>
							<c:out value="Question :"></c:out> <c:out
								value="${q.description}"></c:out></td>
					</tr>
					<tr>
						<td><input type="radio" name="option[<%=i%>]" value="1">
							<c:out value="${q.option1}"></c:out></td>
					</tr>
					<tr>
						<td><input type="radio" name="option[<%=i%>]" value="2">
							<c:out value="${q.option2}"></c:out></td>
					</tr>
					<tr>
						<td><input type="radio" name="option[<%=i%>]" value="3">
							<c:out value="${q.option3}"></c:out></td>
					</tr>
					<tr>
						<td><input type="radio" name="option[<%=i%>]" value="4">
							<c:out value="${q.option4}"></c:out></td>
					</tr>
					<%
						i++;
					%>
				</c:forEach>

				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</c:if>
		</table>
	</form:form>
</body>
</html>