$(function(){
	$.ajaxSettings.async = false ;
	$.getJSON('./servlet/QueryUnCheckedChaptersServlet',{
		isCheck:0,
	},function(json){
		var temp = '' ;
		var chapterName = '' ;
		for(var i=0;i<json.length;i=i+1){
			chapterName = '<a onclick=checkChapter('+json[i].ChapterNum+') style="text-decoration:none;text-align:center">'
			+ json[i].ChapterName+'</a>' ;
			var index = json[i].ListId + 1 ;
			temp = '<tr><td>'
			+ ChapterName + '</td><td>'
			+ json[i].ChapterContent + '</td><td>'
			+ '第' + index + '章节' + '</td><td>'
			+ '<button class = "chooseBTN" onclick=checkChapter('+json[i].ChapterNum+') style="text-decoration:none;text-align:center">'
			+ '审核' + '</button>'
			+ '</td></tr>' + temp ;
		}
		$('#uncheck').append( temp ) ;
		hoverHaveCheck() ;
	});
	
});

function checkChapter( ChapterNum ) {
	$.getJSON('./servlet/CheckChapterServlet',{
		ChapterNum:ChapterNum,
	},function(json){
		if( json.result == 0 ) {
			alert('成功审核章节：'+ChapterNum) ;
			location.reload( true ) ;
		} else if( json.result == 1 ) {
			alert('审核失败') ;
		}
	});
}

function hoverUnCheck() {
	$.getJSON('./servlet/QueryUnCheckedChaptersServlet',{
		isCheck:0,
	},function(json){
		var temp = '' ;
		var chapterName = '' ;
		for(var i=0;i<json.length;i=i+1){
			chapterName = '<a onclick=checkChapter('+json[i].ChapterNum+') style="text-decoration:none;text-align:center">'
			+ json[i].ChapterName+'</a>' ;
			var index = json[i].ListId + 1 ;
			temp = '<tr><td>'
			+ ChapterName + '</td><td>'
			+ json[i].ChapterContent + '</td><td>'
			+ '第' + index + '章节' + '</td><td>'
			+ '<button class = "chooseBTN" onclick=checkChapter('+json[i].ChapterNum+') style="text-decoration:none;text-align:center">'
			+ '审核' + '</button>'
			+ '</td></tr>' + temp ;
		}
		$('#uncheck').append( temp ) ;
	});
}

function hoverHaveCheck() {
	$.getJSON('./servlet/QueryUnCheckedChaptersServlet',{
		isCheck:1,
	},function(json){
		var temp = '' ;
		var chapterName = '' ;
		for(var i=0;i<json.length;i=i+1){
			chapterName = '<a onclick=checkChapter('+json[i].ChapterNum+') style="text-decoration:none;text-align:center">'
			+ json[i].ChapterName+'</a>' ;
			var index = json[i].ListId + 1 ;
			temp = '<tr><td>'
			+ ChapterName + '</td><td>'
			+ json[i].ChapterContent + '</td><td>'
			+ '第' + index + '章节' + '</td><td>'
			+ '<button class = "chooseBTN" onclick=checkChapter('+json[i].ChapterNum+') style="text-decoration:none;text-align:center">'
			+ '撤回' + '</button>'
			+ '</td></tr>' + temp ;
		}
		$('#checked').append( temp ) ;
	});
}
