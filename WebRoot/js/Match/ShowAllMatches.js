var courseNum = CourseNum ;
$(function(){
		var $sm = $("#ShowMatchesTable");
		$.getJSON("./servlet/QueryAllMatchesServlet",{
		},function(json){
			var smtemp = "" ;
			var matchName = "" ;
			for(var i=0;i<json.length;i=i+1){
				matchName = "<a onclick=ShowMatchInfo("+"'"+json[i].MatchNum+"'"+") style='text-decoration:none;text-align:center'>"
				+ json[i].MatchName+"</a>" ;
				smtemp+="<tr><td>"
					+ matchName + "</td><td>"
					+ json[i].MatchContent + "</td><td>"
					+ json[i].StartTime + "</td><td>"
					+ json[i].EndTime + "</td><td>"
					+ json[i].RegistrationNum + "</td><td>"
					+ "<a onclick=Registration("+"'"+json[i].MatchNum+"'"+") style='text-decoration:none;text-align:center'>"
					+ "Registra" + "</a>" ;
				+ "</tr>" ;
			}
			$sm.append( smtemp ) ;
		});
});
function ShowMatchInfo(MatchNum) {
	$("#AllMatchBody").load( "jsp/Match/MatchInfo.jsp?MatchNum="+MatchNum ) ;
}
function Registration( MatchNum ) {
	$.getJSON("./servlet/RegistrationServlet",{
		MatchNum:MatchNum,
		CourseNum:courseNum,
	},function(json){
		if ( json.result == 0 ) {
			alert( "报名成功！" ) ;
		} else if ( json.result == 1 ) {
			alert( "报名失败！" ) ;
		} else if ( json.result == 2 ) {
			alert( "您已经报名参加这个比赛了！" ) ;
		}
	});
}

function Back() {
	window.history.back() ;
}