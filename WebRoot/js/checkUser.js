$(function(){
	$.ajaxSettings.async = false ;
	$.getJSON('./servlet/QueryAllUserServlet',{
		isLock:0,
	},function(json){
		var temp = '' ;
		var nickName = '' ;
		var userType = '' ;
		for( var i = 0 ; i < json.length ; i++ ){
			var url = 'person.jsp?UserNum'+json[i].UserNum ;
			nickName = '<a href='+url+'>'+json[i].NickName+'</a>' ;
			switch ( json[i].UserType ) {
				case 0 : userType = 'Administrator' ; break ;
				case 1 : userType = '学校用户' ; break ;
				case 2 : userType = '教师用户' ; break ;
				case 3 : userType = '学生用户' ; break ;
				default : userType = '错误用户类型' ; break ;
			}
			var userLock ;
			var lockType = -1 ;
			switch ( json[i].isLock ) {
				case 0 : userLock = '正常' ; lockType = 0 ; break ;
				case 1 : userLock = '锁定' ; lockType = 1 ; break ;
				default : userLock = '未知错误' ; break ;
			}
			temp = '<tr>'
				+ '<td>' + nickName + '</td>'
				+ '<td>' + json[i].RegTime + '</td>' 
				+ '<td>' + json[i].UserPhone + '</td>'
				+ '<td>' + userType + '</td>'
				+ '<td>' + userLock + '</td>'
				+ '<td><button class = "chooseBTN" onclick = checkUser(' + json[i].UserNum + ') >审核</button></td>' 
				+ '</tr>' + temp ;
		}
		$('#uncheck').append( temp ) ;
		hoverHaveCheck() ;
	});
	
	
});

function checkUser( userNum ) {
	alert('成功开始审核用户：'+userNum ) ;
}

function hoverUnCheck() {
	$.getJSON('./servlet/QueryAllUserServlet',{
		isLock:0,
	},function(json){
		var temp = '' ;
		var nickName = '' ;
		var userType = '' ;
		for( var i = 0 ; i < json.length ; i++ ){
			var url = 'person.jsp?UserNum'+json[i].UserNum ;
			nickName = '<a href='+url+'>'+json[i].NickName+'</a>' ;
			switch ( json[i].UserType ) {
				case 0 : userType = 'Administrator' ; break ;
				case 1 : userType = '学校用户' ; break ;
				case 2 : userType = '教师用户' ; break ;
				case 3 : userType = '学生用户' ; break ;
				default : userType = '错误用户类型' ; break ;
			}
			var userLock ;
			var lockType = -1 ;
			switch ( json[i].isLock ) {
				case 0 : userLock = '正常' ; lockType = 0 ; break ;
				case 1 : userLock = '锁定' ; lockType = 1 ; break ;
				default : userLock = '未知错误' ; break ;
			}
			temp = '<tr>'
				+ '<td>' + nickName + '</td>'
				+ '<td>' + json[i].RegTime + '</td>' 
				+ '<td>' + json[i].UserPhone + '</td>'
				+ '<td>' + userType + '</td>'
				+ '<td>' + userLock + '</td>'
				+ '<td><button class = "chooseBTN" onclick = checkUser(' + json[i].UserNum + ') >审核</button></td>' 
				+ '</tr>' + temp ;
		}
		$('#uncheck').append( temp ) ;
	});
}

function hoverHaveCheck() {
	$.getJSON('./servlet/QueryAllUserServlet',{
		isLock:1,
	},function(json){
		var temp = '' ;
		var nickName = '' ;
		var userType = '' ;
		for( var i = 0 ; i < json.length ; i++ ){
			var url = 'person.jsp?UserNum'+json[i].UserNum ;
			nickName = '<a href='+url+'>'+json[i].NickName+'</a>' ;
			switch ( json[i].UserType ) {
				case 0 : userType = 'Administrator' ; break ;
				case 1 : userType = '学校用户' ; break ;
				case 2 : userType = '教师用户' ; break ;
				case 3 : userType = '学生用户' ; break ;
				default : userType = '错误用户类型' ; break ;
			}
			var userLock ;
			var lockType = -1 ;
			switch ( json[i].isLock ) {
				case 0 : userLock = '正常' ; lockType = 0 ; break ;
				case 1 : userLock = '锁定' ; lockType = 1 ; break ;
				default : userLock = '未知错误' ; break ;
			}
			temp = '<tr>'
				+ '<td>' + nickName + '</td>'
				+ '<td>' + json[i].RegTime + '</td>' 
				+ '<td>' + json[i].UserPhone + '</td>'
				+ '<td>' + userType + '</td>'
				+ '<td>' + userLock + '</td>'
				+ '<td><button class = "chooseBTN" onclick = checkUser(' + json[i].UserNum + ') >锁死</button></td>' 
				+ '</tr>' + temp ;
		}
		$('#checked').append( temp ) ;
	});
}