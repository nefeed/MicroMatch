function AddAward() {
	var AwardName = $("#NewAwardName").val() ;
	$.getJSON("./servlet/AddNewAwardServlet",{
		MatchNum:MatchNum,
		AwardName:AwardName,
	},function(json){
		if( json.result == 0 ) {
			alert("新增奖项成功！") ;
			$("#NewAwardName").val("") ;
		}else {
			alert("新增奖项失败！") ;
		}		
	});
}