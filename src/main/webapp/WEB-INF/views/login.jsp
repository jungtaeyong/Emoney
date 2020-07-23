<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>로그인 페이지 </title>
  <!-- Custom CSS -->
  <link rel="stylesheet" href="/css/form.css">
  <link href="/css/modal.css" rel="stylesheet">
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>

<body>

<div id="wrapper" class="content-wrapper">
	<form id="loginForm" action="/user/loginPost" method="post">
		<input type="hidden" id="loginFormId" name="id"/>
		<input type="hidden" id="loginFormPw" name="sPasswd"/>
	</form>
		<h3 class="title">로그인</h3>
			<span style="color: red;"></span>
			<input type="text" id="id" name="id" placeholder="아이디" required>
			<p id="idMsg" style="display:inline;color:red"></p>
			<input type="password" id="sPasswd" name="sPasswd" placeholder="비밀번호" required>
			<p id="pwMsg" style="display:inline;color:red"></p>
			<button type="button" id="loginBtn">로그인 하기</button>
			<input type="hidden" id="RSAModulus" value="${RSAModulus}"/>
			<input type="hidden" id="RSAExponent" value="${RSAExponent}"/>
	
	<c:url var="signUpUrl" value="./signup" />
	<p class="sign-up-msg">아직 계정이 없으신가요? <a href="${signUpUrl }" class="sign-up-btn" >회원가입</a></p>
	<p class="divider"><span>또는</span></p>
	<a id="naver_id_login" style="cursor: pointer;" onclick="naverBtn('${url}')"><img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a>
<button id="myBtn">Open Modal</button>
</div>

 <!-- The Modal -->
 <div id="myModal" class="modal">

 <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <div id="wrapper">
    <label for="nickname">닉네임</label>
		<input type="text" id="nickname" name="nickname" />
		<button type="button" id="nicknameChkBtn">중복확인 </button>
		<p id="nicknameMsg" style="display:inline; color:red;">중복검사를 진행해 주세요. </p>
		<br>
		<label for="phone">핸드폰 </label>
		<select id="number0">
		    <option value="010" selected="selected">010</option>
		    <option value="011">011</option>
		    <option value="016">016</option>
		    <option value="017">017</option>
		    <option value="018">018</option>
		    <option value="019">019</option>
		    <option value="070">070</option>
		</select> -
		<input type="text" id="number1" name="number1" maxlength='4' /> -
		<input type="text" id="number2" name="number2" maxlength='4' />
		<br>
		<br>
		<button type="button" id="naverLoginBtn">회원가입</button>
		</div>
   </div>
 </div>
	<%
	String oauth = "false";
	//if(session.getAttribute("oauth") != null) oauth = (String)session.getAttribute("oauth");
	%>

	<script type="text/javascript">
		var dest='${dest}';
		console.log('dest:'+dest);
		var session='${oauth}';
		var naverId,naverName;
		if(session) {
			session = JSON.parse(session);
			$("#myModal").css("display","block");
			console.log(session.response.id);
		}
		console.log(session);
		
		$(".close").click(function(){
			$("#myModal").css("display","none");
			<%session.removeAttribute("oauth"); %>
		})
	</script>

	<!-- RSA 자바스크립트 라이브러리 -->
	<script type="text/javascript" src="/js/jsbn.js"></script>
	<script type="text/javascript" src="/js/rsa.js"></script>
	<script type="text/javascript" src="/js/prng4.js"></script>
	<script type="text/javascript" src="/js/rng.js"></script>
	<!-- RSA 암호화 처리 스크립트 -->

	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
	<script src="/js/user_login.js"></script>
</body>

</html>