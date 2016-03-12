var MatchName = "" ;
var MatchContent = "" ;
var StartTime = "" ;
var EndTime = "" ;
function PublishConfirm() {
	MatchName = $("#MatchName").val() ;
	MatchContent = $("#MatchContent").val() ;
	StartTime = $("#StartTime").val() ;
	EndTime = $("#EndTime").val() ;
	if( MatchName.length == 0 ){
		alert("比赛名称不能为空！") ;
	} else if( MatchContent.length == 0 ) {
		alert("比赛概述不能为空！") ;
	} else if( StartTime.length == 0 ) {
		alert("比赛开始时间不能为空！") ;
	} else if( EndTime.length == 0 ) {
		alert("比赛结束时间不能为空！") ;
	} else {
		$.getJSON("./servlet/PublishMatchServlet",{
			UserNum:LoginUserNum,
			MatchName:MatchName,
			MatchContent:MatchContent,
			StartTime:StartTime,
			EndTime:EndTime,
		},function(json){
			if ( json.result == 0 ) {
				$("#body").load("../UserType/College.jsp") ;
			} else if ( json.result == 1 ) {
				alert( "发布比赛失败！" ) ;
			}
		});
	}
}
function Cancel() {
		window.history.back() ;
}