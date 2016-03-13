var local = window.location;  
var contextPath = local.pathname.split("/")[1];  
var basePath = local.protocol+"//"+local.host+"/"+contextPath+'/';  
var imgSrc = '' ;
$(function(){
	$.ajaxSettings.async = false ;
	$('#indexBtn').attr( 'class' , '' ) ;
	$('#myAttendBtn').attr( 'class' , '' ) ;
	$('#newCourseBtn').attr( 'class' , 'logo_current' ) ;
	loadSubject() ;

//	window.onload=function(){
//	    /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
//		xiuxiu.embedSWF("altContent",5,"100%","99%");
//	    //修改为您自己的图片上传接口
//		xiuxiu.setUploadURL("/micromatch/coursePic_upload_form.jsp");
//		xiuxiu.setUploadType(2);
//		xiuxiu.setUploadDataFieldName("TestFile");
//		xiuxiu.onInit = function ()
//		{
//			xiuxiu.loadPhoto("");
//		};
//		xiuxiu.onUploadResponse = function (data)
//		{
//			imgSrc = decodeURI(data) ;
//			imgSrc = imgSrc.replace(/ /, '').replace( /\r/,'' ).replace( /\n/,'' ).replace( /\t/, '') ;
//			$('#newPic').attr('src', basePath+imgSrc) ;
//			$('#newPic').show() ;
//			$('#pic_upbutton').hide() ;
//			$('#myModal').hide() ;
//			$('#blockInit').hide() ;
//			$('#blockInit').attr( 'style', 'display: none;' ) ;
//		}
//	};
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
	
	 loadInfomation() ;
});
// 上传课程图片1
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
                $('#newPicArrow').hide() ;
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
 * 遍历得到的课程信息
 */
function loadInfomation() {
	$.getJSON('/micromatch/servlet/QueryCourseByCourseNumServlet',{
		CourseNum:CourseNum,
	},function(json){
		$('#newCourseName').val( json.CourseName ) ;
		$('#newCourseContent').val( json.CourseContent ) ;
		$('#newPic').attr( 'src', json.CoverPicture ) ;
		$('#newPic').show() ;
		$('#pic_upbutton').hide() ;
		var sId = json.SubId ;
		var $subSelect = $('#subSelect option') ;
		var $childSubSelect = $('#childSubSelect option') ;
		for( var i = 0 ; i < $subSelect.length ; i ++ ) {
			$subSelect.eq(i).attr( 'selected' , 'selected') ;
			showChildSubSelect( $subSelect.eq(i).val() ) ;
			for ( var j = 0 ; j < $childSubSelect.length ; j ++ ) {
				if( sId == $childSubSelect.eq(j).val() ){
					$childSubSelect.eq(j).attr( 'selected' , 'selected') ;
				}
			}
		}
	}) ;
}
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
			showChildSubSelect( 1 ) ;
		}
	}) ;
}

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

function confirmUpdateCourse() {
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
	} else if ( imgSrc == '' ) {
		alert( '请上传一张课程图层！' ) ;
	} else {
		$.getJSON('./servlet/UpdateCourseServlet',{
			CourseNum:CourseNum,
			UserNum:LoginUserNum,
			CourseName:courseName,
			CourseContent:courseContent,
			SubId:subId,
			CoverPicture:imgSrc,
		},function(json){
			if ( json.result == 0 ) {
				alert( "课程修改成功！" ) ;
				location.href = '/micromatch/jsp/afterNew.jsp?ObjectNum='+CourseNum+'&ObjectType=0' ;
			} else if ( json.result == 1 ) {
				alert( "课程修改失败！" ) ;
			}
		});
	}
}
