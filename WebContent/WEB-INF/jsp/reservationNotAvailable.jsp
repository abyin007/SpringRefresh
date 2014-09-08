<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Not Available</title>
</head>
<body>
	Your reservation for ${exception.courtName} is not available on
	<fmt:formatDate value="${exception.date}" pattern="yyyy-MM-dd" />
	at ${exception.hour}:00.
</body>
</html>