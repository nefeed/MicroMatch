
$(function(){
	$("#ShowAllCourseBody").load("../Course/ShowAllCourses.jsp?UserNum="+LoginUserNum) ;
});

function Home() {
	location.href = "../../login.jsp" ;
}
