$(function(){
	$.getJSON("./servlet/QueryChapterByCourseNumServlet",{
		CourseNum:CourseNum,
	},function(json){
		var $sc = $("#ShowChaptersTable");
		var sctemp = "" ;
		var chapterName = "" ;
		for(var i=0;i<json.length;i=i+1){
			chapterName = "<a onclick=ShowChapterInfo("+"'"+json[i].ChapterNum+"'"+") style='text-decoration:none;text-align:center'>"
			+ json[i].ChapterName+"</a>" ;
			sctemp+="<tr><td>"
			+ chapterName + "</td><td>"
			+ json[i].ChapterContent + "</td>"
			+ "</tr>" ;
		}
		$sc.append( sctemp ) ;
	});
});
function ShowChapterInfo(ChapterNum) {
	location.href = "/micromatch/jsp/Chapter/ChapterInfo.jsp?ChapterNum="+ChapterNum+"&UserNum="+UserNum ;
}
function AddChapter() {
	$("#ToolBody").load( "/micromatch/jsp/Chapter/AddChapter.jsp?CourseNum="+CourseNum ) ;
}
function Back() {
		window.history.back() ;
}