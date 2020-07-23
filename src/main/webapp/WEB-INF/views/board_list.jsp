<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

 	<meta charset="utf-8">
	<title>게시판 글 목록 </title>
	<link rel="stylesheet" href="/css/table.css">
	<link rel="stylesheet" href="/css/common.css">
	<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js" charset="utf-8"></script> 
</head>
<body>
	
	<h2 class="menu-title">BOARD LIST</h2>
	<div id="container">
		<div class="button-wrapper">
			<button type="button" id="writeBtn">글쓰기 </button>
			<button type="button" id="mainBtn">메인으로 </button>
		</div>
	<table>
		<tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>조회수</th><th>작성일자</th>
		
		<c:forEach items="${list }" var="list">
		<tr class="group-list">
			<td class="group-id">${list.brdId }</td>
			<td class="group-name"><a class="ellip"href="/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&brdId=${list.brdId }">${list.title }</a></td>
			<td class="group-writer">${list.writer }</td>
			<td class="group-view-cnt">${list.viewCnt }</td>
			<td class="group-reg-date">
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.regDate}" />
				</fmt:timeZone>
			</td>
		</tr>
		</c:forEach>			
	
	</table>
	
	<div class="pagination-wrapper">
				<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="page-num"><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			   <li class="page-num<c:if test='${pageMaker.cri.page == idx}'> active</c:if>">
					<a href="list${pageMaker.makeQuery(idx)}">${idx}</a>
				</li>
			   <li></li>
			  </c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li class="page-num"><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
				</c:if>
			</ul>
			</div>
		</div>
	<script src="/js/board_list.js"></script>
</body>
</html>