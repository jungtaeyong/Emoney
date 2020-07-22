var originId;
var originPw;
var originPwCheck;
var originName;
var originNickname;
var originPhone;

var regExpId = /^[0-9a-zA-Z]{4,12}$/;
var regExpPw = /^(((?=.*[A-Z])((?=.*[a-z])|(?=.*[0-9])|(?=.*[\W\_])))|((?=.*[a-z])((?=.*[0-9])|(?=.*[\W\_])))|((?=.*[0-9])(?=.*[\W\_])))[\S]{6,12}$/;
var regExpName =/^[가-힣]{1,20}$/;


$("#number1").keyup (function () {
    var limit = $(this).attr("maxlength");
    if (this.value.length >= limit) {
        $('#number2').focus();
        return false;
    }
});

$('#pw').keyup(function(){
   if(regExpPw.test(this.value)){
	   $('#pwMsg').css('color','green');
	   $('#pwMsg').text('사용가능한 비밀번호 입니다.');
   }else{
      $('#pwMsg').css('color','gray');
      $('#pwMsg').text('영어 대/소문자, 숫자, 특수문자 중 2가지 이상 조합 6자~12자(띄어쓰기 불가)');
   }
});

$('#idChkBtn').click(function(){
   var id=$('#id').val();
   if(!regExpId.test(id)) {
      alert("아이디 입력이 잘못되었습니다. ");
      return;
   } 
   $.ajax({
      type:'GET',
      url: './idCheck/'+id,
      
      success: function(data){
         console.log("success idchk");
         if(data===0){
            alert("사용가능 합니다.");
            $('#idMsg').text("사용가능한 아이디 입니다.");
            $('#idMsg').css('color','green');
            originId=id;
         }else{
            alert("이미 존재하는 아이디 입니다.");
            $('#idMsg').text("이미 존재하는 아이디 입니다.");
            $('#idMsg').css('color','red');
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

$('#submit').click(function(){
   var id=$('#id').val();
   var name=$('#name').val();
   var pw=$('#pw').val();
   var pwCheck=$('#pwCheck').val();
   var nickname=$('#nickname').val();
   var phone=$('#number0').val() +"-"+ $('#number1').val()+"-"+$('#number2').val();

   if(!regExpId.test(id) ) {
      alert("아이디 입력이 잘못되었습니다. ");
   } else if(originId != id) {
      alert("아이디 중복확인을 해주세요.")
   } else if(!regExpPw.test(pw) ) {
      alert("비밀번호 입력이 잘못되었습니다. ");
   } else if(pw != pwCheck){
      alert("비밀번호가 다릅니다.")
   } else if(!regExpName.test(name)) {
      alert("이름 입력이 잘못되었습니다. ");
   } else if(nickname == ""){
      alert("닉네임 입력이 잘못되었습니다. ");
   } else if(nickname != originNickname) {
      alert("닉네임 중복확인을 해주세요.");
   } else if(phone == "") {
      alert("핸드폰 번호를 입력해주세요. ");
   } else {
      var form={
            id:id,
            sPasswd:pw,
            name:name,
            userType:"origin",
            nickname:nickname,
            phone:phone
         }
      console.log(form);
      $.ajax({
         type:'POST',
         url: '/user/signup',
         dataType: 'json',
         contentType:"application/json",
         data: JSON.stringify(form),
         
         success: function(data){
            console.log("success signup");
            if(data==1){
            	alert("회원가입이 완료되었습니다. 로그인을 해주세요!");
            	document.location.href="/user/login";
            }else{
            	alert("에러가 발생했습니다. 잠시후에 다시 시도해주세요");
            }
         },
         error: function(request, status, error){
   
         }  
      })

   }
});



//$('#idChk').click(function(){
//   var form={
//            id:3,
//            name:"태용"
//         }
//   $.ajax({
//      type:'POST',
//      url: 'http://localhost:8081/user/idCheck',
//      dataType: 'json',
//      contentType:"application/json",
//      data: JSON.stringify(form),
//      
//      success: function(data){
//         console.log("success");
//      },
//      error: function(request, status, error){
//
//      }  
//   })
//   console.log("hi");
//});



