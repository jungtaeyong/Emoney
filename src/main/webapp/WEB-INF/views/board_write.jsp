<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html> 
<head> 
	<meta charset="UTF-8"> 
	<title>KIVIEW &mdash; 글쓰기</title> 
	<!-- css --> 
	 <!-- js --> 
	 <!-- SmartEditor2 라이브러리 -->
	 <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js" charset="utf-8"></script> 
	 <script type="text/javascript" src="/naver_editor/js/HuskyEZCreator.js"></script> 
 </head> 
 <body>
 	
 	<form id="writeForm" action="/board/writePost" method="post">
		<input type="hidden" id="formTitle" name="title"/>
		<input type="hidden" id="formContent" name="content"/>
	</form>
 		<h2>글 쓰기</h2>
 		<h4>작성자 : ${login.nickname }</h4>
		<label for="title">제목</label>
		<input type="text" id="title" name="title" />
	 	<textarea id="smarteditor" row="10" cols="100"></textarea>
	 	<button type="button" id="writeBtn">글쓰기 </button>
	 	<button type="button" id="listBtn">목록으로 </button>
 	
	<form id="editor_upimage" name="editor_upimage" method="post" enctype="multipart/form-data" onSubmit="return false;">
		
	</form>
	
	<button type="button" id="test">test</button>
	
	<script type="text/javascript">
	var editor_object = [];
	
	$(function(){
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: editor_object,
			elPlaceHolder: "smarteditor",
			sSkinURI: "/naver_editor/SmartEditor2Skin.html",
			htParams: {
				//툴바 사용 여부
				bUseToolbar : true,
				//입력창 크기 조절바 사용 여부
				bUseVerticalResizer : true,
				//모드 탭 사용 여부 (editor| html | text)
				bUseModeChanger : true,
				
			},
			//성공 콜백 인듯
			//fOnAppLoad : function(){
			//	editor_object.getById["smarteditor"].exec("SET_IR", ["<span style='font-size: 18pt; color: rgb(255, 0, 0);'> 입니다.</span>"]);
			//}

		});
		
		$("#savebutton").click(function(){
			
		
			// 이 부분에 에디터 validation 검증
			
			//폼 submit
			$("$frm").submit();
		})
	})
 
</script>
<script src="/js/board_write.js"></script>
 </body>
 
 </html>



