<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>회원가입</title>
<link rel="stylesheet" href="/css/form.css">
<style>
	#signup_table{
		border:1px solid;
		margin : 0 auto;
		width:50%;
	}
</style>

</head>

<!-- join_form.html -->

<body>
<div id="wrapper" class="content-wrapper">
		<label for="id">아이디</label>
		<input type="text" id="id" name="id" />
		<button type="button" id="idChkBtn">중복확인 </button>
		<p style="display:inline">4자이상 12자이하 영문,숫자(띄어쓰기, 특수문자 불가)</p>
		<p id="idMsg" style="display:inline; color:red;">중복검사를 진행해 주세요.</p>
		<br/>
		<label for="pw">비밀번호</label>
		<input type="password" id="pw" name="pw" placeholder="영문 및 숫자만 입력 가능합니다. " />
		<p id="pwMsg"  style="display:inline;color:gray;">영어 대/소문자, 숫자, 특수문자 중 2가지 이상 조합 6자~12자(띄어쓰기 불가)</p>
		<br>
		<input type="password" id="pwCheck" name="pwCheck" placeholder="비밀번호를 다시 입력해주세요." />
		<p id="pwCheckMsg"></p>
		<label for="name">성함</label>
		<input type="text" id="name" name="name" />
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
		<button type="button" id="submit">회원가입</button>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/js/user_signup.js"></script>
</body>
</html>


