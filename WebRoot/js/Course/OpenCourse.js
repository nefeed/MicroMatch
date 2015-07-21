var SubId = "" ;
var CourseType = "" ;
$("#UpdateButton").val("UpdateCourse") ;
$(function(){
	$.getJSON("./servlet/QueryCourseByCourseNumServlet",{
		CourseNum:CourseNum,
	},function(json){
		$("#CourseName").val(json.CourseName) ;
		$("#CourseContent").val(json.CourseContent) ;
		$("#SubjectName").val(json.SubjectName) ;
		$("#AudienceNum").val(json.AudienceNum) ;
		$("#SubscriptionNum").val(json.SubscriptionNum) ;
		$("#Createtime").val(json.Createtime) ;
			$("#ChpaterBody").load("jsp/Chapter/ShowCoursesChapter.jsp?CourseNum="+CourseNum+"&UserNum="+LoginUserNum);
	});
});

function PollVote() {
	var Poll = $("#Poll").val() ;
	$.getJSON("./servlet/PollVoteServlet",{
		UserNum:LoginUserNum,
		CourseNum:CourseNum,
		Poll:Poll,
	},function(json){
		if( json.result == 0 ) {
			alert("评分课程成功！") ;
		}else if( json.result == 1 ) {
			alert("评分课程失败！") ;
		}else if( json.result == 2 ) {
			alert("您已经评分过本次课程了！") ;
		}
	});
}
function ShowVideo() {
	location.href = "jsp/Chapter/ChapterVideo.jsp?ChapterNum="+ChapterNum+"&UserNum="+LoginUserNum ;
}
function Back() {
	window.history.back() ;
}