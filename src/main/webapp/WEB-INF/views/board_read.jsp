<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html> 
<html> 
<head> 
	<meta charset="UTF-8"> 
	<title>${boardVO.title} - 글 읽기 </title>
	<link rel="stylesheet" href="/css/table.css">
	<link rel="stylesheet" href="/css/common.css">
</head>
<body>
<div id="container" class="content-wrapper">
	<table>
		<tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>조회수</th><th>작성일자</th>
		<tr class="group-list">
			<td class="group-id">${boardVO.brdId }</td>
			<td class="group-name">${boardVO.title }</td>
			<td class="group-writer">${boardVO.writer }</td>
			<td class="group-view-cnt">${boardVO.viewCnt }</td>
			<td class="group-reg-date">
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regDate}" />
				</fmt:timeZone>
			</td>
		</tr>
	</table>
	<br>
	<div class="group-content">${boardVO.content }</div>
	<div class="button-wrapper">
		<c:if test="${login.nickname == boardVO.writer || login.userType == 'admin' }">
		<button type="button" id="removeBtn" onclick="removeBoard(${cri.page},${cri.perPageNum },${boardVO.brdId })">삭제하기 </button>
		</c:if>
		<c:if test="${login.nickname == boardVO.writer }">
			<button type="button" id="modifyBtn" onclick="modifyBoard(${cri.page},${cri.perPageNum },${boardVO.brdId })">수정하기 </button>
		</c:if>
		<button type="button" id="listBtn" onclick="listBoard(${cri.page},${cri.perPageNum })">목록으로 </button>
	</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/board_read.js"></script>
</body>
</html>