
function Home() {
	location.href = "/micromatch/login.jsp" ;
}
function PublishMatch() {
	$("#CBody").load( "/micromatch/jsp/Match/PublishMatch.jsp" ) ;
}
function ShowMyMatches() {
	$("#CBody").load( "/micromatch/jsp/Match/ShowMyMatches.jsp" ) ;
}