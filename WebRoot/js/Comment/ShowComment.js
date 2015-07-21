$(function(){
	$.getJSON("./servlet/QueryCommentByObjectTypeAndNumServlet",{
		ObjectType:ObjectType,
		ObjectNum:CourseNum,
	},function(json){
		var $c = $("#CommentTable") ;
		if(json.length!=0){
			var ctemp = "" ;
			for(var i=0 ; i<json.length ; i++ ){
				ctemp+="<tr><td>"
					+ json[i].CommentTime + "</td><td>"
					+ json[i].NickName + "</td><td>"
					+ json[i].CommentContent + "</td>"
					+ "</tr>" ;
			}
			$c.append( ctemp ) ;			
		}
	});
});