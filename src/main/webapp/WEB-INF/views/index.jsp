<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Emoney Community</title>
	 <!-- Bootstrap Core CSS -->
  <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
  <link href="/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="/css/stylish-portfolio.min.css" rel="stylesheet">
  <!-- Bootstrap Core CSS -->
  <style>
	  	.carousel-item {
	  height: 100vh;
	  min-height: 350px;
	  background: no-repeat center center scroll;
	  -webkit-background-size: cover;
	  -moz-background-size: cover;
	  -o-background-size: cover;
	  background-size: cover;
	  }
  </style>

</head>

<body id="page-top">

	  <!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
	  <div class="container">
	    <a class="navbar-brand" href="#">Emoney Community</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
	          <span class="navbar-toggler-icon"></span>
	        </button>
	    <div class="collapse navbar-collapse" id="navbarResponsive">
	      <ul class="navbar-nav ml-auto">
	        <li class="nav-item active">
	          <a class="nav-link" href="#">홈으로
	                <span class="sr-only">(current)</span>
	              </a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">서비스 이용안내</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">로그인</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">회원가입</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>

	<header>
	  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
	    <ol class="carousel-indicators">
	      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
	      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
	      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
	      <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
	    </ol>
	    <div class="carousel-inner" role="listbox">
	      <!-- Slide One - Set the background image for this slide in the line below -->
	      <div class="carousel-item active" style="background-image: url('/img/portfolio-1.jpg')">
	        <div class="carousel-caption d-none d-md-block">
	          <h2 class="display-4">수익률 게시판</h2>
	          <p class="lead">회원님들의 수익을 자랑해보세요!<br> 투자 비법과 정보를 공유할 수 있는 공간입니다!</p>
	        </div>
	      </div>
	      <!-- Slide Two - Set the background image for this slide in the line below -->
	      <div class="carousel-item" style="background-image: url('/img/portfolio-2.jpg')">
	        <div class="carousel-caption d-none d-md-block">
	          <h2 class="display-4">전문가 무료상담</h2>
	          <p class="lead">주식을 처음 접하시는 분에게 추천합니다.<br>입문부터 관리까지 모든 것을 알려드립니다.</p>
	        </div>
	      </div>
	      <!-- Slide Three - Set the background image for this slide in the line below -->
	      <div class="carousel-item" style="background-image: url('/img/portfolio-3.jpg')">
	        <div class="carousel-caption d-none d-md-block">
	          <h2 class="display-4">서비스 상담</h2>
	          <p class="lead">전화가 껄끄러우신가요?<br>채팅으로 편하게 물어보세요!</p>
	        </div>
	      </div>
	      <!-- Slide Four -->
		  <div class="carousel-item" style="background-image: url('/img/portfolio-4.jpg')">
	        <div class="carousel-caption d-none d-md-block">
	          <h2 class="display-4">마음의 소리</h2>
	          <p class="lead">회원님의 의견을 보내주세요!<br>이머니는 회원님의 의견을 반영합니다.</p>
	        </div>
	      </div>	      
	    </div>
	    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
	          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	          <span class="sr-only">Previous</span>
	        </a>
	    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
	          <span class="carousel-control-next-icon" aria-hidden="true"></span>
	          <span class="sr-only">Next</span>
	        </a>
	  </div>
	</header>

	<!-- Page Content -->
	<section class="py-5">
	  <div class="container">
	    <h1 class="display-4">Full Page Image Slider</h1>
	    <p class="lead">The background images for the slider are set directly in the HTML using inline CSS. The images in this snippet are from <a href="https://unsplash.com">Unsplash</a>, taken by <a href="https://unsplash.com/@joannakosinska">Joanna Kosinska</a>!</p>
	  </div>
	</section>

	  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="/js/stylish-portfolio.min.js"></script>

</body>

</html>
