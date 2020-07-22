function listBoard(page, perPageNum) {
	var url="/board/list?page="+page+"&perPageNum="+perPageNum;
	document.location.href=url;
}

function removeBoard(page, perPageNum, brdId) {
	
	var form ={brdId: brdId};
	console.log("remove")
	
	$.ajax({
       type:'POST',
       url: '/board/removePost',
       contentType:"application/json",
       data: JSON.stringify(form),
       
       success: function(data){
          console.log("success removeBoard");
          if(data=="deleted"){
          	alert("글을 삭제했습니다. 리스트로 돌아갑니다.");
          	var url="/board/list?page="+page+"&perPageNum="+perPageNum;
        	document.location.href=url;
          }else{
          	alert("에러가 발생했습니다. 잠시후에 다시 시도해주세요");
          }
       },
       error: function(request, status, error){
    	   alert("에러가 발생했습니다. 잠시후에 다시 시도해주세요");
       }  
    })
}

function modifyBoard(page, perPageNum, brdId) {
	
	var url="/board/modifyPage?page="+page+"&perPageNum="+perPageNum+"&brdId="+brdId;
	document.location.href=url;
}