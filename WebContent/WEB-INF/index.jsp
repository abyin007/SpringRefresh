<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Court Reservation - Home</title>
</head>
<body>
	<table align="left">
		<tr>
			<td><a href="welcome?language=en_US">Welcome Page</a></td>
		</tr>
		<tr>
			<td><a href="welcome?language=de">Begrüßungs-Seite</a></td>
		</tr>
		<tr>
			<td><a href="reservationForm">Reservation Form</a></td>
		</tr>
		<tr>
			<td><a href="reservationQuery">Reservation Query</a></td>
		</tr>
		<tr>
			<td><a href="testRedirect">Redirect to welcome page</a></td>
		</tr>
		<tr>
			<td><a href="about">About</a></td>
		</tr>
	</table>
	<%-- <c:redirect url="/hello" /> --%>
</body>
</html>