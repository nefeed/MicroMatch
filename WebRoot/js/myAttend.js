// JavaScript Document
$(function() {
	$.ajaxSettings.async = false ;
//	if( LoginUserType != 3 ) {
//		alert('您并非学生用户，请更换用户类型后尝试！') ;
//		location.href = 'index.jsp' ;
//	}
	if( LoginUserNum == '' ) {
		alert('请登入后继续尝试！') ;
		location.href = 'index.jsp' ;
	} else {
		$.getJSON("./servlet/ShowMyAttendsServlet",{
			UserNum:LoginUserNum,
		},function(json){
			var temp = '' ;
			if( json == 'undefine' || json == null ) {
				alert('你还没有订阅过课程，请订阅自己喜欢的课程后继续尝试！');
				location.href = 'index.jsp' ;
			} else {
				for(var i = 0 ; i < json.length ; i ++ ){
					var url = 'jsp/Course/courseInfo.jsp?CourseNum='+json[i].CourseNum ;
					// + '&ListId=0' ;
					var subject = json[i].SubjectName ;
					var cname = json[i].CourseName ;
					if ( cname.length >= 20 ) {
						cname = cname.substring(0, 16) ;
						cname += "..." ;
					}
					var period = json[i].Period ;
					if( period != 0 ){
						temp += '<li>'   
							+ '<a href='+url+'><img src = "'+json[i].CoverPicture+'" class = "courseminiPic" /></a>'
							+ '<a class ="main_1_a1" href='+url+'>'+cname+'</a>'
							+ '<em><i class = "i1" >' + period + ' 章节</i>'
							+ '<i class = "i3" >' + json[i].AudienceNum + '人观看</i>'
							+ '</em>'
							+ '<a href='+url+'><img class = "img" src = "img/index_16.png" width = "50" height = "50" /></a>'
							+ '</li>' ;
					}
				}
			}
			$('#ul1').append( temp ) ;
		});
	}
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
		$('.main_21').show();
		$('.main_22').hide();
		$('#a1').addClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a2').hover(function(){
		$('.main_22').show();
		$('.main_21').hide();
		$('#a2').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a3').hover(function(){
		$('#a3').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a4').hover(function(){
		$('#a4').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a5').hover(function(){
		$('#a5').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a6').removeClass('main_1_current')
		});
	$('#a6').hover(function(){
		$('#a6').addClass('main_1_current');
		$('#a1').removeClass('main_1_current');
		$('#a2').removeClass('main_1_current');
		$('#a3').removeClass('main_1_current');
		$('#a4').removeClass('main_1_current');
		$('#a5').removeClass('main_1_current')
		})
});
