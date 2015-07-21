$(function(){
	var $sc = $("#ShowCoursesTable");
	$.getJSON("./servlet/QueryAllCourseServlet",{
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
			+ "<a onclick=AttendCourse("+"'"+json[i].CourseNum+"'"+") style='text-decoration:none;text-align:center'>"
			+ "AttendCourse" + "</a>" ;
			+ "</td></tr>" ;
		}
		$sc.append( sctemp ) ;
	});
});

function OpenCourse(CourseNum) {
	$("#CoursesBody").load( "jsp/Course/OpenCourse.jsp?CourseNum="+CourseNum ) ;
}
function AttendCourse(CourseNum){
	$.getJSON("./servlet/AttendCourseServlet",{
		UserNum:UserNum,
		CourseNum:CourseNum,
	},function(json){
		if( json.result == 0 ) {
			alert("订阅课程成功！") ;
		}else if( json.result == 1 ) {
			alert("订阅课程失败！") ;
		}else if( json.result == 2 ) {
			alert("您已经订阅过这门课程了！")
		}
	});
}