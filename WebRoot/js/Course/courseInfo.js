var SubId = "" ;
var fid = 0 ;
$(function(){
	$.ajaxSettings.async = false ;
	$.getJSON("./servlet/QueryCourseByCourseNumServlet",{
		CourseNum:CourseNum,
	},function(json){
		$('#coverPic').attr( 'src' , json.CoverPicture ) ;
		$('#teacherNick').html( json.NickName ) ;
		$('#courseName').html(json.CourseName) ;
		$('#courseContent').html(json.CourseContent) ;
		$('#subject').html(json.SubjectName) ;
		$('#audienceNum').html(json.AudienceNum) ;
		$('#createtime').html(json.Createtime) ;
		$('#period').html( json.Period ) ;
		SubId = json.SubId ;
	});
	showAttendBTN() ;
	loadChapter() ;
	loadComment() ;
});

/**
 * 进入课程
 */
function openCourse() {
	location.href = 'course.jsp?CourseNum='+CourseNum+'&ListId=0' ;
}


/**
 * 遍历章节
 */
function loadChapter(){
	var $cl = $('#chapterList') ;
	$cl.html('') ;
	var period = parseInt( $('#period').html() ) ;
	var temp = '' ;
	if( period != 0 ) {
		$.getJSON('./servlet/QueryChapterByCourseNumServlet',{
			CourseNum:CourseNum,
		},function(json){
			var content = '' ;
			var url = '' ;
			var chaName = '' ;
			for ( var i = 0 ; i < json.length ; i++ ) {
				chaName = json[i].ChapterName ;
				url = 'course.jsp?CourseNum=' + CourseNum
				+ '&ListId=' + i ;
				var index = i + 1 ;
				temp += '<tr>'
	            	+ '<td><a href='+url+'><input type="button" class="main_button1" value="第'+index+'章" /></a></td>'
	            	+ '<td><em ><a class = "whata" href='+url+'>'+chaName+'</a></em></td>'
					+ '<td><a href='+url+'><input type="button" class="main_button2" /></a></td>' 
					+ '<td><x>主讲人：</x></td>'
					+ '<td><x>'+ $('#teacherNick').html() +'</x></td>'
					+ '</tr>' ;
			}
		});
	} else {
		var temp = '<div align="center" style="padding-top:30px ;">该课程未创建任何章节！</div>' ;
	}
	$cl.html( temp ) ;
}

/**
 * 发表评论
 */
function publishComment(){
	if ( LoginUserNum == '' || LoginUserType == undefinded ) {
		alert( '请登入后继续尝试！' ) ;
		location.href = 'login.jsp' ;
	} else {
		var publishCommentContent = $('#publishCommentContent').val() ;
		if( publishCommentContent.length >= 250 ){
			alert('您输入的评论超过了125个文字的上限，请修改后重新发表！')
		} else {
			$.getJSON("./servlet/PublishCommentServlet",{
				CommentContent:publishCommentContent,
				UserNum:LoginUserNum,
				ObjectNum:CourseNum,
				ObjectType:0,
				PID:fid,
			},function(json){
				if( json.result == 0 ) {
					$('#publishCommentContent').val('') ;
					$('#commentBody').empty();
					loadComment() ;
					fid = 0 ;
				}else {
					alert("发表评论失败！") ;
					fid = 0 ;
				}
			});		
		} 		
	}
}

/**
 * 加载评论
 */
function loadComment() {
	$.getJSON( './servlet/QueryCommentByObjectTypeAndNumServlet',{
		ObjectNum:CourseNum,
		ObjectType:0,
	},function(json){
		var commentTemp = '' ;
		if( json != null ){
			for ( var i = 0 ; i < json.length ; i ++ ) {
				var replytemp = '' ;
				replytemp = loadReply( i , json[i].ID ) ;
				commentTemp += 
					'<div class="talk_2_1">' 
						+ '<img src='+json[i].UserPicture+'></img>'
						+ '<div class="talk_center">'
							+ '<em><a>' + json[i].CommentContent + '</a><br /><br /></em>'
							+ '<i><span><a class = "default-uinfo" id="comNick'+ i +'" >' + json[i].NickName + '</a></span>    <a class = "default-uinfo" >发表时间：' + json[i].CommentTime + '</a></i>'
						+ replytemp
					+ '</div>' ;
			}						
		} else {
			commentTemp = '<div class = "scrBody" align="center"><a class = "showCResult" target="view_window" >您将成为第一个评论者！</a></div>'
		}
		$('#commentBody').append(commentTemp) ;
	});
}
/**
 * 加载回复
 * @param index
 * @param commentID
 * @returns {String}
 */
function loadReply( index , commentID ) {
	var all = '' ;
	var replyFunction = 'onclick="reply(' + index + ',' + commentID + ')" ' ;
	$.getJSON( './servlet/showReplyServlet',{
		PID:commentID,
	},function(json){
		if( json != null ){
			var replyNum = '<div class="talk_right"><a ' + replyFunction + ' href="javascript:;" class="talk_r_em">' 
				+ json.length 
				+ '</a><a ' + replyFunction + ' href="javascript:;" >点击回复</a><label class="collapseBtn" data-toggle="collapse" href="#collapse' + index + '" aria-expanded="false" aria-controls="collapse'+index+'">展开<span class="caret"></span></label></div>' ;
			var temp = '' ;
			for( var i = 0 ; i < json.length ; i ++ ){
				temp +=
					'<div class="well" style="width:800px ; height:auto ;"><div style="width:700px; margin-bottom:30px; padding-bottom:20px;">'
					+ '<img src="' + json[i].UserPicture + '" width="50" height="50" />'
					+ '<div class="talk_center"><em>'
					+ json[i].CommentContent 
					+ '</em><i><span>' 
					+ json[i].NickName 
					+ '</span>' 
					+ json[i].CommentTime 
					+ '</i></div>'
					+ '</div></div>' ;
			}
			all = '<div class="collapse" id="collapse'+index+'">'
				+ temp + '</div></div>'
				+ replyNum ;
		} else {
			all = '</div><div class="talk_right"><a class="talk_r_em" ' + replyFunction + ' href="javascript:;" >0</a><a ' + replyFunction + ' href="javascript:;" >点击回复</a></div>' ;
		}
	}) ;
	return all ;
}

/**
 * 点击回复某人的按钮后反馈
 * @param index
 * @param commentId
 */
function reply( index , commentId ) {
	if( LoginNickName == $('#comNick'+index).html() ) {
		alert('请不要回复自己！') ;
	} else {
		fid = commentId ;
		var $comcontent = $('#publishCommentContent') ;
		$comcontent.val( ' 回复 ' + $('#comNick'+index).html() + ' ： ' ) ;
		$comcontent.focus() ;
		javascript:document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight ;		
	}
}

/**
 * 登录判断用户是否登录，为登录用户开放功能
 */
function showAttendBTN() {
	var $attendBtn = $('#attendBTN') ;
	if( LoginUserNum == "" ){
		alert('登录可以享受更多精彩内容！') ;
	} else {
		$.getJSON("./servlet/haveAttendedServlet",{
			UserNum:LoginUserNum,
			CourseNum:CourseNum,
		},function(json){
			if( json.result == 0 ) {
				$attendBtn.val('订阅课程') ;
			} else if (json.result == 1 ) {
				$attendBtn.val('取消订阅') ;
			}
		});
	}
	isMyCourse() ;
}

/**
 * 订阅确认
 */
function attendConfirm() {
	if( LoginUserNum == "" ){
		alert('请您先尝试登录，再继续操作！') ;
	} else {
		if( $('#attendBTN').val() == '订阅课程' ) {
			$.getJSON("./servlet/AttendCourseServlet",{
				UserNum:LoginUserNum,
				CourseNum:CourseNum,
			},function(json){
				if( json.result == 0 ) {
					showAttendBTN() ;
				} else if (json.result == 1 ) {
					alert( '订阅失败！' ) ;			
				} else if (json.result == 2 ) {
					alert( '您已经订阅过本课程！' ) ;
					showAttendBTN() ;
				}
			});			
		} else if ( $('#attendBTN').val() == '取消订阅' ) {
			$.getJSON("./servlet/DeleteAttendCourseServlet",{
				UserNum:LoginUserNum,
				CourseNum:CourseNum,
			},function(json){
				if( json.result == 0 ) {
					showAttendBTN() ;
				} else if (json.result == 1 ) {
					alert( '取消订阅失败！' ) ;			
				}
			});	
		}
	}
}

/**
 * 判断是否为本人创建的课程
 */
function isMyCourse() {
	var temp = '' ;
	var $obg = $('#optionBtnGroup') ;
	$obg.html('') ;
	$.getJSON('./servlet/isMyCourseServlet',{
		UserNum:LoginUserNum,
		CourseNum:CourseNum,
	},function(json){
		if( json.result == 0 ) {
			temp = '<button type="button" class="btn btn-info" onclick="updateCourse()">修改课程</button>'
					+ '<button type="button" class="btn btn-info" onclick="addNewChapter()">新增章节</button>'
					+ '<button type="button" class ="btn btn-info" data-toggle="modal" data-target="#myModal" >上传附件</button>'
					+ '<button type="button" class="btn btn-info" onclick = "registMatchBtn()" data-toggle="modal" data-target="#MatchListBody" >曾获荣誉</button>' ;
		} else if (json.result == 1 ) {
			temp = '<button type="button" class="btn btn-info" onclick = "registMatchBtn()" data-toggle="modal" data-target="#MatchListBody" >曾获荣誉</button>' ;
		}
		$obg.html(temp) ;
	});	
}

/**
 * 新增内容按钮事件
 */
function addNewBtn() {
	location.href = "jsp/afterNew.jsp?ObjectNum="+CourseNum+"&ObjectType=0" ;
}

/**
 * 历史荣誉按钮事件
 */
function registMatchBtn() {
	$.getJSON('./servlet/queryHistoryAwardByCourseNumServlet',{
		CourseNum:CourseNum,
	},function(json){
		var temp = '' ;
		if ( json != null ) {
			$('#MatchListTable').html('<tr class="info" ><td>奖项名称</td><td>比赛名称</td><td>开始时间</td><td>结束时间</td><td>操作</td></tr>') ;
			for( var i = 0 ; i < json.length ; i ++ ) {
				url = 'jsp/Match/MatchInfo.jsp?MatchNum=' + json[i].MatchNum ;
				temp += '<tr>'
					+ '<td>'+json[i].AwardName+'</td>'
					+ '<td><a href='+url+'>'+json[i].MatchName+'</a></td>'
					+ '<td>'+json[i].StartTime+'</td>'
					+ '<td>'+json[i].EndTime+'</td>'
					+ '<td><a class = "btn btn-success" href='+url+'>查看</a></td>'
					+ '</tr>' ;
			}			
		} else {
			$('#MatchListTable').html('<tr class="info" ><td>奖项名称</td><td>比赛名称</td><td>开始时间</td><td>结束时间</td></tr>') ;
			temp = '<tr>'
				+ '<td>无获奖记录</td>'
				+ '<td>无获奖记录</td>'
				+ '<td>0000-00-00</td>'
				+ '<td>0000-00-00</td>'
				+ '</tr>' ;
		}
		$('#MatchListTable').append( temp ) ;
	});
}

/**
 * 修改课程按钮，点击进入修改课程界面
 */
function updateCourse() {
	location.href = "jsp/Course/update_course.jsp?CourseNum="+CourseNum ;
}
function saveAccessory() {
	var fileName = $('#fileName').val() ;
	var fileAddress = $('#fileAddress').val() ;
	$.getJSON('./servlet/saveAccessoryServlet',{
			UserNum:LoginUserNum,
			ObjectNum:CourseNum,
			ObjectType:0,
			FileName:fileName,
			FileAddress:fileAddress,
	},function(json){
		if ( json.result == 0 ) {			
			$('#myModal').hide() ;
			$('#blockInit').hide() ;
			$('#blockInit').attr( 'style', 'display: none;' ) ;
		}
		if( json.result == 1 ) {
			alert('上传附件失败！') ;
		}
	});
}

function addNewChapter() {
	location.href = "jsp/Chapter/new_chapter.jsp?CourseNum=" + CourseNum ;
}