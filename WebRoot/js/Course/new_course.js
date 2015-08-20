var local = window.location;  
var contextPath = local.pathname.split("/")[1];  
var basePath = local.protocol+"//"+local.host+"/"+contextPath+'/';  
var imgSrc = '' ;
$(function(){
	
	loadSubject() ;
//	window.onload=function(){
//	    /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
//		xiuxiu.embedSWF("altContent",5,"100%","99%");
//	    //修改为您自己的图片上传接口
//		xiuxiu.setUploadURL(basePath+"coursePic_upload_form.jsp");
//		xiuxiu.setUploadType(2);
//		xiuxiu.setUploadDataFieldName("TestFile");
//		xiuxiu.onInit = function ()
//		{
//			xiuxiu.loadPhoto("") ;
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
	$('#file_upload').change(function () {
		ajaxFileUpload1();
	}) ;
	$('#pic_upbutton').change(function () {
        ajaxFileUpload2();
    }) ;
	 $("#subSelect").change(function(){
         $("#subSelect option").each(function(i,o){
        	 showChildSubSelect($("#subSelect").val()) ;
         });
     });
});
//上传课程图片1
function ajaxFileUpload1() {
    $.ajaxFileUpload({
              url: './servlet/uploadCourseImgServlet', //用于文件上传的服务器端请求地址
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
//上传课程图片2
function ajaxFileUpload2() {
    $.ajaxFileUpload({
              url: './servlet/uploadCourseImgServlet', //用于文件上传的服务器端请求地址
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
/**
 * 读取学科分类
 */
function loadSubject(){
	$.getJSON( './servlet/ShowAllSubjectsServlet',{
		
	},function(json){
		var temp = '' ;
		if ( json != null || json.length !=0 ) {
			for( var i=0 ; i < json.length ; i ++ ){
				if( json[i].PID == 0 ){
					temp += '<option value = ' + json[i].ID + '>' + json[i].SubjectName + '</option>' ;			
				}
			}
			$('#subSelect').append( temp ) ;
			showChildSubSelect( json[0].ID ) ;
		}
	}) ;
}

/**
 * 读取子学科分类
 * @param pid
 */
function showChildSubSelect( pid ) {
	$('#childSubSelect').html('') ;
	$.getJSON('./servlet/showChildSubjectServlet',{
		SubId:pid,
	},function(json){
		var temp = '' ;
		if ( json != null || json.length !=0 ) {
			for( var i=0 ; i < json.length ; i ++ ){
				if( json[i].PID != 0 ){
					temp += '<option value = ' + json[i].ID + '>' + json[i].SubjectName + '</option>' ;		
				}
			}
			$('#childSubSelect').append( temp ) ;
		}
	});
}

function confirmSaveCourse() {
	var courseName = $('#newCourseName').val() ;
	var subId = $('#childSubSelect').val() ;
	var courseContent = $('#newCourseContent').val() ;
	if( courseName.length < 2 ) {
		alert( '输入的课程名称长度过短，请修改后重新尝试！' ) ;
	} else if ( courseName > 15 ) {
		alert( '输入的课程名称长度过长，请修改后重新尝试！' ) ;
	} else if ( subId == '' ){
		alert( '请选择一个子学科分类后继续尝试！' ) ;
	} else if ( courseContent.length > 220 ) {
		alert( '输入的课程概述太长，请修改后重新尝试！' ) ;
	} else {
		$.getJSON('./servlet/CreateCourseServlet',{
			UserNum:LoginUserNum,
			CourseName:courseName,
			CourseContent:courseContent,
			SubId:subId,
			CoverPicture:imgSrc,
		},function(json){
			if ( json.result == 0 ) {
				location.href = 'courseInfo.jsp?CourseNum='+json.CourseNum ;
			} else if ( json.result == 1 ) {
				alert( "课程创建失败！" ) ;
			}
		});
	}
}

function backToHome() {
	location.href = './/.//mainActivity.jsp' ;
}
