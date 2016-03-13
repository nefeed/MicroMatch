var check = 0 ;
$(function(){
	$.ajaxSettings.async = false ;
	$.getJSON('./servlet/QueryUnCheckedCoursesServlet',{
		isCheck:check,
	},function(json){
		var temp = '' ;
		var courseName = '' ;
		for(var i=0;i<json.length;i=i+1){
			var url = '/micromatch/jsp/Course/course.jsp?CourseNum='+json[i].CourseNum
			+ '&ListId=0&UserNum='+LoginUserNum ;
			courseName = '<a href='+url+' style="text-decoration:none;text-align:center">'
			+ json[i].CourseName+'</a>' ;
			temp = '<tr><td>'
			+ courseName + '</td><td>'
			+ json[i].Createtime + '</td><td>'
			+ json[i].CourseContent + '</td><td>'
			+ json[i].SubjectName + '</td><td>'
			+ json[i].NickName + '</td><td>'
			+ '<button class = "chooseBTN" onclick=checkCourse('+json[i].CourseNum+') style="text-decoration:none;text-align:center">'
			+ '审核' + '</button>'
			+ '</td></tr>' + temp ;
		}
		$('#uncheck').append( temp ) ;
		hoverHaveCheck() ;
	});
	
});

function checkCourse( courseNum ) {
	$.getJSON('./servlet/CheckCourseServlet',{
		CourseNum:courseNum,
	},function(json){
		if( json.result == 0 ) {
			alert('成功审核课程：'+courseNum) ;
			location.reload( true ) ;
		} else if( json.result == 1 ) {
			alert('审核失败') ;
		}
	});
}

function hoverUnCheck() {
	check = 0 ;
	$.getJSON('./servlet/QueryUnCheckedCoursesServlet',{
		isCheck:check,
	},function(json){
		var temp = '' ;
		var courseName = '' ;
		for(var i=0;i<json.length;i=i+1){
			var url = '/micromatch/jsp/Course/course.jsp?CourseNum='+json[i].CourseNum
			+ '&ListId=0&UserNum='+LoginUserNum ;
			courseName = '<a href='+url+' style="text-decoration:none;text-align:center">'
			+ json[i].CourseName+'</a>' ;
			temp = '<tr><td>'
			+ courseName + '</td><td>'
			+ json[i].Createtime + '</td><td>'
			+ json[i].CourseContent + '</td><td>'
			+ json[i].SubjectName + '</td><td>'
			+ json[i].NickName + '</td><td>'
			+ '<button class = "chooseBTN" onclick=checkCourse('+json[i].CourseNum+') style="text-decoration:none;text-align:center">'
			+ '审核' + '</button>'
			+ '</td></tr>' + temp ;
		}
		$('#uncheck').append( temp ) ;
	});
}

function hoverHaveCheck() {
	check = 1 ;
	$.getJSON('./servlet/QueryUnCheckedCoursesServlet',{
		isCheck:check,
	},function(json){
		var temp = '' ;
		var courseName = '' ;
		for(var i=0;i<json.length;i=i+1){
			var url = '/micromatch/jsp/Course/course.jsp?CourseNum='+json[i].CourseNum
			+ '&ListId=0&UserNum='+LoginUserNum ;
			courseName = '<a href='+url+' style="text-decoration:none;text-align:center">'
			+ json[i].CourseName+'</a>' ;
			temp = '<tr><td>'
			+ courseName + '</td><td>'
			+ json[i].Createtime + '</td><td>'
			+ json[i].CourseContent + '</td><td>'
			+ json[i].SubjectName + '</td><td>'
			+ json[i].NickName + '</td><td>'
			+ '<button class = "chooseBTN" onclick=checkCourse('+json[i].CourseNum+') style="text-decoration:none;text-align:center">'
			+ '撤回' + '</button>'
			+ '</td></tr>' + temp ;
		}
		$('#checked').append( temp ) ;
	});
}