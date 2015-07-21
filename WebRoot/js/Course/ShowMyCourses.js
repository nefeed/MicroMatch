
$(function(){
	$.getJSON("./servlet/QueryCourseByUserNumServlet",{
		UserNum:LoginUserNum,
	},function(json){
		var $sc = $("#ShowCoursesTable");
		var sctemp = "" ;
		var courseName = "" ;
		for(var i=0;i<json.length;i=i+1){
			courseName = "<a onclick=ShowCourseInfo("+"'"+json[i].CourseNum+"'"+") style='text-decoration:none;text-align:center'>"
			+ json[i].CourseName+"</a>" ;
			sctemp+="<tr><td>"
			+ courseName + "</td><td>"
			+ json[i].CourseContent + "</td><td>"
			+ json[i].SubjectName + "</td><td>"
			+ json[i].AudienceNum + "</td><td>"
			+ json[i].Createtime + "</td>"
			+ "</tr>" ;
		}
		$sc.append( sctemp ) ;
	});
});
function CreateCourse() {
	$("#TBody").load( "jsp/Course/CreateCourse.jsp" ) ;
}
function Back() {
	window.history.back() ;
}
function ShowCourseInfo(CourseNum) {
	$("#TBody").load( "jsp/Course/CourseInfo.jsp?CourseNum="+CourseNum ) ;
}