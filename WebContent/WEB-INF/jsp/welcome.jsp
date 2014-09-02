<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- comment entry is the default message -->
<title><spring:message code="welcome.title" />
<%-- <spring:message code="welcome.title" text="Welcome" /> --%>
</title>
</head>
<body>
	<h2>
	<spring:message code="welcome.message" />
	<!-- comment entry is the default message -->
		<%-- 		<spring:message code="welcome.message"
			text="Welcome to Court Reservation System" /> --%>
	</h2>
	Today is
	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />
	.
	<br />
	<br /> Handling time : ${handlingTime} ms.
	<br />
	<br /> Locale : ${pageContext.response.locale}

</body>
</html>