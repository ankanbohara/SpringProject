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
<title>Manage Topics</title>
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
	<h4><a href="/educational/admin/courses/${courseid}/">Click here to go
		to topic page</a></h4>
	<form:form method="POST" modelAttribute="question"
		action="/educational/admin/courses/${courseid}/${sectionid}/${topicid}/questionsave">
		<h3>${courseName}</h3>
		<h4>Course Code-${courseid}</h4>
		<h4>${sectionName}</h4>
		<h4>${topicName}</h4>

		<table>

			<!--  tr>
				<td><form:label path="id">TopicID</form:label></td>
				<td><form:input path="id" value="${tid}"
						readonly="true" /><font color='red'><form:errors
							path='id' /></font></td>
			</tr-->


			<tr>
				<td><form:label path="id">QuestionID</form:label></td>
				<c:if test="${not empty update}">
					<td><form:input path="id" value="${question.id}"
							readonly="true" /><font color='red'><form:errors
								path='id' /></font></td>
				</c:if>

				<c:if test="${empty update}">
					<td><form:input path="id" value="-1" readonly="true" /><font
						color='red'><form:errors path='id' /></font></td>
				</c:if>
			</tr>

			<tr>
				<td><form:label path="description">Question Description</form:label></td>
				<td><form:input path="description"
						value="${question.description}" /><font color='red'><form:errors
							path='description' /></font></td>
			</tr>

			<tr>
				<td><form:label path="topicid">Topic ID</form:label></td>
				<td><form:input path="topicid" value="${topicid}"
						readonly="true" /><font color='red'><form:errors
							path='topicid' /></font></td>
			</tr>

			<tr>
				<td><form:label path="option1">Option 1</form:label></td>
				<td><form:input path="option1" value="${question.option1}" /><font
					color='red'><form:errors path='option1' /></font></td>
			</tr>

			<tr>
				<td><form:label path="option2">Option 2</form:label></td>
				<td><form:input path="option2" value="${question.option2}" /><font
					color='red'><form:errors path='option2' /></font></td>
			</tr>

			<tr>
				<td><form:label path="option3">Option 3</form:label></td>
				<td><form:input path="option3" value="${question.option3}" /><font
					color='red'><form:errors path='option3' /></font></td>
			</tr>

			<tr>
				<td><form:label path="option4">Option 4</form:label></td>
				<td><form:input path="option4" value="${question.option4}" /><font
					color='red'><form:errors path='option4' /></font></td>
			</tr>


			<tr>
				<td><form:label path="correctans">Correct Answer</form:label></td>
				<td><form:input path="correctans"
						value="${question.correctans}" /><font color='red'><form:errors
							path='correctans' /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<a
		href="/educational/admin/courses/${courseid}/${sectionid}/${topicid}/">Click
		here to add questions</a>
	<h4>${error}</h4>
	<h3>Current Questions in the Section :</h3>
	<table id="table2">
		<tr>
			<th>Description</th>
			<th>Option1</th>
			<th>Option2</th>
			<th>Option3</th>
			<th>Option4</th>
			<th>TopicID</th>
			<th>CorrectAnswer</th>
			<th>Operation</th>
		</tr>
		<c:forEach items="${questions}" var="question">

			<tr>
				<td>${question.description}</td>
				<td>${question.option1}</td>
				<td>${question.option2 }</td>
				<td>${question.option3 }</td>
				<td>${question.option4 }</td>
				<td>${question.topicid }</td>
				<td>${question.correctans}</td>
				<td><a
					href="/educational/admin/courses/${courseid}/${sectionid}/${question.topicid}/questiondelete/${question.id}">Delete</a>
					| <a
					href="/educational/admin/courses/${courseid}/${sectionid}/${question.topicid}/questionupdate/${question.id}">
						Update</a></td>
			<tr>
		</c:forEach>

	</table>
</body>
</html>