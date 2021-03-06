<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reservation Form</title>
<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>
</head>
<body>
	<form:form method="post" modelAttribute="reservation">
		<form:errors path="*" cssClass="error" />
		<table>
			<tr>
				<td>Court Name</td>
				<td><form:input path="courtName" /></td>
				<td><form:errors path="courtName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><form:input path="date" /></td>
				<td><form:errors path="date" cssClass="error" /></td>
				<td>Give: 01/14/2008 for getting execption</td>
			</tr>
			<tr>
				<td>Hour</td>
				<td><form:input path="hour" /></td>
				<td><form:errors path="hour" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>