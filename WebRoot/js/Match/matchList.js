// JavaScript Document
var p = 0 ;
var mJson ;
$(function() {
	$.ajaxSettings.async = false ;
	showMatches() ;
});

/**
 * 显示搜索的课程
 */
function showMatches() {
	$('#matchList').html('') ;
	$.getJSON("./servlet/QueryAllMatchesServlet",{
	},function(json){
		if ( json == null || json.length == 0 ) {
			var showResult = '<p class = "showResult" >所有用户都太懒了，还没有上传比赛！</p>' ;
			$("#withoutResult").append(showResult) ;
		} else {
			mJson = json ;
			showPage( mJson.length ) ;
		}
	});
}

/**
 * 点击页数显示课程
 */
function pageMatchs() {
	$('#matchList').html('') ;
	var max = p * 4 ;
	var temp = "" ;
	for( var i = ( p - 1 ) * 4 ; i < max ; i ++ ){
		if ( i == mJson.length ) {
			break ;
		}
		var mname = mJson[i].MatchName ;
		var url = 'jsp/Match/MatchInfo.jsp?MatchNum=' + mJson[i].MatchNum ;
		var registrationNum = mJson[i].RegistrationNum ;
		var mtemp = '' ;
		switch(mJson[i].MatchTemp) {
			case 0 : mtemp = '初赛进行中！' ;
					break ;
			case 1 : mtemp = '复赛进行中！' ;
					break ;
			case 2 : mtemp = '决赛进行中！' ;
					break ;
			case 3 : mtemp = '已结束！' ;
					break ;
			default : mtemp = '神秘！' ;
					break ;
		}
		temp = '<div class="witkey_cnt">' 
			+ '<div class="witkey_bannar">'
				+ '<a href="'+url+'" class="witkey_top">'+ mname +'</a>'
				+ '<a href="'+url+'"><img src="'+mJson[i].MatchPicture+'"/></a>'
				+ '<a href="'+url+'" class="witkey_bottom">'+mtemp+'</a>'
			+ '</div>'
			+ '<div class="witkey_txt">'
				+ '<ul>'
					+ '<li class="witkey_txt1">'
						+ '<p class="top_p">活动时间：</p>'
						+ '<p class="">'+mJson[i].StartTime+'~'+mJson[i].EndTime+'</p>'
					+ '</li>'
					+ '<li class="witkey_txt2">'
						+ '<p class="top_p">活动介绍：</p>'
						+ '<p class="">'+mJson[i].MatchContent+'<a href="'+url+'" class="class-dpt">[详情]</a></p>'
					+ '</li>'
				+ '</ul>'
			+ '</div>'
			+ '<div class="clear"></div>'
			+ '</div>' + temp ;
	}
	$('#matchList').append( temp ) ;
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
	if ( length % 4 == 0 ) {
		pageNum = length / 4 ;
	} else {
		pageNum = parseInt( length / 4 ) ;
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
        	pageMatchs() ;
        }
    });
}