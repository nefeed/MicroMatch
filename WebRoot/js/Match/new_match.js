var local = window.location;  
var contextPath = local.pathname.split("/")[1];  
var basePath = local.protocol+"//"+local.host+"/"+contextPath+'/';  
var imgSrc = '' ;
$(function(){
//	window.onload=function(){
//	    /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
//		xiuxiu.embedSWF("altContent",5,"100%","99%");
//	    //修改为您自己的图片上传接口
//		xiuxiu.setUploadURL(basePath+"matchPic_upload_form.jsp");
//		xiuxiu.setUploadType(2);
//		xiuxiu.setUploadDataFieldName("TestFile");
//		xiuxiu.onInit = function ()
//		{
//			xiuxiu.loadPhoto("");
//		}	
//		xiuxiu.onUploadResponse = function (data)
//		{
//			imgSrc = decodeURI(data) ;
//			imgSrc = imgSrc.replace(/ /, '').replace( /\r/,'' ).replace( /\n/,'' ).replace( /\t/, '') ;
//			$('#newPic').attr('src', basePath+imgSrc) ;
//			$('#newPic').show() ;
//			$('#pic_upbutton').hide() ;
//			$('#blockInit').hide() ;
//			$('#blockInit').attr( 'style', 'display: none;' ) ;
//			$('#myModal').modal('hide') ;
//		}
//	}
	$('#pic_upbutton').change(function () {
        ajaxFileUpload1();
    }) ;
	$('#file_upload').change(function () {
        ajaxFileUpload2();
    }) ;
});
// 上传比赛图片1
function ajaxFileUpload1() {
    $.ajaxFileUpload({
              url: './servlet/uploadMatchImgServlet', //用于文件上传的服务器端请求地址
              secureuri: false, //是否需要安全协议，一般设置为false
              fileElementId: 'pic_upbutton', //文件上传域的ID
              dataType: 'json', //返回值类型 一般设置为json
              success: function (data, status)  //服务器成功响应处理函数
              {
            	imgSrc = data.address ;
                $('#newPic').attr('src', data.address) ;
                $('#newPic').show() ;
                $('.sl-custom-file').hide() ;
              },
              error: function (data, status, e)//服务器响应失败处理函数
              {
                alert('上传失败,错误代码如下！\n' + e);
              }
            }
    );
    return false;
}
//上传比赛图片2
function ajaxFileUpload2() {
    $.ajaxFileUpload({
              url: './servlet/uploadMatchImgServlet', //用于文件上传的服务器端请求地址
              secureuri: false, //是否需要安全协议，一般设置为false
              fileElementId: 'file_upload', //文件上传域的ID
              dataType: 'json', //返回值类型 一般设置为json
              success: function (data, status)  //服务器成功响应处理函数
              {
            	imgSrc = data.address ;
                $('#newPic').attr('src', data.address) ;
                $('#newPic').show() ;
                $('.sl-custom-file').hide() ;
              },
              error: function (data, status, e)//服务器响应失败处理函数
              {
                alert('上传失败,错误代码如下！\n' + e);
              }
            }
    );
    return false;
}
function saveMatch() {
	var mName = $('#matchName').val() ;
	var mContent = $('#matchContent').val() ;
	var sTime = $('#startInput').val() ;
	var eTime = $('#endInput').val() ;
	var begintime = [] ;
	var stoptime = [] ;
	begintime = sTime.split('-') ;
	stoptime = eTime.split('-') ;
	var eyear = parseInt(begintime[0]);
	var emonth = parseInt(begintime[1]);
	var eday = parseInt(begintime[2]);
	var syear = parseInt(stoptime[0]);
	var smonth = parseInt(stoptime[1]);
	var sday = parseInt(stoptime[2]);
	if( mName.length < 2 ) {
		alert( '输入的课程名称长度过短，请修改后重新尝试！' ) ;
	} else if ( mName > 15 ) {
		alert( '输入的课程名称长度过长，请修改后重新尝试！' ) ;
	} else if ( sTime == '' ){
		alert( '请选择一个开始时间后继续尝试！' ) ;
	} else if ( mContent.length > 220 ) {
		alert( '输入的课程概述太长，请修改后重新尝试！' ) ;
	} else if ( eTime == '' ) {
		alert( '请选择一个结束时间后继续尝试！' ) ;
	} else if ( (eyear > syear) || 
			(eyear == syear && emonth > smonth) || 
			(eyear == syear && emonth == smonth && eday >= sday) ) {
		alert( '比赛开始时间不能晚于比赛结束时间，请修改后继续尝试！' ) ;	
	} else {
		$.getJSON("./servlet/PublishMatchServlet",{
			UserNum:LoginUserNum,
			MatchName:mName,
			MatchContent:mContent,
			StartTime:sTime,
			EndTime:eTime,
			MatchPic:imgSrc,
		},function(json){
			if ( json.result == 0 ) {
				location.href = 'Match/MatchInfo.jsp?MatchNum='+json.MatchNum ;
			} else if ( json.result == 1 ) {
				alert( "发布比赛失败！" ) ;
			}
		});
	}
}

function backToHome() {
	location.href = './/.//mainActivity.jsp' ;
}
