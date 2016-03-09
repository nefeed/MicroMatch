$(function(){
	$.getJSON("./servlet/QueryUnCheckedChaptersServlet",{
	},function(json){
		var $sc = $("#ShowChaptersTable");
		var sctemp = "" ;
		var chapterName = "" ;
		for(var i=0;i<json.length;i=i+1){
			chapterName = "<a onclick=ShowChapterInfo("+"'"+json[i].ChapterNum+"'"+") style='text-decoration:none;text-align:center'>"
			+ json[i].ChapterName+"</a>" ;
			sctemp+="<tr><td>"
			+ chapterName + "</td><td>"
			+ json[i].ChapterContent + "</td><td>"
			+ "<button onclick=CheckChapter("+"'"+json[i].ChapterNum+"'"+") style='text-decoration:none;text-align:center'>"
			+ "Check" + "</button>" ;
			+ "</td></tr>" ;
		}
		$sc.append( sctemp ) ;
	});
});
function CheckChapter(ChapterNum){
	$.getJSON("./servlet/CheckChapterServlet",{
		ChapterNum:ChapterNum,
	},function(json){
		if( json.result == 0 ) {
			alert("审核章节成功！") ;
		}else if( json.result == 1 ) {
			alert("审核章节失败！") ;
		}
	});
}
function ShowChapterInfo(ChapterNum) {
	location.href = "../Chapter/ChapterInfo.jsp?ChapterNum="+ChapterNum+"&UserNum="+UserNum ;
}
function Back() {
		window.history.back() ;
}