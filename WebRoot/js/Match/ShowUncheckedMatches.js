$(function(){
		var $sm = $("#ShowMatchesTable");
		$.getJSON("./servlet/QueryUnCheckedMatchesServlet",{
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
					+ "<button onclick=CheckMatch("+"'"+json[i].MatchNum+"'"+") style='text-decoration:none;text-align:center'>"
					+ "Check" + "</button>" ;
				+ "</td></tr>" ;
			}
			$sm.append( smtemp ) ;
		});
});
function ShowMatchInfo(MatchNum) {
	$("#MatchBody").load( "./MatchInfo.jsp?MatchNum="+MatchNum ) ;
}
function ShowMatchInfo(MatchNum) {
	$("#AllMatchBody").load( "./MatchInfo.jsp?MatchNum="+MatchNum ) ;
}
function CheckMatch( MatchNum ) {
	$.getJSON("./servlet/CheckMatchServlet",{
		MatchNum:MatchNum,
	},function(json){
		if ( json.result == 0 ) {
			alert( "审核通过！" ) ;
		} else if ( json.result == 1 ) {
			alert( "审核失败！" ) ;
		}
	});
}

function Back() {
	window.history.back() ;
}