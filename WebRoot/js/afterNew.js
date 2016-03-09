// 上传附件
$(function(){
	$.ajaxSettings.async = false ;
	if( ObjectType == 0 ) {
		$('#newChapterBtn').show() ;
	} else {
		$('#newChapterBtn').hide() ;
	}

});

function saveAccessory() {
	var fileName = $('#fileName').val() ;
	var fileAddress = $('#fileAddress').val() ;
	$.getJSON('./servlet/saveAccessoryServlet',{
			UserNum:LoginUserNum,
			ObjectNum:ObjectNum,
			ObjectType:ObjectType,
			FileName:fileName,
			FileAddress:fileAddress,
	},function(json){
		if ( json.result == 0 ) {			
			$('#blockInit').hide() ;
			$('#blockInit').attr( 'style', 'display: none;' ) ;
			$('#myModal').hide() ;
			$('#myModal').modal('hide') ;
			alert('上传附件成功！') ;
		}
		if( json.result == 1 ) {
			alert('上传附件失败！') ;
		}
	});
}

function loadnewChapter() {
	location.href = "./Chapter/new_chapter.jsp?CourseNum=" + ObjectNum ;
}