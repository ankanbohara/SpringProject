<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />

<spring:url var="js" value="/resources/js" />

<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>20 Minute Genius | Home</title>
<%@include file="./shared/homecss.jsp" %>
<meta charset="utf-8">
<%@include file="./shared/homeprop.jsp"%>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="50">

	<!-- Navigation Bar -->
	<%@include file="./shared/navbar.jsp"%>
	<!-- Navbar close -->

	<%@include file="home.jsp"%>

	<!-- Container (About Us section) -->
	<%@include file="about.jsp"%>
	<!-- End About Us -->

	<!-- WHY 20 Minute Genius Starts -->
	<%@include file="why20mg.jsp"%>
	<!-- Why 20 Minute Genius Ends -->

	<!-- <Current Courses Begin> -->
	<%@include file="courses.jsp"%>
	<!-- <Current Courses End> -->

	<!-- Container (Contact Section) -->
	<%@include file="./shared/contact.jsp"%>
	<!-- End Contact -->
	<table>
		<c:forEach items="${list}" var="item">
			<tr>
				<td><c:out value="${item.name}" /></td>
			</tr>
		</c:forEach>
	</table>
	<script>
		$(document).ready(
				function() {
					// Initialize Tooltip
					$('[data-toggle="tooltip"]').tooltip();

					// Add smooth scrolling to all links in navbar + footer link
					$(".navbar a, footer a[href='#myPage']").on('click',
							function(event) {

								// Make sure this.hash has a value before overriding default behavior
								if (this.hash !== "") {

									// Prevent default anchor click behavior
									event.preventDefault();

									// Store hash
									var hash = this.hash;

									// Using jQuery's animate() method to add smooth page scroll
									// The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
									$('html, body').animate({
										scrollTop : $(hash).offset().top
									}, 900, function() {

										// Add hash (#) to URL when done scrolling (default click behavior)
										window.location.hash = hash;
									});
								} // End if
							});
				})
	</script>

</body>
</html>
