$(function(){
	var $sm = $("#ShowMatchesTable");
	$.getJSON("./servlet/QueryMatchByUserNumServlet",{
		UserNum:LoginUserNum,
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
			+ json[i].RegistrationNum + "</td>"
			+ "</tr>" ;
		}
		$sm.append( smtemp ) ;
	});
});
function PublishMatch() {
	$("#CBody").load( "jsp/Match/PublishMatch.jsp" ) ;
}
function Back() {
	window.history.back() ;
}
function ShowMatchInfo(MatchNum) {
	$("#CBody").load( "jsp/Match/MatchInfo.jsp?MatchNum="+MatchNum ) ;
}