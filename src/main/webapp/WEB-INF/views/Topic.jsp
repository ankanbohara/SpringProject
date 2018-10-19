<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
<meta charset="UTF-8">
<title>${section.name}</title>
</head>
<body>
	<a href="/educational/logout">Logout</a>
	<h2 style="text-decoration: underline;">${section.name}</h2>
	<c:forEach items="${topicMap}" var="topic">
		<ul>
			<li>${topic.key.name}</li>
			<c:forEach items="${topic.value}" var="material">
				<ul>
					<li>${material.name}</li>

					<c:if test="${material.name=='Tests'}">
						<ul>
							<c:set var="link" value="${ linkMap[material]}" />
							<c:if test="${empty scoreMap[link] }">
								<p>You have not attempted this test
								<p>
								<li><a
									href="/educational/${userid}/${topic.key.courseid}/${topic.key.sectionid}/${topic.key.id}/${link}">Click
										here to attempt</a></li>
							</c:if>
							<c:if test="${not empty scoreMap[link]}">
								<table>
									<tr>
										<th>Score</th>
										<th>Date</th>
										<th>Correct</th>
										<th>Not Attempted</th>
										<th>Max Score</th>
									</tr>
									<c:forEach items="${scoreMap[link]}" var="scores">
										<tr>
											<td>${scores.score}</td>
											<td>${scores.time }</td>
											<td>${scores.correct }</td>
											<td>${scores.notattempted}</td>
											<td>${scores.maxScore}</td>
										<tr>
									</c:forEach>
								</table>
								<a
									href="/educational/${userid}/${topic.key.courseid}/${topic.key.sectionid}/${topic.key.id}/${link}">Click
									here to reattempt</a>
							</c:if>

						</ul>
					</c:if>

				</ul>
			</c:forEach>
		</ul>
	</c:forEach>
</body>
</html>