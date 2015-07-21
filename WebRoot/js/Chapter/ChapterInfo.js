$(function(){
	$.getJSON("./servlet/QueryChapterByChapterNumServlet",{
		ChapterNum:ChapterNum,
	},function(json){
		$("#ChapterName").val(json.ChapterName) ;
		$("#ChapterContent").val(json.ChapterContent) ;
		$("#ChapterVideo").val(json.ChapterVideo) ;
		$("#Createtime").val(json.Createtime) ;
		$("#DownloadAccessoryBody").load( "jsp/Accessory/ShowAccessory.jsp?ObjectType="+1+"&ObjectNum="+ChapterNum+"&UserNum="+UserNum ) ;
	});
});

function ChangeUpload() {
	location.href = "jsp/Accessory/UploadAccessory.jsp?ObjectType=1"+"&ObjectNum="+ChapterNum+"&UserNum="+UserNum ;
}

function ShowVideo() {
	location.href = "jsp/Chapter/ChapterVideo.jsp?ChapterNum="+ChapterNum+"&UserNum="+UserNum ;
}

function Back() {
	window.history.back() ;
}