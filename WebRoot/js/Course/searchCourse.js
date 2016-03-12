// JavaScript Document
var p = 0 ;
var cJson ;
$(function() {
	$.ajaxSettings.async = false ;
	$('#searchInput').val(unsureCourseName) ;
	showCourses() ;
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
			if ('undefined' == typeof(document.body.style.maxHeight)){//兼容IE6
				$(this).find('ul:first').hide();
			}
			else
			{
				$(this).find('ul:first').hide();
			}
		});
	});
	$('.main ul li').each(function(){
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
			if ('undefined' == typeof(document.body.style.maxHeight)){//兼容IE6
				$(this).find('.img').hide();
			}
			else
			{
				$(this).find('.img').hide();
			}
		});
	});
});

/**
 * 显示搜索的课程
 */
function showCourses() {
	$('#ul1').html('') ;
	$.getJSON("./servlet/QueryUnsureCourseServlet",{
		CourseName:unsureCourseName,
	},function(json){
		if( json == null || json.length == 0 ){
			alert('无相似的课程，请更换课程名继续尝试！');
			var showResult = '<p class = "showResult" >没有搜索结果！</p>' ;
			$("#withoutResult").append(showResult) ;
		} else {
			cJson = json ;
			showPage( json.length ) ;
		}
	});
}

/**
 * 点击页数显示课程
 */
function pageCourses() {
	$('#ul1').html('') ;
	if( cJson == null || cJson.length == 0 ){
		alert('无相似的课程，请更换课程名继续尝试！');
		var showResult = '<p class = "showResult" >没有搜索结果！</p>' ;
		$("#withoutResult").append(showResult) ;
	} else {
		var temp = "" ;
		var max = p * 16 ;
		for(var i = ( p - 1 ) * 16 ; i < max ; i ++ ){
			if ( i == cJson.length ) {
				break ;
			}
			var url = './jsp/Course/courseInfo.jsp?CourseNum='+cJson[i].CourseNum ;
			// + '&ListId=0' ;
			var cname = cJson[i].CourseName ;
			if ( cname.length >= 20 ) {
				cname = cname.substring(0, 16) ;
				cname += "..." ;
			}
			var period = cJson[i].Period ;
			temp += '<li>'   
				+ '<a href='+url+'><img src = "'+cJson[i].CoverPicture+'" class="courseminiPic"/></a>'
				+ '<a class ="main_1_a1" href='+url+'>'+cname+'</a>'
				+ '<em>'
				+ '<i class = "i1" >' + period + ' 章节</i>'
				+ '<i class = "i3" >' + cJson[i].AudienceNum + '人观看</i>'
				+ '</em>'
				+ '<a href='+url+'><img class = "img" src = "img/index_16.png" width = "50" height = "50" /></a>'
				+ '</li>' ;
		}
		$('#ul1').append( temp ) ;
	}
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
	if ( length % 16 == 0 ) {
		pageNum = length / 16 ;
	} else {
		pageNum = parseInt( length / 16 ) ;
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
			pageCourses() ;
        }
    });
}