function listBoard(page, perPageNum) {
	var url="/board/list?page="+page+"&perPageNum="+perPageNum;
	document.location.href=url;
}

function cancelModify(page, perPageNum, brdId) {
	var url="/board/readPage?page="+page+"&perPageNum="+perPageNum+"&brdId="+brdId;
	document.location.href=url;
}

function modifyBoard(page, perPageNum, brdId){
   var title=$('#title').val();
   var content=editor_object.getById["smarteditor"].getIR();
   console.log("sdd");
   if(title == "") {
      alert("제목을 입력해주세요.");
      return;
   }
   if(content == "") {
      alert("내용을 입력해주세요.");
      return;
   }
   
   $("#formBrdId").val(brdId);
   $("#formTitle").val(title);
   $("#formContent").val(content);
   $("#modifyForm").submit();

//   var form={
//           title : title,
//           content : content,
//           writer : writer,
//           brdId : brdId
//        }
//   console.log(form);
//   $.ajax({
//       type:'POST',
//       url: '/board/modifyPost',
//       dataType: 'json',
//       contentType:"application/json",
//       data: JSON.stringify(form),
//       
//       success: function(data){
//          console.log("success modifyBoard");
//          if(data==1){
//          	alert("글수정을 완료했습니다.");
//          	var url="/board/readPage?page="+page+"&perPageNum="+perPageNum+"&brdId="+brdId;
//        	document.location.href=url;
//          	
//          }else{
//          	alert("에러가 발생했습니다. 잠시후에 다시 시도해주세요");
//          }
//       },
//       error: function(request, status, error){
// 
//       }  
//   })
}