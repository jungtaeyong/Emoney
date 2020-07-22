<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html> 
<head> 
	<meta charset="UTF-8"> 
<title>Insert title here</title>

</head>
<body>
	<table>
		<tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>조회수</th><th>작성일자</th>
		<tr>
			<td>${boardVO.brdId }</td>
			<td>${boardVO.title }</td>
			<td>${boardVO.writer }</td>
			<td>${boardVO.viewCnt }</td>
			<td>${boardVO.regDate }</td>
		</tr>
	</table>
	<br>
	<h3>글내용</h3>
	<div>${boardVO.content }</div>
	<c:if test="${login.nickname == boardVO.writer || login.userType == 'admin' }">
		<button type="button" id="removeBtn" onclick="removeBoard(${cri.page},${cri.perPageNum },${boardVO.brdId })">삭제하기 </button>
	</c:if>
	<c:if test="${login.nickname == boardVO.writer }">
		<button type="button" id="modifyBtn" onclick="modifyBoard(${cri.page},${cri.perPageNum },${boardVO.brdId })">수정하기 </button>
	</c:if>
	<button type="button" id="listBtn" onclick="listBoard(${cri.page},${cri.perPageNum })">목록으로 </button>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/board_read.js"></script>
</body>
</html>