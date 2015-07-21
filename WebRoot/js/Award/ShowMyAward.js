$(function(){
	var $at = $("#AwardTable") ;
	$.getJSON("./servlet/QueryMatchAwardServlet",{
		MatchNum:MatchNum,
	},function(json){
		var attemp = "" ;
		var CourseName = "" ;
		for( var i = 0 ; i < json.length ; i++ ) {
			CourseName = "<a onclick=ShowCourseInfo("+"'"+json[i].CourseNum+"'"
				+")style='text-decoration:none;text-align:center'>"
				+ json[i].CourseName+"</a>" ;
			attemp += "<tr><td>"
				+ json[i].AwardName + "</td><td>"
				+ CourseName + "</td></tr>" ;
		}
		$at.append( attemp ) ;
	});
	
});

function ShowCourseInfo(CourseNum) {
	location.href = "jsp/Course/CourseInfo.jsp?CourseNum="+CourseNum ;
}