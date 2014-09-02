<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Reservation Query</title>
</head>
<body>
	<form method="post">
		Court Name <input type="text" name="courtName" value="${courtName}" />
		<input type="submit" value="Query" />
		<%-- <form:select path="courtName" items="${courtList}" itemValue="0" itemLabel="name" /> --%>
	</form>
	<table border="1">
		<tr>
			<th>Court Name</th>
			<th>Date</th>
			<th>Hour</th>
			<th>Player</th>
		</tr>
		<c:forEach items="${reservations}" var="reservation">
			<tr>
				<td>${reservation.courtName}</td>
				<td><fmt:formatDate value="${reservation.date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${reservation.hour}</td>
				<td>${reservation.player.name}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br /> Handling time : ${handlingTime} ms.
	<br />
	<br /> Locale : ${pageContext.response.locale}
</body>
</html>