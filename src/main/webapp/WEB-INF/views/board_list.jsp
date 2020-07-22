<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>

 	<meta charset="utf-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js" charset="utf-8"></script> 
</head>
<body>
	
	
	<table>
		<tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>조회수</th><th>작성일자</th>
		
		<c:forEach items="${list }" var="list">
		<tr>
			<td>${list.brdId }</td>
			<td><a href="/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&brdId=${list.brdId }">${list.title }</a></td>
			<td>${list.writer }</td>
			<td>${list.viewCnt }</td>
			<td>${list.regDate }</td>
		</tr>
		</c:forEach>			
	
	</table>
	
	<button type="button" id="writeBtn">글쓰기 </button>
	<button type="button" id="mainBtn">메인으로 </button>
	
	<div>
	 <ul>
	  <c:if test="${pageMaker.prev}">
	   <li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
	  </c:if> 
	  
	  <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	   <li><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
	  </c:forEach>
	    
	  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	   <li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
	  </c:if> 
	 </ul>
	</div>
		
	<script src="/js/board_list.js"></script>
</body>
</html>