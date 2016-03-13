
function CreateCourse() {
	$("#TBody").load( "/micromatch/jsp/Course/CreateCourse.jsp" ) ;
}

function ShowMyCourses() {
	$("#TBody").load( "/micromatch/jsp/Course/ShowMyCourses.jsp" ) ;
}

function Home() {
	location.href = "/micromatch/login.jsp" ;
}