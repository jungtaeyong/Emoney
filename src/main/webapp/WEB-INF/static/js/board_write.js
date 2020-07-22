$('#writeBtn').click(function(){
   var title=$('#title').val();
   var content=editor_object.getById["smarteditor"].getIR();
   
   var dd1=$('#smarteditor').text();
   if(title == "") {
      alert("제목을 입력해주세요.");
      return;
   }
   if(content == "") {
      alert("내용을 입력해주세요.");
      return;
   }

   $("#formTitle").val(title);
   $("#formContent").val(content);
   console.log(title);
   console.log(content);
   $("#writeForm").submit();

});

$('#listBtn').click(function(){
	document.location.href='/board/list';
})

$('#test').click(function(){
	console.log("aa");
	
	fetch("https://x1.choicestock.co.kr/x1_stock/choice_api/A?api_key=K_zTp4dxld30uyEEe08GO7") // (/test.json) 호출 (GET)
	.then(e => e.json()) // 비동기
	.then(e => console.log(e))
	
//	const xhr = new XMLHttpRequest();
//	xhr.open("GET", "https://x1.choicestock.co.kr/x1_stock/choice_api/A?api_key=K_zTp4dxld30uyEEe08GO7", true);
////	xhr.setRequestHeader(header, token);
//	
//	xhr.send();
//	xhr.onload = function (data) {
//		if(xhr.status == 200 || xhr.status == 201) {
//			console.log("aaaa");
//			console.log(data);
//		}
//	}
//	$.ajax({
//	       type:'GET',
//	       url: 'https://x1.choicestock.co.kr/x1_stock/choice_api/A?api_key=K_zTp4dxld30uyEEe08GO7',
//	       dataType: 'json',
//	       
//	       success: function(data){
//	          console.log("success");
//	          console.log(data);
//	       },
//	       error: function(request, status, error){
//	    	   alert("에러가 발생했습니다. 잠시후에 다시 시도해주세요");
//	       }  
//	    })
	
})

