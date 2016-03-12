// JavaScript Document
var chapterNum = '' ;
var fid = 0 ;
$(function() {
	$.ajaxSettings.async = false ;
	showAttendBTN() ;
	$.getJSON('./servlet/QueryCourseByCourseNumServlet',{
		CourseNum:CourseNum,
	},function(json){
		$('#c1').text( json.CourseName ) ;
		$('#c2').text( json.CourseName ) ;
		$('#c3').text( json.CourseName ) ;
		$('#c1').attr( 'href' , './jsp/Course/courseInfo.jsp?CourseNum=' + CourseNum ) ;
		$('#c2').attr( 'href' , './jsp/Course/courseInfo.jsp?CourseNum=' + CourseNum ) ;
		$('#c3').attr( 'href' , './jsp/Course/courseInfo.jsp?CourseNum=' + CourseNum ) ;
		$('#content1').text( json.CourseContent ) ;
		$('#period').text( json.Period + '学时' ) ;
		$('#createtime').text( json.Createtime ) ;
		if( json.Period != 0 ){	
			$.getJSON('./servlet/QueryChapterByCourseNumServlet',{
				CourseNum:CourseNum,
			},function(json){
				var temp = '' ;
				var chapterName = '' ;
				var content = '' ;
				var url = '' ;
				for ( var i = 0 ; i < json.length ; i++ ) {
					url = 'course.jsp?CourseNum=' + CourseNum
					+ '&ListId=' + i ;
					var index = i + 1 ;
					temp += '<li>'
						+ '<a href = '+url+' >第'+index+'课时：'+json[i].ChapterName+'</a><br/>'
						+ '<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+json[i].ChapterContent + '</p>'
						+ '<hr color="#eeeeee"/>'
						+ '</li>'
				}
				$('#listChapter').append( temp ) ;
				$.getJSON( './servlet/showChooseChapterServlet',{
					CourseNum:CourseNum,
					ListId:listId,
				},function(json){
					$('#cha1').text( json.ChapterName ) ;
					$('#cha2').text( json.ChapterName ) ;
					$('#cha1').attr( 'href' , 'course.jsp?CourseNum=' + CourseNum + '&ListId=' + listId ) ;
					$('#cha2').attr( 'href' , 'course.jsp?CourseNum=' + CourseNum + '&ListId=' + listId ) ;
					var CuPlayerList = json.ChapterVideo ;
					chapterNum = json.ChapterNum ;
//					var so = new SWFObject("CuSunPlayer/CuPlayerMiniV4.swf","CuPlayerV4","1000","560","9","#000000");
//					so.addParam("allowfullscreen","true");
//					so.addParam("allowscriptaccess","always");
//					so.addParam("wmode","opaque");
//					so.addParam("quality","high");
//					so.addParam("salign","lt");
//					so.addVariable("CuPlayerSetFile","CuSunPlayer/CuPlayerSetFile.xml"); //播放器配置文件地址
//					so.addVariable("CuPlayerFile", CuPlayerList ); //视频文件地址
//					so.addVariable("CuPlayerWidth","1000"); //视频宽度
//					so.addVariable("CuPlayerHeight","560"); //视频高度
//					so.addVariable("CuPlayerAutoPlay","no"); //是否自动播放
//					so.write("CuPlayer");
				//酷播迷你：官方连播代码示例
				var sp =CuPlayerList.split("|");
				var num = sp.length;
				var video_index = 0;
				function getNext(pars)
				{	
					if(video_index < num-1)
					{
						video_index++;
						so.addVariable("JcScpVideoPath",sp[video_index]);
						so.write("CuPlayer");	
					}
					else
					{
						video_index = 0;
						so.addVariable("JcScpVideoPath",sp[video_index]);
						so.write("CuPlayer");	
					}
				}
				function changeStream(CuPlayerFile){
					so.addVariable("JcScpVideoPath",sp[CuPlayerFile]);
					so.write("CuPlayer");	
				}
				
				CuPlayerFile =sp[video_index];
				var so = new SWFObject("CuSunPlayer/player.swf","ply","1000","560","9","#000000");
				so.addParam("allowfullscreen","true");
				so.addParam("allowscriptaccess","always");
				so.addParam("wmode","opaque");
				so.addParam("quality","high");
				so.addParam("salign","lt");
				//播放器设置文件-----------------------------
				so.addVariable("JcScpFile","CuSunPlayer/CuSunV2set.xml");
				//视频文件及略缩图--------------------------
				so.addVariable("JcScpVideoPath",CuPlayerFile);
				so.addVariable("JcScpImg","Images/flashChangfa2.jpg"); 
				so.addVariable("JcScpSharetitle","标题信息"); 
				so.write("CuPlayer");
				loadComment();
				showAccessory(0) ;
				showAccessory(1) ;
				});
			});
		}
	});
});

/**
 * @Description: TODO
 * @param @param objectType   
 * @return void  
 * @throws展示附件
 * @author Gavin
 * @date 2015年5月31日
 */
function showAccessory( objectType ) {
	$.getJSON("./servlet/QueryAccessoryByObjectTypeAndNumServlet",{
		ObjectNum:CourseNum,
		ObjectType:objectType,
	},function(json){
		var attemp = "" ;
		if( json == null || json.length == 0 ){
			attemp = '本课程无附件！' ;
		}else {
			var accessoryName = "" ;
			for(var i=0;i<json.length;i=i+1){
				var url = './servlet/DownloadServlet?ai='+json[i].AccessoryID
				+'&AccessoryAddress='+json[i].AccessoryAddress
				+'&AccessoryName='+json[i].AccessoryName ;
				accessoryName = '<a href=' + url
				+'&UserNum='+LoginUserNum + '>'
				+ json[i].AccessoryName+'</a>' ;
				attemp += '<li>'
					+ accessoryName 
					+ '</li>' ;
			}
		}
		if( objectType == 0 ){
			$('#courseAccessory').append( attemp ) ;
		} else if (objectType == 1 ) {
			$('#chapterAccessory').append( attemp ) ;
		}
	}) ;
}

/**
 * 发表评论
 */
function publishComment(){
	if ( LoginUserNum == '' || LoginUserType == 'undefinded' ) {
		alert( '请登入后继续尝试！' ) ;
		location.href = './login.jsp' ;
	} else {
		var publishCommentContent = $('#publishCommentContent').val() ;
		if( publishCommentContent.length >= 250 ){
			alert('您输入的评论超过了125个文字的上限，请修改后重新发表！')
		} else {
			$.getJSON("./servlet/PublishCommentServlet",{
				CommentContent:publishCommentContent,
				UserNum:LoginUserNum,
				ObjectNum:chapterNum,
				ObjectType:1,
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
		ObjectNum:chapterNum,
		ObjectType:1,
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
			commentTemp = '<div class = "scrBody" align="center"> <a class = "showCResult" target="view_window" >您将成为第一个评论者！</a></div>'
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
	$.ajaxSettings.async = false ;
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
	$.ajaxSettings.async = true ;
	return all ;
}

/**
 * 点击回复某人的按钮后反馈
 * @param index
 * @param commentId
 */
function reply( index , commentId ) {
	if( LoginUserNum == '' || LoginUserNum == 'undefinded') {
		alert('登录可以享受更多精彩内容！') ;
		location.href = './login.jsp' ;
	} else {
		if( LoginNickName == $('#comNick'+index).html() ) {
			alert('请不要回复自己！') ;
		} else {
			fid = commentId ;
			var $comcontent = $('#publishCommentContent') ;
			$comcontent.val( ' 回复 ' + $('#comNick'+index).html() + ' ： ' ) ;
			$comcontent.focus() ;
			document.getElementsByTagName('BODY')[0].scrollTop = document.getElementsByTagName('BODY')[0].scrollHeight;
		}		
	}
}
/**
 * 登录判断用户是否登录，为登录用户开放功能
 */
function showAttendBTN() {
	var $attendBtn = $('#attendBTN') ;
	if( LoginUserNum == "" ){
		$attendBtn.text('点我订阅') ;
		$attendBtn.attr('class' , 'optionBtn btn btn-info') ;
	} else {
		isMyCourse() ;
		newAudience() ;
		$attendBtn.show() ;
		$.getJSON("./servlet/haveAttendedServlet",{
			UserNum:LoginUserNum,
			CourseNum:CourseNum,
		},function(json){
			if( json.result == 0 ) {
				$attendBtn.text('点我订阅') ;
				$attendBtn.attr('class' , 'optionBtn btn btn-info') ;
			} else if (json.result == 1 ) {
				$attendBtn.text('取消订阅') ;
				$attendBtn.attr('class' , 'optionBtn btn btn-warning') ;
			}
		});
	}
}
/**
 * 订阅确认
 */
function attendConfirm() {
	if( LoginUserNum == '' || LoginUserNum == 'undefinded' ){
		alert('请您先尝试登录，再继续操作！') ;
		location.href = './login.jsp' ;
	} else {
		if( $('#attendBTN').text() == '点我订阅' ) {
			$.getJSON("./servlet/AttendCourseServlet",{
				UserNum:LoginUserNum,
				CourseNum:CourseNum,
			},function(json){
				if( json.result == 0 ) {
					alert( '订阅成功！' ) ;
					showAttendBTN() ;
				} else if (json.result == 1 ) {
					alert( '订阅失败！' ) ;			
				} else if (json.result == 2 ) {
					alert( '您已经订阅过本课程！' ) ;
				}
			});			
		} else if ( $('#attendBTN').text() == '取消订阅' ) {
			$.getJSON("./servlet/DeleteAttendCourseServlet",{
				UserNum:LoginUserNum,
				CourseNum:CourseNum,
			},function(json){
				if( json.result == 0 ) {
					alert( '取消订阅成功！' ) ;
					location.reload( true ) ;
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
	var $updateBtn = $('#updateBtn') ;
	var $registBtn = $('#RegistBtn') ;
	var $addNewBtn = $('#addNewBtn') ;
	var $attendBtn = $('#attendBTN') ;
	$updateBtn.attr( 'style' , 'padding:0 0 ;' ) ;
	$registBtn.attr( 'style' , 'padding:0 0 ;' ) ;
	$addNewBtn.attr( 'style' , 'padding:0 0 ;' ) ;
	$attendBtn.attr( 'style' , 'padding:0 0 ;' ) ;
	$registBtn.show() ;
	$.getJSON('./servlet/isMyCourseServlet',{
		UserNum:LoginUserNum,
		CourseNum:CourseNum,
	},function(json){
		if( json.result == 0 ) {
			$updateBtn.show() ;
			$addNewBtn.show() ;
		} else if (json.result == 1 ) {		
		}
	});	
}

/**
 * 新增内容按钮事件
 */
function addNewBtn() {
	location.href = "./jsp/afterNew.jsp?ObjectNum="+CourseNum+"&ObjectType=0" ;
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
				url = './jsp/Match/MatchInfo.jsp?MatchNum=' + json[i].MatchNum ;
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
 * 新增观看记录，若已观看过，只增加观看记录
 */
function newAudience() {
	if ( LoginUserNum != '' || LoginUserNum != 'undefinded' ) {
		$.getJSON('./servlet/AudienceCourseServlet',{
			UserNum:LoginUserNum,
			CourseNum:CourseNum,
		},function(json){
		});		
	} 
}

/**
 * 修改课程按钮，点击进入修改课程界面
 */
function updateCourse() {
	location.href = "./jsp/Course/update_course.jsp?CourseNum=" + CourseNum ;
}
