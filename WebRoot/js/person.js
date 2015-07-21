// JavaScript Document
var local = window.location;  
var contextPath = local.pathname.split("/")[1];  
var basePath = local.protocol+"//"+local.host+"/"+contextPath+'/';
var userPic = '' ;
var p = 0 ;
var userType = 3 ;
var aJson = null ; // 用于存放我的订阅Json
var cJson = null ; // 用于存放我的课程Json
var mJson = null ; // 用于存放我的比赛Json
var ot = 0 ; // 当前点击的Tab，0：我的订阅；1：我的课程；2：我的比赛
$(function() {
	$.ajaxSettings.async = false ;
	$('.right2').hide() ;
	findTheOne() ; // 显示该用户的详情
	window.onload=function(){
	    /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
		xiuxiu.embedSWF("altContent",5,"93%","100%");
	    //修改为您自己的图片上传接口
		xiuxiu.setUploadURL(basePath+"userPic_upload_form.jsp");
		xiuxiu.setUploadType(2);
		xiuxiu.setUploadDataFieldName("TestFile");
		xiuxiu.onInit = function ()
		{
			xiuxiu.loadPhoto("");
		}	
		xiuxiu.onUploadResponse = function (data)
		{
			userPic = decodeURI(data) ;
			userPic = userPic.replace(/ /, '').replace( /\r/,'' ).replace( /\n/,'' ).replace( /\t/, '') ;
			$('#fileAddress').val(userPic) ;
			updateUserPic( userPic ) ;
		}
	};
	$('#geren').click(function(){
		$(this).addClass('subject_current');
		$('#picSettings').removeClass('subject_current');
		$('#person_Logout').removeClass('subject_current');
		$('.right1').show();
		$('.right2').hide();
		})
	$('#picSettings').click(function(){
		$(this).addClass('subject_current');
		$('#geren').removeClass('subject_current');
		$('#person_Logout').removeClass('subject_current');
		$('.right2').show();
		$('.right1').hide();
		})
	/*$('#person_Logout').click(function(){
		$(this).addClass('subject_current');
		$('#geren').removeClass('subject_current');
		$('#picSettings').removeClass('subject_current');		
		})*/
	// em1：我的订阅；em2：我的课程；em3：我的比赛
    $('#em1').click(function(){
    	p = 0 ;
    	ot = 0 ;
    	showPage( aJson.length ) ;
		$(this).addClass('em_current');
		$('#em2').removeClass('em_current');
		$('#em3').removeClass('em_current');
		$('#ul1').show();
		$('#ul2').hide();
		$('#ul3').hide() ;
		})
	$('#em2').click(function(){
		p = 0 ;
		ot = 1 ;
		showPage( cJson.length ) ;			
		$(this).addClass('em_current');
		$('#em1').removeClass('em_current');
		$('#em2').removeClass('em_current');
		$('#ul2').show();
		$('#ul1').hide();
		$('#ul3').hide() ;

		})
	$('#em3').click(function(){
		p = 0 ;
		ot = 2 ;
		showPage( mJson.length ) ;
		$(this).addClass('em_current');
		$('#em2').removeClass('em_current');
		$('#em1').removeClass('em_current');
		$('#ul3').show() ;
		$('#ul1').hide();
		$('#ul2').hide();
		})
	$('#person_Logout').on('click', function(){
		$(this).addClass('subject_current');
		$('#geren').removeClass('subject_current');
		$('#picSettings').removeClass('subject_current');
					 $.layer({
						 title: '一键注销',
						area: ['200px','100px'],
						btn: ['确定注销','取消'],
						dialog: {
							btns: 2,                    
							type: 4,
							btn: ['确定注销','取消']
						}
						
						
					});
						
				})
});
/**
 * 显示该用户的详情信息
 */
function findTheOne() {
	$.getJSON("./servlet/QueryUserInfoServlet",{
		UserNum:TUserNum,
	},function(json){
		$('#emUserPicture').attr( 'src' , json.UserPicture ) ;
		$('#emNickName').text(json.NickName) ;
		userType = json.UserType ;
		isMine() ;
	});
}
/**
 * 判断是否查看个人中心用户为本人
 */
function isMine() {
	if( LoginUserNum != TUserNum ){
		$('#picSettings').hide() ;
		$('#person_Logout').hide() ;
	}
	whatUserType() ;
}

/**
 * 根据用户类型显示功能
 */
function whatUserType() {
	// UserType 0：管理员；1：学院用户；2：教师用户；3：学生用户
	switch( userType ) {
		case 0:
			ot = 2 ;
			showMyCourses() ;
			showMyMatchs() ;
			$('#em1').hide() ;
			$('#em2').show() ;
			$('#em3').show() ;
			$('#ul1').hide() ;
			$('#ul2').hide() ;
			$('#ul3').show() ;
			$('#em3').addClass('em_current') ;
			break ;
		case 1:
			ot = 2 ;
			showMyMatchs() ;
			$('#em1').hide() ;
			$('#em2').hide() ;
			$('#em3').show() ;
			$('#ul1').hide() ;
			$('#ul2').hide() ;
			$('#ul3').show() ;
			$('#em3').addClass('em_current') ;
			break ;
		case 2:
			ot = 1 ;
			showMyCourses() ;
			$('#em1').hide() ;
			$('#em2').show() ;
			$('#em3').hide() ;
			$('#ul1').hide() ;
			$('#ul2').show() ;
			$('#ul3').hide() ;
			$('#em2').addClass('em_current') ;
			break ;
		case 3:
			ot = 0 ;
			showMyAttend() ;
			$('#em1').show() ;
			$('#em2').hide() ;
			$('#em3').hide() ;
			$('#ul1').show() ;
			$('#ul2').hide() ;
			$('#ul3').hide() ;
			$('#em1').addClass('em_current') ;
			break ;
		default :
			ot = 0 ;
			showMyAttend() ;
			$('#em1').show() ;
			$('#em2').hide() ;
			$('#em3').hide() ;
			$('#ul1').show() ;
			$('#ul2').hide() ;
			$('#ul3').hide() ;
			$('#em1').addClass('em_current') ;
			break ;
	}
}
/**
 * 更新用户头像
 */
function updateUserPic( userPic ) {
	if( UserPic = '' ) {
		alert( '请先上传头像才尝试更新！') ;
	} else {
		$.getJSON( './servlet/updateUserPicServlet',{
			UserNum:TUserNum,
			UserPic:userPic,
		},function(json){
			if ( json.result == 0 ) {
				location.reload(true) ;
			} else if ( json.result == 1 ) {
				alert( '更新头像失败！' ) ;
			}
		}) ;
	}
}

/**
 * 注销用户登录
 */
function logOut() {
	$.getJSON('./servlet/logOutServlet',{
	},function(json){
		
	});
	location.href = "index.jsp" ;
}

/**
 * 遍历我的订阅
 */
function showMyAttend() {
	$.getJSON("./servlet/ShowMyAttendsServlet",{
		UserNum:TUserNum,
	},function(json){
		aJson = json ;
		showPage( aJson.length ) ;
	});
}
function pageAttend() {
	var temp = '' ;
	$('#ul1').html('') ;
	if( aJson == null ) {
		var showResult = '<div align="center"><p class = "showResult" >您未订阅过任何课程！</p></div>' ;
		$('#ul1').html(showResult) ;
	} else {
		var max = p * 6 ;
		for(var i = ( p - 1 ) * 6 ; i < max ; i ++ ){
			if ( i == aJson.length ) {
				break ;
			}
			var url = 'jsp/Course/courseInfo.jsp?CourseNum='+aJson[i].CourseNum ;
			// + '&ListId=0' ;

			var cname = aJson[i].CourseName ;
			if ( cname.length >= 20 ) {
				cname = cname.substring(0, 16) ;
				cname += "..." ;
			}
			var period = aJson[i].Period ;
			temp += '<li>'   
				+ '<a href='+url+'><img src = "'+aJson[i].CoverPicture+'" class = "courseminiPic"/></a>'
				+ '<a class ="main_1_a1" href='+url+'>'+cname+'</a>'
				+ '<em><i class = "i1" >' + period + ' 章节</i>'
				+ '<i" class = "i3" >' + aJson[i].AudienceNum + '人观看</i>'
				+ '</em>'
				+ '</li>' ;
		}
	}
	$('#ul1').html( temp ) ;
}
/**
 * 显示我创建的课程
 */
function showMyCourses(){
	$.getJSON("./servlet/QueryCourseByUserNumServlet",{
		UserNum:TUserNum,
	},function(json){
		cJson = json ;
		showPage( cJson.length ) ;
		
	});
}
function pageCourse() {
	$('#ul2').html('') ;
	var ctemp = '' ;
	if( cJson == null ) {
		var showResult = '<div align="center"><p class = "showResult" >您未创建过任何课程！</p></div>' ;
		$('#ul2').html(showResult) ;
	} else {
		var max = p * 6 ;
		for(var i = ( p - 1 ) * 6 ; i < max ; i ++ ){
			if ( i == cJson.length ) {
				break ;
			}
			var url = 'jsp/Course/courseInfo.jsp?CourseNum='+cJson[i].CourseNum ;
			// + '&ListId=0' ;
			var cname = cJson[i].CourseName ;
			if ( cname.length >= 20 ) {
				cname = cname.substring(0, 16) ;
				cname += "..." ;
			}
			var period = cJson[i].Period ;
			ctemp += '<li>'   
				+ '<a href='+url+'><img src = "'+cJson[i].CoverPicture+'" class = "courseminiPic"/></a>'
				+ '<a class ="main_1_a1" href='+url+'>'+cname+'</a>'
				+ '<em><i class = "i1" >' + period + ' 章节</i>'
				+ '<i class = "i3" >' + cJson[i].AudienceNum + '人观看</i>'
				+ '</em>'
				+ '</li>' ;
		}
	}
	$('#ul2').html( ctemp ) ;
}
/**
 * 展示我的比赛
 */
function showMyMatchs() {
	$.getJSON("./servlet/QueryMatchByUserNumServlet",{
		UserNum:TUserNum,
	},function(json){
		mJson = json ;
		showPage( mJson.length ) ;
		
	});
}
function pageMatch() {
	$('#ul3').html('') ;
	var mtemp = '' ;
	if( mJson == null || mJson.length == 0  ) {
		var showResult = '<div align="center"><p class = "showResult" >您未创建过任何比赛！</p></div>' ;
		$('#ul3').html(showResult) ;
	} else {
		var max = p * 6 ;
		for(var i = ( p - 1 ) * 6 ; i < max ; i ++ ){
			if ( i == mJson.length ) {
				break ;
			}
			var url = 'jsp/Match/MatchInfo.jsp?MatchNum='+mJson[i].MatchNum ;
			var content = mJson[i].MatchContent ;
			if( content.length >= 20) {
				content = content.substring(0,16) ;
				content += "..." ;
			}
			var mname = mJson[i].MatchName ;
			if ( mname.length >= 20 ) {
				mname = mname.substring(0, 16) ;
				mname += "..." ;
			}
			mtemp += '<li>'   
				+ '<a href='+url+'><img src = "'+mJson[i].MatchPicture+'" class = "courseminiPic" /></a>'
				+ '<a class ="main_1_a1" href='+url+'>'+mname+'</a>'
				+ '<p>'+content+'</p>'
				+ '<em><i class = "i1" >' + mJson[i].RegistrationNum + ' 参赛课程</i></em>'
				+ '</li>' ;
		}
	}
	$('#ul3').html( mtemp ) ;
}
/**
 * @Description: TODO
 * @param  显示一共有的页数
 * @return void  
 * @throws
 * @author Gavin
 * @date 2015年5月31日
 */
function showPage( length ) {
	
	var pageNum = 1 ;
	if ( length % 6 == 0 ) {
		pageNum = length / 6 ;
	} else {
		pageNum = parseInt( length / 6 ) ;
		pageNum ++ ;
	}
	laypage({
        cont: 'page', //容器。值支持id名、原生dom对象，jquery对象,
        pages: pageNum, //总页数
        skip: false, //是否开启跳页
        skin: 'molv',
        groups: 6, //连续显示分页数
        first: '首页', //若不显示，设置false即可
        last: '尾页', //若不显示，设置false即可
        prev: false, //若不显示，设置false即可
        next: false, //若不显示，设置false即可
        hash: true, //开启hash
        jump: function(obj){ //触发分页后的回调
        	p = obj.curr ;
        	// ot的值， 0：正在操作的是我的订阅；1：正在操作的是我的课程；2：正在操作的是我的比赛
        	switch (ot) {
        		case 0 :
        			pageAttend() ;
        			break ;
        		case 1 :
        			pageCourse() ;
        			break ;
        		case 2 :
        			pageMatch() ;
        			break ;
        		default :
        			pageAttend() ;
        			break ;
        	}
        }
    });
}