var originNickname;
var originPhone;

function login(){
	var id = $("#id").val();
	var sPasswd = $("#sPasswd").val();
	
	if(id==""){
		$("#idMsg").text("아이디를 입력해주세요");
		$("#id").focus();
		return;
	}
	if(sPasswd==""){
		$("#pwMsg").text("비밀번호를 입력해주세요");
		$("#sPasswd").focus();
		return;
	}
	var RSAModulus = $("#RSAModulus").val();
	var RSAExponent = $("#RSAExponent").val();
	var rsa = new RSAKey();
	rsa.setPublic(RSAModulus, RSAExponent);
	id=rsa.encrypt(id);
	sPasswd=rsa.encrypt(sPasswd);
	$("#loginFormId").val(id);
	$("#loginFormPw").val(sPasswd);
	$("#loginForm").submit();
}

$("#loginBtn").click(function(){
	login();
})

$("#sPasswd").keyup(function(key){
	if(key.keyCode==13){
		login();
	}
})

$("#myBtn").click(function(){
	$("#myModal").css("display","block");
})


function naverBtn(url){
	window.open(url, 'naver_login', 
			'height=700, width=600,menubar=no,directories=no,resizable=no,status=no,scrollbars=yes'); 
	return false;
}

$('#nicknameChkBtn').click(function(){
   var nickname=$('#nickname').val();
   if(nickname == "") {
      alert("닉네임 입력이 잘못되었습니다. ");
      return;
   }
   $.ajax({
      type:'GET',
      url: './nicknameCheck/'+nickname,
      success: function(data){
         console.log("success nick");
         if(data===0){
            alert("사용가능 합니다.");
            $('#nicknameMsg').text("사용가능한 닉네임 입니다.");
            $('#nicknameMsg').css('color','green');
            originNickname=nickname;
         }else{
            alert("이미 존재하는 닉네임 입니다.");
            $('#nicknameMsg').text("이미 존재하는 닉네임 입니다.");
            $('#nicknameMsg').css('color','red');
         }
      },
      error: function(request, status, error){
        console.log("#idChkBtn ajax - get error ---");
        console.log("error : "+error);
        console.log("request : "+request);
        console.log("status : "+status);
      }
      
   })
});

$("#number1").keyup (function () {
    var limit = $(this).attr("maxlength");
    if (this.value.length >= limit) {
        $('#number2').focus();
        return false;
    }
});


$('#naverLoginBtn').click(function(){
   var nickname=$('#nickname').val();
   var phone=$('#number0').val() +"-"+ $('#number1').val()+"-"+$('#number2').val();

   if(nickname == ""){
      alert("닉네임 입력이 잘못되었습니다. ");
   } else if(nickname != originNickname) {
      alert("닉네임 중복확인을 해주세요.");
   } else if(phone == "") {
      alert("핸드폰 번호를 입력해주세요. ");
   } else {
      var form={
            id:session.response.id,
            name:session.response.name,
            userType:"naver",
            nickname:nickname,
            phone:phone
         }
      console.log(form);
      $.ajax({
         type:'POST',
         url: '/user/naverSignup',
         dataType: 'json',
         contentType:"application/json",
         data: JSON.stringify(form),
         
         success: function(data){
            console.log("success signup");
            if(data==1){
            	alert("회원가입이 완료되었습니다.");
            	if(dest!=''){
            		document.location.href=dest;
            	}else{
            		document.location.href="/user/index";
            	}
            }else{
            	alert("에러가 발생했습니다. 잠시후에 다시 시도해주세요");
            }
         },
         error: function(request, status, error){
   
         }  
      })

   }
});
