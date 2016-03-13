// JavaScript Document
$(function() {
	$.ajaxSettings.async = false ;
	$.getJSON("./servlet/QueryAllCourseServlet",{
		
	},function(json){
		var temp = '' ;
		var j = 0 ;
		for(var i = 0 ; i < 8 ; i ++ ){
			if ( i == json.length ) {
				break ;
			}
			var url = '/micromatch/jsp/Course/courseInfo.jsp?CourseNum=' + json[i].CourseNum ;
			// + '&ListId=0' ;
			var cname = json[i].CourseName ;
			if ( cname.length >= 20 ) {
				cname = cname.substring(0, 16) ;
				cname += "..." ;
			}
			var period = json[i].Period ;
			temp += '<li>'   
				+ '<a href='+url+'><img src = '+json[i].CoverPicture+' class = "courseminiPic" /></a>'
				+ '<a class ="main_1_a1" href='+url+'>'+cname+'</a>'
				+ '<em>'
				+ '<i class = "i1" >' + period + ' 章节</i>'
				+ '<i class = "i3" >' + json[i].AudienceNum + '人观看</i>'
				+ '</em>'
				+ '<a href='+url+'><img class = "img" src = "img/index_16.png" width = "50" height = "50" /></a>'
				+ '</li>' ;
		}
		$('#ul1').append( temp ) ;
		$.ajaxSettings.async = true ;
		showIndexSubject() ;
	});
	$('#am1').show() ;
	$('.subject_left em').each(function(){
		$(this).mouseenter(function(){
			if ('undefined' == typeof(document.body.style.maxHeight)){//兼容IE6
				$(this).find('ul:first').show();
			}
			else
			{
				$(this).find('ul:first').show();
			}
		});
		$(this).mouseleave(function(){
			if ('undefined' == typeof(document.body.style.maxHeight)){
				$(this).find('ul:first').hide();
			}
			else
			{
				$(this).find('ul:first').hide();
			}
		});
	});
	$('.main_21 ul li').each(function(){
		$(this).mouseenter(function(){
			if ('undefined' == typeof(document.body.style.maxHeight)){//兼容IE6
				$(this).find('.img').show();
			}
			else
			{
				$(this).find('.img').show();
			}
		});
		$(this).mouseleave(function(){
			if ('undefined' == typeof(document.body.style.maxHeight)){

				$(this).find('.img').hide();
			}
			else
			{
				$(this).find('.img').hide();
			}
		});
	});
	$('#a1').hover(function(){
		$('#am1').show();
		$('#am2').hide();
		$('#am3').hide();
		$('#am4').hide();
		$('#am5').hide();
		$('#am6').hide();
		$('#a1').addClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a2').hover(function(){
		$('#am1').attr('style' , 'display:none') ;
		$('#am2').attr('style' , 'display:block') ;
		$('#am3').attr('style' , 'display:none') ;
		$('#am4').attr('style' , 'display:none') ;
		$('#am5').attr('style' , 'display:none') ;
		$('#am6').attr('style' , 'display:none') ;
		$('#a2').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a3').hover(function(){
		$('#am1').hide();
		$('#am2').hide();
		$('#am3').show();
		$('#am4').hide();
		$('#am5').hide();
		$('#am6').hide();
		$('#a3').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a4').hover(function(){
		$('#am1').hide();
		$('#am2').hide();
		$('#am3').hide();
		$('#am4').show();
		$('#am5').hide();
		$('#am6').hide();
		$('#a4').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a5').hover(function(){
		$('#am1').hide();
		$('#am2').hide();
		$('#am3').hide();
		$('#am4').hide();
		$('#am5').show();
		$('#am6').hide();
		$('#a5').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a6').hover(function(){
		$('#am1').hide();
		$('#am2').hide();
		$('#am3').hide();
		$('#am4').hide();
		$('#am5').hide();
		$('#am6').show();
		$('#a6').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current')
		})
});

function showIndexSubject() {
	$.ajaxSettings.async = false ;
	$.getJSON('./servlet/showIndexSubjectServlet',{
		
	},function(json){
		var $subject = null ;
		var $subject2 = null ;
		for ( var i = 0 ; i < json.length ; i ++ ) {
			$subject = $("#s"+i) ;
			$subject.text( json[i].SubjectName ) ;
			$subject.attr( 'href' , '/micromatch/jsp/Course/subjectCourse.jsp?SubName='+json[i].SubjectName  ) ;
			showChildSubject( i , json[i].SID ) ;
			if( i != json.length - 1 ) {
				var j = i + 2 ;
				$subject2 = $("#a"+j) ;
				$subject2.text( json[i].SubjectName ) ;
				$subject2.attr( 'href' , '/micromatch/jsp/Course/subjectCourse.jsp?SubName='+json[i].SubjectName  ) ;
				$subject2 = null ;
				loadingSubjectCourse( json[i].SubjectName , j ) ;
			}
			$subject = null ;
		}
	});
	$.ajaxSettings.async = true ;
}
function showChildSubject( i , SID ) {
	$.ajaxSettings.async = false ;
	$.getJSON('./servlet/showChildSubjectServlet',{
		SubId:SID,
	},function(json){
		if( json != null ){
			var $sul = $( "#subject_ul"+i ) ;
			var litemp = '' ;
			var url = '';
			for ( var j = 0 ; j < json.length ; j ++ ){
				url = '/micromatch/jsp/Course/subjectCourse.jsp?SubName='+json[j].SubjectName ;
				litemp += '<li><a href='+url+'>' + json[j].SubjectName + '</a></li><br>';
				url = null ;
			}
			$sul.append( litemp ) ;
			$sul = null ;							
		}
	});
	$.ajaxSettings.async = true ;
}
function loadingSubjectCourse( asubName , j ) {
	$.ajaxSettings.async = false ;
	$.getJSON("./servlet/QueryCourseBySubNameServlet",{
		SubName:asubName,
	},function(json){
		if( json.length == 0 ){
			temp = '<p>无此章节的课程！</p>' ;			
		} else {
			var temp = "" ;
			for(var i = 0 ; i < 16 ; i ++ ){
				if ( i == json.length ) {
					break ;
				}
				var url = '/micromatch/jsp/Course/courseInfo.jsp?CourseNum='+json[i].CourseNum ;
				// + '&ListId=0' ;
				var cname = json[i].CourseName ;
				if ( cname.length >= 20 ) {
					cname = cname.substring(0, 16) ;
					cname += "..." ;
				}
				var period = json[i].Period ;
				temp += '<li>'   
					+ '<a href='+url+'><img src = '+json[i].CoverPicture+' class = "courseminiPic" /></a>'
					+ '<a class ="main_1_a1" href='+url+'>'+cname+'</a>'
					+ '<em>'
					+ '<i class = "i1" >' + period + ' 章节</i>'
					+ '<i class = "i3" >' + json[i].AudienceNum + '人观看</i>'
					+ '</em>'
					+ '<a href='+url+'><img class = "img" src = "img/index_16.png" width = "50" height = "50" /></a>'
					+ '</li>' ;
			}
			$('#ul'+j).append( temp ) ;
		}
	});
	$.ajaxSettings.async = true ;
}
