var awardId = 0 ;
var myPeriod = 0 ;
var chooseCNum = '' ;
var matchTemp = 0 ;
var p = 1 ; // 当前选择的页数
var cJson ;
$(function(){
	$.ajaxSettings.async = false ;
	var $pb = $('#preliminaryBody') ; // 初赛参赛者列表
	$.getJSON("./servlet/QueryMatchByMatchNumServlet",{
		MatchNum:MatchNum,
	},function(json){
		$('#matchName').html(json.MatchName) ;
		$('#matchContent').html(json.MatchContent) ;
		$('#registNum').html( json.RegistrationNum + ' 节课程' ) ;
		$('#matchPicture').attr( 'src' , json.MatchPicture ) ;
		$('#matchStart').html(json.StartTime) ;
		$('#matchEnd').html(json.EndTime) ;
		$('#matchMaker').html("创建者： " + json.NickName) ;
		$('#matchMaker').attr('href','../../person.jsp?UserNum='+json.UserNum) ;
		$('#publishTime').html("发布时间： " + json.PublishTime) ;
		
		
		$("#newMatchName").val(json.MatchName) ;
		$("#newMatchContent").val(json.MatchContent) ;
		$("#newStartTime").val(json.StartTime) ;
		$("#newEndTime").val(json.EndTime) ;
		// 比赛阶段，0 初赛 1 复赛 2 决赛 3 已经结束
		matchTemp = json.MatchTemp ;
		showRegists() ;
		isMyMatch() ;
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
	});
	$("#courseSelect").change(function(){
         $("#courseSelect option").each(function(i,o){
        	 showChapterSelect($("#courseSelect").val()) ;
         });
    });
});

/**
 * 点击页数显示初赛课程
 */
function pageCourses() {
	$('#preliminaryBody').html( '' ) ;
	var prtemp = '' ;
	if( cJson == 'undefined' || cJson == null || cJson.length == 0 ){
		var showResult = '<p class = "showResult" >没有参赛课程！</p>' ;
		$("#withoutResult").append(showResult) ;
	} else {
		var max = p * 8 ;
		for( var i = ( p - 1 ) * 8 ; i < max ; i ++ ){
			if ( i == cJson.length ) {
				break ;
			}
			url = 'course.jsp?CourseNum=' + cJson[i].CourseNum + '&ListId=0' ;
			var cname = cJson[i].CourseName ;
			if ( cname.length >= 16 ) {
				cname = cname.substring(0, 12) ;
				cname += "..." ;
			}
			if( LoginUserType == 3 ) {
				if( matchTemp == 0 ) {
					var method = 'chooseCourse("'+ cJson[i].CourseNum +'")' ;
					prtemp += '<div class="show-content">'
						+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ cJson[i].CoverPicture +'" alt="展示图片" /></a>'
						+ '<div class="content-top-left"><img src="Images/bronze.png" alt="初赛奖牌图片"/></div>'
						+ '<div class="content-top-right"><img src="Images/bronze-first.png" alt="初赛锦旗图片"/></div>'
						+ '</div>'
						+ '<div class="landscape">'
						+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
						+ '<div class="landscape-right" ><button onclick = ' + method + ' type="button" class="votebtn" data-toggle="modal" data-target="#periodModal" >评分</button></div>'
						+ '</div>'
						+ '<div class="interactive" >'
						+ '<div class="score">'
						+ '<div class="score-pic"><img src="Images/show-score.jpg" alt="得分"/></div>'
						+ '<div> <a style="color:#959595;">得分:</a> <a style="color:#959595;">'+cJson[i].Poll+'分</a> </div>'
						+ '</div>'
						+ '<div class="vote">'
						+ '<div class="vote-pic"><img src="Images/show-vote.jpg" alt="投票"/></div>'
						+ '<a style="color:#959595;">投票:</a> <a style="color:#959595;">' + cJson[i].PollNum + '人</a> </div>'
						+ '</div>'
						+ '</div>'
						+ '</div>' ;					
				} else {
					prtemp += '<div class="show-content">'
						+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ cJson[i].CoverPicture +'" alt="展示图片" /></a>'
						+ '<div class="content-top-left"><img src="Images/bronze.png" alt="初赛奖牌图片"/></div>'
						+ '<div class="content-top-right"><img src="Images/bronze-first.png" alt="初赛锦旗图片"/></div>'
						+ '</div>'
						+ '<div class="landscape">'
						+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
						+ '</div>'
						+ '<div class="interactive" >'
						+ '<div class="score">'
						+ '<div class="score-pic"><img src="Images/show-score.jpg" alt="得分"/></div>'
						+ '<div> <a style="color:#959595;">得分:</a> <a style="color:#959595;">'+cJson[i].Poll+'分</a> </div>'
						+ '</div>'
						+ '<div class="vote">'
						+ '<div class="vote-pic"><img src="Images/show-vote.jpg" alt="投票"/></div>'
						+ '<a style="color:#959595;">投票:</a> <a style="color:#959595;">' + cJson[i].PollNum + '人</a> </div>'
						+ '</div>'
						+ '</div>'
						+ '</div>' ;
				}
			} else {
				prtemp += '<div class="show-content">'
					+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ cJson[i].CoverPicture +'" alt="展示图片" /></a>'
					+ '<div class="content-top-left"><img src="Images/bronze.png" alt="初赛奖牌图片"/></div>'
					+ '<div class="content-top-right"><img src="Images/bronze-first.png" alt="初赛锦旗图片"/></div>'
					+ '</div>'
					+ '<div class="landscape">'
					+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
					+ '</div>'
					+ '<div class="interactive" >'
					+ '<div class="score">'
					+ '<div class="score-pic"><img src="Images/show-score.jpg" alt="得分"/></div>'
					+ '<div> <a style="color:#959595;">得分:</a> <a style="color:#959595;">'+cJson[i].Poll+'分</a> </div>'
					+ '</div>'
					+ '<div class="vote">'
					+ '<div class="vote-pic"><img src="Images/show-vote.jpg" alt="投票"/></div>'
					+ '<a style="color:#959595;">投票:</a> <a style="color:#959595;">' + cJson[i].PollNum + '人</a> </div>'
					+ '</div>'
					+ '</div>'
					+ '</div>' ;
			}
		}
	}
	$('#preliminaryBody').html( prtemp ) ;
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
	if ( length % 8 == 0 ) {
		pageNum = length / 8 ;
	} else {
		pageNum = parseInt( length / 8 ) ;
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
/**
 * 遍历参赛者
 */
function showRegists() {
	
	var $fm = $('#finalMatch') ; // 决赛主体
	var $qm = $('#quarterMatch') ; // 复赛主体
	var $pm = $('#preliminaryMatch') ; // 初赛主体
	
	var $qb = $('#quarterBody') ; // 复赛赛参赛者列表
	var $fb = $('#finalBody') ; // 决赛赛参赛者列表
	var url = '' ;
	// 遍历初赛课程
	$.getJSON('./servlet/QueryRegisterByMatchNumServlet',{
		MatchNum:MatchNum,
		MatchTemp:0,
	},function(json){
		
		var prtemp = '' ;
		$('#preliminaryBody').html('') ;
		cJson = json ;	
		showPage( cJson.length ) ;
	});
	switch ( matchTemp ) {
		case 0 :
			$fm.hide() ;
			$qm.hide() ;
			break ;
		case 1:
			$fm.hide() ;
			// 遍历复赛课程
			$.getJSON('./servlet/QueryRegisterByMatchNumServlet',{
				MatchNum:MatchNum,
				MatchTemp:1,
			},function(json){
				var qrtemp = '' ;
				$qb.html('') ;
				for(var i=0 ; i<json.length ; i++ ){
					url = '../../course.jsp?CourseNum=' + json[i].CourseNum + '&ListId=0' ;
					var cname = json[i].CourseName ;
					if ( cname.length >= 20 ) {
						cname = cname.substring(0, 16) ;
						cname += "..." ;
					}
					if( LoginUserType == 3 ) {
						if( matchTemp == 1 ) {
							var method = 'chooseCourse("'+ json[i].CourseNum +'")' ;
							qrtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
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
						} else {
							qrtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
								+ '</div>'
								+ '<div class="landscape">'
								+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
					} else {
						qrtemp += '<div class="show-content">'
							+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
							+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
							+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
							+ '</div>'
							+ '<div class="landscape">'
							+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
				$qb.html( qrtemp ) ;
			});
			break ;
		case 2:
			// 遍历复赛课程
			$.getJSON('./servlet/QueryRegisterByMatchNumServlet',{
				MatchNum:MatchNum,
				MatchTemp:1,
			},function(json){
				var qrtemp = '' ;
				$qb.html('') ;
				for(var i=0 ; i<json.length ; i++ ){
					url = 'course.jsp?CourseNum=' + json[i].CourseNum + '&ListId=0' ;
					var cname = json[i].CourseName ;
					if ( cname.length >= 20 ) {
						cname = cname.substring(0, 16) ;
						cname += "..." ;
					}
					if( LoginUserType == 3 ) {
						if( matchTemp == 1 ) {
							var method = 'chooseCourse("'+ json[i].CourseNum +'")' ;
							qrtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
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
						} else {
							qrtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
								+ '</div>'
								+ '<div class="landscape">'
								+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
					} else {
						qrtemp += '<div class="show-content">'
							+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
							+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
							+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
							+ '</div>'
							+ '<div class="landscape">'
							+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
				$qb.html( qrtemp ) ;
			});
			// 遍历决赛课程
			$.getJSON('./servlet/QueryRegisterByMatchNumServlet',{
				MatchNum:MatchNum,
				MatchTemp:2,
			},function(json){
				var frtemp = '' ;
				$fb.html('') ;
				for(var i=0 ; i<json.length ; i++ ){
					url = 'course.jsp?CourseNum=' + json[i].CourseNum + '&ListId=0' ;
					var cname = json[i].CourseName ;
					if ( cname.length >= 20 ) {
						cname = cname.substring(0, 16) ;
						cname += "..." ;
					}
					if( LoginUserType == 3 ) {
						if( matchTemp == 2 ) {
							var method = 'chooseCourse("'+ json[i].CourseNum +'")' ;
							frtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/gold.png" alt="决赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-gold.png" alt="决赛锦旗图片"/></div>'
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
						} else {
							frtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/gold.png" alt="决赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-gold.png" alt="决赛锦旗图片"/></div>'
								+ '</div>'
								+ '<div class="landscape">'
								+ '<div class="landscape-left"><a href="' + url + '">'+cname+'</a></div>'
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
					} else {
						frtemp += '<div class="show-content">'
							+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
							+ '<div class="content-top-left"><img src="Images/gold.png" alt="决赛奖牌图片"/></div>'
							+ '<div class="content-top-right"><img src="Images/silver-gold.png" alt="决赛锦旗图片"/></div>'
							+ '</div>'
							+ '<div class="landscape">'
							+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
				$fb.html( frtemp ) ;
			});
			break ;
		case 3:
			// 遍历复赛课程
			$.getJSON('./servlet/QueryRegisterByMatchNumServlet',{
				MatchNum:MatchNum,
				MatchTemp:1,
			},function(json){
				var qrtemp = '' ;
				$qb.html('') ;
				for(var i=0 ; i<json.length ; i++ ){
					url = 'course.jsp?CourseNum=' + json[i].CourseNum + '&ListId=0' ;
					var cname = json[i].CourseName ;
					if ( cname.length >= 20 ) {
						cname = cname.substring(0, 16) ;
						cname += "..." ;
					}
					if( LoginUserType == 3 ) {
						if( matchTemp == 1 ) {
							var method = 'chooseCourse("'+ json[i].CourseNum +'")' ;
							qrtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
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
						} else {
							qrtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
								+ '</div>'
								+ '<div class="landscape">'
								+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
					} else {
						qrtemp += '<div class="show-content">'
							+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
							+ '<div class="content-top-left"><img src="Images/silver.png" alt="复赛奖牌图片"/></div>'
							+ '<div class="content-top-right"><img src="Images/silver-semi.png" alt="复赛锦旗图片"/></div>'
							+ '</div>'
							+ '<div class="landscape">'
							+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
				$qb.html( qrtemp ) ;
			});
			// 遍历决赛课程
			$.getJSON('./servlet/QueryRegisterByMatchNumServlet',{
				MatchNum:MatchNum,
				MatchTemp:2,
			},function(json){
				var frtemp = '' ;
				$fb.html('') ;
				for(var i=0 ; i<json.length ; i++ ){
					url = 'course.jsp?CourseNum=' + json[i].CourseNum + '&ListId=0' ;
					var cname = json[i].CourseName ;
					if ( cname.length >= 20 ) {
						cname = cname.substring(0, 16) ;
						cname += "..." ;
					}
					if( LoginUserType == 3 ) {
						if( matchTemp == 2 ) {
							var method = 'chooseCourse("'+ json[i].CourseNum +'")' ;
							frtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/gold.png" alt="决赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-gold.png" alt="决赛锦旗图片"/></div>'
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
						} else {
							frtemp += '<div class="show-content">'
								+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
								+ '<div class="content-top-left"><img src="Images/gold.png" alt="决赛奖牌图片"/></div>'
								+ '<div class="content-top-right"><img src="Images/silver-gold.png" alt="决赛锦旗图片"/></div>'
								+ '</div>'
								+ '<div class="landscape">'
								+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
					} else {
						frtemp += '<div class="show-content">'
							+ '<div class="content-top" ><a href="'+url+'" ><img class = "content-top-cp" src="'+ json[i].CoverPicture +'" alt="展示图片" /></a>'
							+ '<div class="content-top-left"><img src="Images/gold.png" alt="决赛奖牌图片"/></div>'
							+ '<div class="content-top-right"><img src="Images/silver-gold.png" alt="决赛锦旗图片"/></div>'
							+ '</div>'
							+ '<div class="landscape">'
							+ '<div class="landscape-left" ><a href="' + url + '">'+cname+'</a></div>'
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
				$fb.html( frtemp ) ;
			});
			break ;
		default:
			$fm.hide() ;
			$qm.hide() ;
			$pm.hide() ;
			break ;
	}
}

/**
 * 更新比赛活动操作
 */
function UpdateMatch() {
	var MatchName = "" ;
	var MatchContent = "" ;
	var StartTime = "" ;
	var EndTime = "" ;
	$("#MatchName").removeAttr('readOnly') ;
	$("#MatchContent").removeAttr('readOnly') ;
	$("#StartTime").removeAttr('readOnly') ;
	$("#EndTime").removeAttr('readOnly') ;
	$("#UpdateButton").html("Confirm") ;
	$("#UpdateButton").val("Confirm") ;
	MatchName = $("#MatchName").val() ;
	MatchContent = $("#MatchContent").val() ;
	StartTime = $("#StartTime").val() ;
	EndTime = $("#EndTime").val() ;
	if( $("#MatchName").val() != MatchName ||
			$("#MatchContent").val() != MatchContent ||
			$("#StartTime").val() != StartTime ||
			$("#EndTime").val() != EndTime ) {
		MatchName = $("#MatchName").val() ;
		MatchContent = $("#MatchContent").val() ;
		StartTime = $("#StartTime").val() ;
		EndTime = $("#EndTime").val() ;
		var begintime = [] ;
		var stoptime = [] ;
		begintime = StartTime.split('-') ;
		stoptime = EndTime.split('-') ;
		var eyear = parseInt(begintime[0]);
		var emonth = parseInt(begintime[1]);
		var eday = parseInt(begintime[2]);
		var syear = parseInt(stoptime[0]);
		var smonth = parseInt(stoptime[1]);
		var sday = parseInt(stoptime[2]);
		if( MatchName.length < 2 ) {
			alert( '输入的比赛名称长度过短，请修改后重新尝试！' ) ;
		} else if ( MatchName > 15 ) {
			alert( '输入的比赛名称长度过长，请修改后重新尝试！' ) ;
		} else if ( begintime == '' ){
			alert( '请选择一个开始时间后继续尝试！' ) ;
		} else if ( MatchContent.length > 220 ) {
			alert( '输入的比赛概述太长，请修改后重新尝试！' ) ;
		} else if ( stoptime == '' ) {
			alert( '请选择一个结束时间后继续尝试！' ) ;
		} else if ( (eyear <= syear) || 
				(eyear == syear && emonth <= smonth) || 
				(eyear == syear && emonth == smonth && eday <= sday) ) {
			alert( '比赛开始时间不能晚于比赛结束时间，请修改后继续尝试！' ) ;	
		} else {
			$.getJSON("./servlet/UpdateMatchServlet",{
				MatchNum:MatchNum,
				MatchName:MatchName,
				MatchContent:MatchContent,
				StartTime:StartTime,
				EndTime:EndTime,
			},function(json) {
				if ( json.result == 0 ) {
					$('#updateMatchModal').hide() ;
					$('#blockInit').hide() ;
					$('#blockInit').attr( 'style', 'display: none;' ) ;
					location.reload(true) ;
				} else if ( json.result == 1 ) {
					alert( "比赛修改失败！" ) ;
				}
			});	
		}
	} else {
		alert("您没有做任何修改！") ;
	}
	$("#UpdateButton").html("UpdateMatch") ;
	$("#MatchName").attr("readonly",true) ;
	$("#MatchContent").attr("readonly",true) ;
	$("#StartTime").attr("readonly",true) ;
	$("#EndTime").attr("readonly",true) ;
	$("#UpdateButton").val("UpdateMatch") ;
}

/**
 * 判断访问比赛的用户
 */
function isMyMatch() {
	var $registBody = $('#registBody') ;
	var $updateBody = $('#updateBody') ;
	$updateBody.html('') ;
	var check = false ;
	// 教师用户
	if( LoginUserType == 2 ){
		$registBody.show() ;
		showAllMyCourses() ;
	}
	// 管理员或学院用户，判断是否为本比赛的创建者
	else if ( LoginUserType == 0 || LoginUserType == 1 ) {
		var $otherOption = $('.otherOption') ;
		$.getJSON("./servlet/isMyMatchServlet",{
			UserNum:LoginUserNum,
			MatchNum:MatchNum,
		},function(json) {
			if ( json.result == 0 ) {
				$registBody.hide() ;
				var mt = '结束初赛' ;
				switch ( matchTemp ) {
				case 0:
					mt = '结束初赛';
					break;
				case 1:
					mt = '结束复赛';
					break;
				case 2:
					mt = '结束决赛';
					break;
				default:
					break;
				}
				var $cr = $('#chooseRegist') ; // 颁奖时遍历参赛者的选择框
				var crtemp = '<option value=0>请选择颁给奖项的课程</option>' ;
				for ( var i = 0 ; i < cJson.length ; i ++ ) {
					$cr.html('') ;
					if( cJson == 'undefined' || cJson == null || cJson.length == 0 ){
						crtemp = '<option value=0 selected = "selected" >无课程参赛！</option>' ;
					} else {
						crtemp += '<option value ="' + cJson[i].CourseNum + '">' + cJson[i].CourseName + '</option>' ;
					}
					$cr.html( crtemp ) ;	
				}
				var optionTemp = '' ;
				if ( matchTemp == 3 ) {
					optionTemp = '<button type="button" class="btn btn-info" data-toggle="modal" data-target="#updateMatchModal">修改比赛</button> '
						+ '<button id="updateBtn" type="button" class="btn btn-info" data-toggle="modal" data-target="#newAwardModal">新增奖项</button>' ;
				} else {
					optionTemp = '<button type="button" class="btn btn-info" data-toggle="modal" data-target="#updateMatchModal">修改比赛</button> '
						+ '<button id="updateBtn" type="button" class="btn btn-info" data-toggle="modal" data-target="#newAwardModal">新增奖项</button>'
						+ '<button id="updateBtn" type="button" class="btn btn-info" data-toggle="modal" data-target="#finishMatchModal">' + mt + '</button>' ;					
				}
				$updateBody.html(optionTemp) ;
				check = true ;
			}
		});			
	} 
	// 学生该用户
	else if ( LoginUserType == 3 ) {
		$registBody.hide() ;
	}
	showAward( check ) ;
}

/**
 * 在select选择框中读取自己创建的课程
 */
function showAllMyCourses() {
	if ( matchTemp == 0 ) {
		$('#courseSelect').html('') ;
		$.getJSON('./servlet/QueryCourseByUserNumServlet',{
			UserNum:LoginUserNum,
		},function(json) {
			var temp = '' ;
			if ( json == null || json.length == 0 ) {
				temp = '<option value="0" selected = "selected" >请您先创建属于自己的第一个课程</option>' ;
			} else {
				temp = '<option value="0" selected = "selected" >请选择您要报名参赛的课程</option>' ;
				for ( var i = 0 ; i < json.length ; i ++ ) {
					temp += '<option value ="' + json[i].CourseNum + '">' + json[i].CourseName + '</option>' ;
				}
			}
			$('#courseSelect').append(temp) ;
		}) ;		
	} else {
		var $registBody = $('#registBody') ;
		$registBody.html('') ;
		$registBody.hide() ;
	}
}

/**
 * 遍历本课程下所有章节
 * @param cNum
 */
function showChapterSelect( cNum ) {
	var $courseSelect = $('#courseSelect') ;
	var $chapterSelect = $('#chapterSelect') ;
	var scNum = $courseSelect.val();
	if( scNum == 0 ) {
		$chapterSelect.hide() ;
		$courseSelect.attr( 'style' , 'width: 800px;') ;
	} else {
		$courseSelect.attr( 'style' , 'width: 400px;') ;
		$chapterSelect.show() ;
		$chapterSelect.html('') ;
		$.getJSON('./servlet/QueryChapterByCourseNumServlet',{
			CourseNum:cNum,
		},function(json) {
			var temp = '' ;
			if ( json == null || json.length == 0 ) {
				temp = '<option value="0" selected = "selected" >请您先发布本课程下的第一篇章节！</option>' ;
			} else {
				temp = '<option value="0" selected = "selected" >请选择该课程要报名参赛的章节</option>' ;
				for ( var i = 0 ; i < json.length ; i ++ ) {
					temp += '<option value ="' + json[i].ChapterNum + '">' + json[i].ChapterName + '</option>' ;
				}
			}
			$chapterSelect.append(temp) ;
		}) ;
	}
}

/**
 * 确认报名比赛
 */
function confirmRegist() {
	var scNum = $('#courseSelect').val();
	if( scNum == 0 ) {
		alert('你无权报名参赛或未选择课程！') ;
	} else {
		$.getJSON('./servlet/RegistrationServlet',{
			CourseNum:scNum,
			MatchNum:MatchNum,
		},function(json){
			var check = json.result ;
			if( check == 2 ) {
				alert( '本课程已经报名该比赛！' ) ;
			} else if ( check == 0 ) {
				showRegists() ;
			} else if ( check == 1 ) {
				alert( '报名参赛失败！' ) ;
			}
		});		
	}
}

/**
 * 新建奖项
 */
function newAward() {
	var $nan = $('#newAwardName') ;
	var $nar = $('#newAwardRemark') ;
	var naName = $nan.val() ;
	var naRemark = $nar.val() ;
	if( naName.length < 0 || naName.length > 30 ) {
		alert( '你输入的奖项名称不合规，请重新尝试！' ) ;
	} else if( naRemark.length > 255 ) {
		alert( '你输入的奖项概述太长，请删减后重新尝试！' ) ;
	} else {
		$.getJSON('./servlet/AddNewAwardServlet',{
			UserNum:LoginUserNum,
			MatchNum:MatchNum,
			AwardName:naName,
			Remark:naRemark,
		},function(json) {
			var check = json.result ;
			if ( check == 0 ) {
				$('#newAwardName').html('') ;
				$('#newAwardRemark').html('') ;
				showAward( true ) ;
			} else if ( check == 1 ) {
				alert("新建奖项失败！") ;
			} else if ( check == 2 ) {
				alert("不是本人创建的比赛，无权操作！") ;
			}
		});
	}
}


/**
 * 获取该比赛奖项
 */
function showAward( check ) {
	var $atable = $('#awardTable') ;
	$atable.html('') ;
	$('#awardBody').hide() ;
	// 只有比赛结束状态或者是本比赛的创建者才显示比赛
	if( check || matchTemp == 3 ) {
		$('#awardBody').show() ;
		var temp = '' ;
		var aname = '' ;
		var aremark = '' ;
		var cname = '' ;
		var chref = '' ;
		if( check ) {
			$atable.html('<tr class="info"><td>奖项名称</td><td>奖项概述</td><td>获奖课程</td><td class="otherOption" >操作</td></tr>') ; 
			$.getJSON('./servlet/QueryMatchAwardServlet',{
				MatchNum:MatchNum,
			},function(json) {
				if( json != null ) {
					var optionBtn = '' ;
					for ( var i = 0 ; i < json.length ; i ++ ) {
						aname = json[i].AwardName ;
						aremark = json[i].Remark ;
						chref = '../Course/courseInfo.jsp?CourseNum=' + json[i].CourseNum ;
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
							+ '<td>' + aremark + '</td>'
							+ '<td>' + cname + '</td>'
							+ '<td class="otherOption">'+ optionBtn +'</td>'
							+ '</tr>';
					}				
				} else {
					$atable.html('<tr class="info"><td>奖项名称</td><td>奖项概述</td><td>获奖课程</td></tr>') ; 
					temp = '<tr>'
						+ '<td>无奖项</td>'
						+ '<td>无奖项</td>'
						+ '<td>无奖项</td>'
						+ '</tr>';
				}
			});
		} else {
			$atable.html('<tr class="info"><td>奖项名称</td><td>奖项概述</td><td>获奖课程</td></tr>') ; 
			$.getJSON('./servlet/QueryMatchAwardServlet',{
				MatchNum:MatchNum,
			},function(json) {
				if ( json != null ) {
					var optionBtn = '' ;
					for ( var i = 0 ; i < json.length ; i ++ ) {
						aname = json[i].AwardName ;
						aremark = json[i].Remark ;
						if( json[i].CourseNum == 'undefined' || json[i].CourseName == null ) {
							cname = '未颁奖' ;
						} else {
							cname = '<a href="'+chref+'" >'
							+ json[i].CourseName + '</a>' ;
						}
						temp += '<tr>'
							+ '<td>' + aname + '</td>'
							+ '<td>' + aremark + '</td>'
							+ '<td>' + cname + '</td>'
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
		$atable.append( temp ) ; 
	}
}

/**
 * 选择奖项操作
 * @param id
 */
function chooseAward( aid ) {
	awardId = parseInt( aid ) ;
	$.getJSON('./servlet/queryAwardByIdServlet',{
		aid:aid,
	},function(json){
		$('#giveAwardName').val( json.AwardName ) ;
		$('#giveAwardRemark').html( json.Remark ) ;		
	});
}


/**
 * 颁奖操作
 */
function giveAward() {
	var cNum = $('#chooseRegist').val() ;
	if( awardId == 0 ){
		alert('请选择至少一个奖项进行颁发！') ;
	} else if( cNum == 0 ) {
		alert('请选择选择课程后进行颁奖！') ;
	} else {
		$.getJSON('./servlet/GivenAwardServlet',{
			UserNum:LoginUserNum,
			MatchNum:MatchNum,
			AwardId:awardId,
			CourseNum:cNum,
		},function(json) {
			var check = json.result ;
			awardId = 0 ;
			$('#giveAwardModal').hide() ;
			$('#blockInit').hide() ;
			if ( check == 0 ) {
				showAward( true ) ;
			} else if ( check == 1 ) {
				alert("颁发奖项失败！") ;
			} else if ( check == 2 ) {
				alert("不是本人创建的比赛，无权操作！") ;
			}
		});		
	}
}

/**
 * 选择要评分的课程
 * @param courseNum
 */
function chooseCourse ( cNum ) {
	chooseCNum = cNum ;
	$.getJSON("./servlet/isPollVoteServlet",{
		UserNum:LoginUserNum,
		CourseNum:chooseCNum,
		MatchNum:MatchNum,
		MatchTemp:matchTemp,
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
			MatchNum:MatchNum,
			UserNum:LoginUserNum,
			CourseNum:chooseCNum,
			Poll:myPeriod,
			MatchTemp:matchTemp,
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
				this.location.href = '../Course/courseInfo.jsp?CourseNum=' + chooseCNum ;
			} else if ( check == 4 ) {
				alert('该课程并未报名该比赛！') ;
			}
			location.reload() ;
		}) ;		
	}
}

function stopDefault(e){
    if(e && e.preventDefault)
           e.preventDefault();
    else
           window.event.returnValue = false;
    return false;
}
/**
 * 
 * @Description: 结束当前阶段比赛
 * @param    
 * @return void  
 * @throws
 * @author Gavin
 * @date 2015年6月23日
 */
function finishMatchTemp() {
	$.getJSON('./servlet/StopMatchServlet',{
		UserNum:LoginUserNum,
		MatchNum:MatchNum,
		MatchTemp:matchTemp,
	},function(json){
		var check = json.result ;
		if( check == 2 ) {
			alert( '您并非该比赛的创建者！' ) ;
		} else if ( check == 0 ) {
			location.reload( true ) ;
		} else if ( check == 1 ) {
			alert( '当前阶段比赛结束失败！' ) ;
		}
		$('#finishMatchModal').hide() ;
		$('#blockInit').hide() ;
		$('#finishMatchModal').modal('hide') ;
	});	
}
