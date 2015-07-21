$(function(){
	var $sc = $("#ShowCoursesTable");
	$.getJSON("./servlet/QueryUnCheckedCoursesServlet",{
	},function(json){
		var sctemp = "" ;
		var courseName = "" ;
		for(var i=0;i<json.length;i=i+1){
			courseName = "<a onclick=OpenCourse("+"'"+json[i].CourseNum+"'"+") style='text-decoration:none;text-align:center'>"
			+ json[i].CourseName+"</a>" ;
			sctemp+="<tr><td>"
			+ courseName + "</td><td>"
			+ json[i].CourseContent + "</td><td>"
			+ json[i].NickName + "</td><td>"
			+ json[i].SubjectName + "</td><td>"
			+ json[i].AudienceNum + "</td><td>"
			+ json[i].Createtime + "</td><td>"
			+ "<button onclick=CheckCourse("+"'"+json[i].CourseNum+"'"+") style='text-decoration:none;text-align:center'>"
			+ "Check" + "</button>" ;
			+ "</td></tr>" ;
		}
		$sc.append( sctemp ) ;
	});
});

function OpenCourse(CourseNum) {
	$("#CoursesBody").load( "jsp/Course/OpenCourse.jsp?CourseNum="+CourseNum ) ;
}
function CheckCourse(CourseNum){
	$.getJSON("./servlet/CheckCourseServlet",{
		CourseNum:CourseNum,
	},function(json){
		if( json.result == 0 ) {
			alert("审核课程成功！") ;
		}else if( json.result == 1 ) {
			alert("审核课程失败！") ;
		}
	});
}