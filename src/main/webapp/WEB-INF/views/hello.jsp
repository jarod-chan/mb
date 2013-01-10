<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<table>
		<tr>
			<td>uuid</td><td>reanlname</td>
		</tr>
		<c:forEach var="map" items="${userMap}">
			<tr>
				<td>${map.uuid}</td><td>${map.realname}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
