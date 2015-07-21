$(function(){
	$.getJSON("./servlet/QueryAccessoryByObjectTypeAndNumServlet",{
		ObjectNum:ObjectNum,
		ObjectType:ObjectType,
	},function(json){
		var $at = $("#AccessoryTable");
		var attemp = "" ;
		if( json.length != 0 ){
			var accessoryName = "" ;
			for(var i=0;i<json.length;i=i+1){
				accessoryName = "<a href=./servlet/DownloadServlet?AccessoryName="+json[i].AccessoryName
				+"&UserNum="+LoginUserNum
				+"&ObjectNum="+ObjectNum
				+"&ObjectType="+ObjectType
				+"&AccessoryID="+json[i].AccessoryID+" style='text-decoration:none;text-align:center'>"
				+ json[i].AccessoryName+"</a>" ;
				attemp+="<tr><td>"
					+ accessoryName + "</td><td>"
					+ "<a href=./servlet/DownloadServlet?AccessoryName="+json[i].AccessoryName
					+"&UserNum="+LoginUserNum
					+"&ObjectNum="+ObjectNum
					+"&ObjectType="+ObjectType
					+"&AccessoryID="+json[i].AccessoryID+">"+Download+"</a>" + "</td><td>"
					+ json[i].CreateTime + "</td><td>"
					+ json[i].DownloadNum + "</td>"
					+ "</tr>" ;
			}
		}else {
			attemp = "<tr><td>"
				+ "附件为空" + "</td><td>"
				+ "不允许下载！" + "</td>"
				+ "</tr>" ;
		}
		$at.append( attemp ) ;			
	});
});