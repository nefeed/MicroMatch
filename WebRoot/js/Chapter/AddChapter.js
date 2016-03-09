
function Back() {
	window.history.back() ;
}

function Confirm() {
	var ChapterName = $("#ChapterName").val() ;
	var ChapterContent = $("#ChapterContent").val() ;
	var ListID = $("#ListID").val() ;
	if( ChapterName.length == 0 ){
		alert("章节名称不能为空！") ;
	} else if( ChapterContent.length == 0 ) {
		alert("章节概述不能为空！") ;
	} else if( ListID.length == 0 ) {
		alert("章节顺序不能为空！") ;
	} else {
		$.getJSON("./servlet/AddChapterServlet",{
		CourseNum:CourseNum,
		ChapterName:ChapterName,
		ChapterContent:ChapterContent,
		ListID:ListID,
	},function(json){
		if ( json.result == 0 ) {
			alert( "章节创建成功！" ) ;
			location.href = "../Chapter/ChapterInfo.jsp?ChapterNum="+json.ChapterNum+"&UserNum="+LoginUserNum ;
		} else if ( json.result == 1 ) {
			alert( "章节创建失败！" ) ;
		}
	});
}
}