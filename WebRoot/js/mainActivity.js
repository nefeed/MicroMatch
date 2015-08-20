var myPeriod = 0 ;
var chooseCNum = '' ;
var newestMatchNum = '' ;
var secondMatchNum = '' ;
var newestMatchTemp = '' ;
var indexNum = 4 ;
$(function() {
	$.ajaxSettings.async = false ;
	showTheNewestMatches(indexNum) ;
	// 需要显示的最新和最热的课程数 
	var needNum = 4 ;
	showNewestCourses( needNum ) ;
	showHotCourses( needNum ) ;
	isMyMatch() ;
	/**
	 * 评分功能模块
	 */
	var stepW = 24 ;
    var stars = $("#star > li");
    $("#showb").css("width",0) ;
    stars.each(function(i){
        $(stars[i]).click(function(e){
        	var n = i + 1 ;
        	myPeriod = n ;
            $("#showb").css({"width":stepW*n}) ;
            $(this).find('a').blur() ;
            return stopDefault(e) ;
        });
    });
	/**
	 * 课程比赛鼠标选中显示播放图片
	 */
	$('.show-content .content-top').each(function(){
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
});

/**
 * 显示首页最新比赛
 */
function showTheNewestMatches( newestNum ) {
	$.getJSON('./servlet/queryTheNewestMatchesServlet',{
		IndexNum:newestNum,
	},function(json){
		// 轮播大图初始化
		var $nmbi = $('#newestMatchBigImg') ; // 轮播大图ul标签
		$nmbi.html('') ; // 轮播大图清空
		var nmbiTemp = '' ; // 轮播大图加载临时模块
		
		// 轮播大图遮罩介绍
		var $nmbia = $('#newestMatchBigImgAlt') ; // 轮播大图遮罩ul
		$nmbia.html('') ; // 轮播大图瀑布流清空
		var nmbiaTemp = '' ; // 轮播大图遮罩临时模块
		
		// 轮播图片右边简略显示
		var $nrmu = $('#newestRightMatchesUl') ; //轮播图片右ul
		$nrmu.html('') ; //轮播图片右ul清空
		var nrmuTemp = '' ; // //轮播图片右ul临时模块
		
		
		var mhref = '' ;
		var imgsrc = '' ;
		var mName = '' ;
		var startTime = '' ;
		var endTime = '' ;
		var master_href = '' ;
		var nmtemp = '' ;
		var begintime = [] ;
		var stoptime = [] ;
		for ( var i = 0 ; i < indexNum ; i ++ ) {
			imgsrc = json[i].MatchPicture ; // 比赛图片路径
			mhref = 'jsp/Match/MatchInfo.jsp?MatchNum=' + json[i].MatchNum ; // 比赛超链接地址
			
			mName = json[i].MatchName ; // 比赛名称
			var miniName = mName ;
			if( miniName.length > 13 ){
				miniName = miniName.substring(0, 11) ;
				miniName += '...' ;
			}
			
			mContent = json[i].MatchContent ; // 比赛概述
			var miniMaster = json[i].NickName ; // 比赛创建者昵称
			if( miniMaster.length > 7 ) {
				miniMaster = miniMaster.substring( 0, 5 ) ;
				miniMaster += '...' ;
			}
			
			startTime = json[i].StartTime ; // 比赛开始时间
			endTime = json[i].EndTime ; // 比赛结束时间
			begintime = startTime.split('-') ;
			stoptime = endTime.split('-') ;
			startTime = begintime[1] + '.' + begintime[2] ;
			endTime = stoptime[1] + '.' + stoptime[2] ;	
			master_href = 'person.jsp?UserNum=' + json[i].UserNum ; // 比赛创建者个人主页链接地址
			if( i == 0 ) {
				// 加载轮播大图
				nmbiTemp = '<li style="display: list-item;"><a href="'+mhref+'" target="_blank"><img src="'+imgsrc+'"></a></li>' ;
				// 加载轮播大图瀑布流介绍
				nmbiaTemp = '<li style="display: list-item;">'
					 	+ '<x3><a href="'+mhref+'" target="_blank">'+mName+'</a></x3>'
					 	+ '<p>'+mContent+'</p>'
				     	+ '</li>' ;
				// 加载轮播图片右Ul
				nrmuTemp = '<li class="current">'
					+ '<a href="'+mhref+'" target="_blank">'
						+ '<img src="'+imgsrc+'">'
						+ '<span class="select_text">'+miniName+'</span>'
						+ '<span class="select_date"><x>'+miniMaster+'</x>&nbsp&nbsp<x>'+startTime+'</x>-<x>'+endTime+'</x></span>'
					+ '</a>'
					+ '</li>' ;
			} else {
				// 加载轮播大图
				nmbiTemp += '<li style="display: none;"><a href="'+mhref+'" target="_blank"><img src="'+imgsrc+'"></a></li>' ;
				// 加载轮播大图瀑布流介绍
				nmbiaTemp += '<li style="display: none;">'
					 	+ '<x3><a href="'+mhref+'" target="_blank">'+mName+'</a></x3>'
					 	+ '<p>'+mContent+'</p>'
				     	+ '</li>' ;
				// 加载轮播图片右Ul
				nrmuTemp += '<li class="">'
					+ '<a href="'+mhref+'" target="_blank">'
						+ '<img src="'+imgsrc+'">'
						+ '<span class="select_text">'+miniName+'</span>'
						+ '<span class="select_date"><x>'+miniMaster+'</x>&nbsp&nbsp<x>'+startTime+'</x>-<x>'+endTime+'</x></span>'
					+ '</a>'
					+ '</li>' ;
			}
			$nmbi.html( nmbiTemp ) ;
			$nmbia.html( nmbiaTemp ) ;
			$nrmu.html( nrmuTemp ) ;
			
		}
		newestMatchNum = json[0].MatchNum ;
		newestMatchTemp = json[0].MatchTemp ;
		// 如果比赛已结束，则显示决赛的参赛者
		if( newestMatchTemp == 3 ) {
			newestMatchTemp = 2 ;
		}
		secondMatchNum = json[1].MatchNum ;
		$('#secondMatchTitle').html( json[1].MatchName ) ;
		var $nmd1 = $('#newestMatchDiv1') ;
		mhref = 'jsp/Match/MatchInfo.jsp?MatchNum=' + json[0].MatchNum ;
		$nmd1.html('<span class="dividing-text-left"></span><span class="dividing-text-center">' + json[0].MatchName + '</span><span class="dividing-text-right"></span> <a class="dividing-more" href="' + mhref + '">更多>></a>') ;				
	});
	showNewestRegisters( newestMatchNum, newestMatchTemp) ;
}

/**
 * 显示最新比赛的参赛者
 */
function showNewestRegisters() {
	$.getJSON('./servlet/QueryRegisterByMatchNumServlet',{
		MatchNum:newestMatchNum,
		MatchTemp:newestMatchTemp,
	},function(json){
		var $nrb = $('#newestRegisterBody') ;
		var nrbtemp = '' ;
		$nrb.html('') ;
		var url = '' ;
		var cname = '' ;
		for ( var i = 0 ; i < json.length ; i ++ ) {
			if ( i == indexNum ) {
				break ;
			}
			url = 'jsp/Course/courseInfo.jsp?CourseNum=' + json[i].CourseNum ;
			cname = json[i].CourseName ;
			if ( cname.length >= 16 ) {
				cname = cname.substring(0, 12) ;
				cname += '...' ;
			}
			if( LoginUserType != 3 || LoginUserNum == '' ) {
				nrbtemp += '<div class="show-content">'
						+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
							+ '<div class="top-right-new" ><img src="Images/new-mark.png" alt="展示图片"/>'
							+ '</div>'
							+ '<div class="landscape">'
								+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
							+ '</div>'
							+ '<div class="interactive" >'
								+ '<div class="score">'
									+ '<div class="score-pic"><img src="Images/show-score.jpg" alt="得分"/></div>'
									+ '<a style="color:#959595;">得分:</a> <a style="color:#959595;">'+json[i].Poll+'分</a>'
								+ '</div>'
								+ '<div class="vote">'
									+ '<div class="vote-pic"><img src="Images/show-vote.jpg" alt="投票"/></div>'
									+ '<a style="color:#959595;">投票:</a> <a style="color:#959595;">' + json[i].PollNum + '人</a>'
								+ '</div>'
							+ '</div>'
						+ '</div>'
					+ '</div>' ;
			} else {
				var method = 'chooseCourse("'+ json[i].CourseNum +'")' ;
				nrbtemp += '<div class="show-content">'
					+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
					+ '<div class="top-right-new" ><img src="Images/new-mark.png" alt="展示图片"/>'
					+ '</div>'
					+ '<div class="landscape">'
					+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
					+ '<div class="landscape-right" ><button onclick = ' + method + ' type="button" class="votebtn" data-toggle="modal" data-target="#periodModal" >评分</button></div>'
					+ '</div>'
					+ '<div class="interactive" >'
					+ '<div class="score">'
					+ '<div class="score-pic"><img src="Images/show-score.jpg" alt="得分"/></div>'
					+ '<div> <a style="color:#959595;">得分:</a> <a style="color:#959595;">'+json[i].Poll+'分</a> </div>'
					+ '</div>'
					+ '<div class="vote">'
					+ '<div class="vote-pic"><img src="Images/show-vote.jpg" alt="投票"/></div>'
					+ '<a style="color:#959595;">投票:</a> <a style="color:#959595;">' + json[i].PollNum + '人</a> </div>'
					+ '</div>'
					+ '</div>'
					+ '</div>' ;
			}
		}
		$nrb.html(nrbtemp) ;
	});
}

/**
 * 显示最新创建的课程
 * @param cnum：显示的课程数量
 */
function showNewestCourses( cnum ) {
	$.getJSON('./servlet/queryNewestCoursesServlet',{
		IndexNum:cnum,
	},function(json){
		var $ncb = $('#newestCoursesBody') ;
		$ncb.html('<div class="dividing-short" ><span class="dividing-text-left"></span><span class="dividing-text-center">最新微课</span><span class="dividing-text-right"></span><a class="dividing-more" href="index.jsp">更多>></a></div>') ;
		var url = '' ;
		var cname = '' ;
		var nctemp = '' ;
		for ( var i = 0 ; i < cnum ; i ++ ) {
			if ( json[i].Period == 0 ) {
				cnum++ ;
			} else {
				url = 'jsp/Course/courseInfo.jsp?CourseNum=' + json[i].CourseNum ;
				cname = json[i].CourseName ;
				if ( cname.length >= 16 ) {
					cname = cname.substring(0, 12) ;
					cname += "..." ;
				}
				nctemp += '<div class="show-content-little">'
					+ '<div  class="content-top" >'
					+ '<a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="'+json[i].CourseName+'" /></a>'
					+ '</div>'
					+ '<div class="landscape">'
					+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
					+ '</div>'
					+ '<div class="interactive" >'
					+ '<div class="class-left">'
					+ '<div class="pic-font"><img src="Images/clock.png" /></div>'
					+ '<div style="-moz-user-select:-moz-none;" onselectstart="return false;"><a style="color:#959595;">' + json[i].Period + ' 章节</a></div>'
					+ '</div>'
					+ '<div class="class-right">'
					+ '<div style="-moz-user-select:-moz-none;" onselectstart="return false;"><a style="color:#959595;">' + json[i].AudienceNum + '人观看</a></div>'
					+ '</div>'
					+ '</div>'
					+ '</div>' ;		
			}
		}
		$ncb.append(nctemp) ;
	});
}

/**
 * 显示首页热门课程
 * @param cNum：显示的课程数量
 */
function showHotCourses( cnum ) {
	$.getJSON('./servlet/queryPopularCoursesServlet',{
		IndexNum:cnum,
	},function(json){
		var $hcb = $('#hotCoursesBody') ;
		$hcb.html('<div class="dividing-short" ><span class="dividing-text-left"></span><span class="dividing-text-center">热门微课</span><span class="dividing-text-right"></span><a class="dividing-more" href="index.jsp">更多>></a></div>') ;
		var url = '' ;
		var cname = '' ;
		var hctemp = '' ;
		for ( var i = 0 ; i < cnum ; i ++ ) {
			if ( json[i].Period == 0 ) {
				cnum++ ;
			} else {
				url = 'jsp/Course/courseInfo.jsp?CourseNum=' + json[i].CourseNum ;
				cname = json[i].CourseName ;
				if ( cname.length >= 16 ) {
					cname = cname.substring(0, 12) ;
					cname += "..." ;
				}
				hctemp += '<div class="show-content-little">'
					+ '<div  class="content-top" >'
					+ '<a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="'+json[i].CourseName+'" /></a>'
					+ '</div>'
					+ '<div class="landscape">'
					+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
					+ '</div>'
					+ '<div class="interactive" >'
					+ '<div class="class-left">'
					+ '<div class="pic-font"><img src="Images/clock.png" /></div>'
					+ '<div style="-moz-user-select:-moz-none;" onselectstart="return false;"><a style="color:#959595;">' + json[i].Period + ' 章节</a></div>'
					+ '</div>'
					+ '<div class="class-right">'
					+ '<div style="-moz-user-select:-moz-none;" onselectstart="return false;"><a style="color:#959595;">' + json[i].AudienceNum + '人观看</a></div>'
					+ '</div>'
					+ '</div>'
					+ '</div>' ;
			}
		}
		$hcb.append(hctemp) ;
	});

}

/**
 * 选择要评分的课程
 * @param cNum：选中的课程Num
 */
function chooseCourse ( cNum ) {
	chooseCNum = cNum ;
	$.getJSON("./servlet/isPollVoteServlet",{
		UserNum:LoginUserNum,
		CourseNum:chooseCNum,
		MatchNum:newestMatchNum,
		MatchTemp:newestMatchTemp,
	},function(json){
		if ( json.result == true ) {
			alert('您已经投过票了！') ;
			location.reload() ;
		}
	}) ;
}
/**
 * 评分
 */
function givePeriod() {
	if( myPeriod == 0 ) {
		alert('请先选择一个您感觉合适的分数！') ;
	} else {
		$.getJSON('./servlet/PollVoteServlet',{
			MatchNum:newestMatchNum,
			UserNum:LoginUserNum,
			CourseNum:chooseCNum,
			Poll:myPeriod,
			MatchTemp:newestMatchTemp,
		},function(json){
			var check = json.result ;
			if ( check == 0 ) {
				myPeriod = 0 ;
			} else if ( check == 1 ) {
				alert('投票失败！') ;
			} else if ( check == 2 ) {
				alert('您已经投过票！') ;
			} else if ( check == 3 ) {
				alert('您还未订阅该课程,请先进入课程详情页面，订阅课程！') ;
				this.location.href = 'jsp/Course/courseInfo.jsp?CourseNum=' + chooseCNum ;
			} else if ( check == 4 ) {
				alert('该课程并未报名该比赛！') ;
			}
			location.reload();
		}) ;
	}
}
/**
 * 判断访问比赛的用户
 */
function isMyMatch() {
	var check = false ;
	// 管理员或学院用户，判断是否为本比赛的创建者
	if ( LoginUserType == 0 || LoginUserType == 1 ) {
		var $otherOption = $('.otherOption') ;
		$.getJSON("./servlet/isMyMatchServlet",{
			UserNum:LoginUserNum,
			MatchNum:newestMatchNum,
		},function(json) {
			if ( json.result == 0 ) {
				check = true ;
			}
		});			
	} 
	showAward( check ) ;
}
/**
 * 获取首条比赛奖项
 */
function showAward( check ) {
	var temp = '' ;
	var aname = '' ;
	var aremark = '' ;
	var cname = '' ;
	var chref = '' ;
	var uname = '' ;
	// check=true时，表示登录用户为比赛的创建者，否则check=false并非该比赛的创建者
	if( check ) {
		$('#awardTable').html('<tr class="info"><td>奖项名称</td><td>获奖课程</td><td>课程讲师</td><td class="otherOption" >操作</td></tr>') ; 
		$.getJSON('./servlet/QueryMatchAwardServlet',{
			MatchNum:newestMatchNum,
		},function(json) {
			if( json != null ) {
				var optionBtn = '' ;
				for ( var i = 0 ; i < json.length ; i ++ ) {
					aname = json[i].AwardName ;
					aremark = json[i].Remark ;
					uname = json[i].NickName ;
					chref = 'jsp/Course/courseInfo.jsp?CourseNum=' + json[i].CourseNum ;
					var method = 'chooseAward(' + json[i].ID + ')' ;
					if( json[i].CourseNum == 'undefined' || json[i].CourseName == null ) {
						cname = '未颁奖' ;
						optionBtn = '<button onclick=' + method + ' type = "button" class="btn btn-success" data-toggle="modal" data-target="#giveAwardModal">颁奖</button>' ;
					} else {
						cname = '<a href="'+chref+'" >'
						+ json[i].CourseName + '</a>' ;
						optionBtn = '<button onclick=' + method + ' type = "button" class="btn btn-success" data-toggle="modal" data-target="#giveAwardModal">重新颁奖</button>' ;
					}
					temp += '<tr>'
						+ '<td>' + aname + '</td>'
						+ '<td>' + cname + '</td>'
						+ '<td>' + uname + '</td>'
						+ '<td class="otherOption">'+ optionBtn +'</td>'
						+ '</tr>';
				}				
			} else {
				$('#awardTable').html('<tr class="warning"><td>奖项名称</td><td>奖项概述</td><td>获奖课程</td></tr>') ; 
				temp = '<tr>'
					+ '<td>无奖项</td>'
					+ '<td>无奖项</td>'
					+ '<td>无奖项</td>'
					+ '</tr>';
			}
		});
	} else {
		$('#awardTable').html('<tr class="info"><td>奖项名称</td><td>获奖课程</td><td>课程讲师</td></tr>') ; 
		$.getJSON('./servlet/QueryMatchAwardServlet',{
			MatchNum:newestMatchNum,
		},function(json) {
			if ( json != null ) {
				var optionBtn = '' ;
				for ( var i = 0 ; i < json.length ; i ++ ) {
					aname = json[i].AwardName ;
					aremark = json[i].Remark ;
					uname = json[i].NickName ;
					chref = 'jsp/Course/courseInfo.jsp?CourseNum=' + json[i].CourseNum ;
					if( json[i].CourseNum == 'undefined' || json[i].CourseName == null ) {
						cname = '未颁奖' ;
					} else {
						cname = '<a href="'+chref+'" >'
						+ json[i].CourseName + '</a>' ;
					}
					temp += '<tr>'
						+ '<td>' + aname + '</td>'
						+ '<td>' + cname + '</td>'
						+ '<td>' + uname + '</td>'
						+ '</tr>';
				}				
			} else {
				temp = '<tr>'
					+ '<td>无奖项</td>'
					+ '<td>无奖项</td>'
					+ '<td>无奖项</td>'
					+ '</tr>';
			}
		});
	}
	$('#awardTable').append( temp ) ; 
}

function stopDefault(e){
    if(e && e.preventDefault)
           e.preventDefault();
    else
           window.event.returnValue = false;
    return false;
}
