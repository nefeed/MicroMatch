
$(function(){
	$("#ShowAllCourseBody").load("jsp/Course/ShowAllCourses.jsp?UserNum="+LoginUserNum) ;
});

function Home() {
	location.href = "Login.jsp" ;
}
