//$(function(){
//	laypage({
//        cont: 'page', //容器。值支持id名、原生dom对象，jquery对象,
//        pages: 20, //总页数
//        skip: false, //是否开启跳页
//        skin: 'molv',
//        groups: 6, //连续显示分页数
//        first: '首页', //若不显示，设置false即可
//        last: '尾页', //若不显示，设置false即可
//        prev: false, //若不显示，设置false即可
//        next: false, //若不显示，设置false即可
//        hash: true, //开启hash
//        jump: function(obj){ //触发分页后的回调
//            $('.view').html('目前正在第'+ obj.curr +'页，一共有：'+ obj.pages +'页');
//			alert( '当前所在页：' + obj.curr ) ;
//        }
//    });
//})
	
	
	
function changeImg(){
 var imgSrc = $("#imgObj");
 var src = imgSrc.attr("src");
 imgSrc.attr("src",chgUrl(src));
}
//时间戳
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url){
 var timestamp = (new Date()).valueOf();
 url = url.substring(0,17);
 if((url.indexOf("&")>=0)){
  url = url + "×tamp=" + timestamp;
 }else{
  url = url + "?timestamp=" + timestamp;
 }
 return url;
}
function isRightCode(){
 var code = $("#veryCode").attr("value");
 code = "c=" + code;
 $.ajax({
  type:"POST",
  url:"resultServlet",
  data:code,
  success:callback
 });
}
function callback(data){
 $("#info").html(data);
}