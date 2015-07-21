$(function(){
	$.getJSON("./servlet/QueryUserInfoServlet",{
		UserNum:LoginUserNum,
	},function(json){
		$('#wkUserPicture').attr( 'src' , json.UserPicture ) ;
		$('#wkNickName').text(json.NickName) ;
		$('#wkNickName').attr( 'href' , 'person.jsp?UserNum='+LoginUserNum+'&NickName='+LoginNickName+'&UserType='+LoginUserType ) ;		
	});
});