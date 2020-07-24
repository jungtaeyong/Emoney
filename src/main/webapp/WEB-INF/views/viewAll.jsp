<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>viewAll Title</title>
<style>
	table{
		border: 1px solid #111111;
	}
	th, td {
		border: 1px solid #111111;
	}
</style>
</head>
<body>
	<h1>View All!</h1>
	
	<table>
		<tr><th>accntId</th><th>nickname</th><th>name</th><th>userType</th><th>phone</th><th>id</th><th>sPasswd</th><th>lastLogin</th>
	<c:forEach items="${list }" var="list">
		<tr>
			<td>${list.accntId }</td>
			<td>${list.nickname }</a></td>
			<td>${list.name }</td>
			<td>${list.userType }</td>
			<td>${list.phone }</td>
			<td>${list.id }</td>
			<td>${list.sPasswd }</td>
			<td>
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.lastLogin}" />
				</fmt:timeZone>
			</td>
		</tr>
	</c:forEach>
	
</body>
</html>