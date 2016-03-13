
$(function(){
	$("#ShowAllCourseBody").load("/micromatch/jsp/Course/ShowAllCourses.jsp?UserNum="+LoginUserNum) ;
});

function Home() {
	location.href = "/micromatch/login.jsp" ;
}
