/*****幻灯片start****/
var flash=document.getElementById('flash');
var flashLeft=document.getElementById('flash_left');//左边
var flashRight=document.getElementById('flash_right');//右边
var lis=flash.getElementsByTagName('li');//flash下所有的li
var spans=flash.getElementsByTagName('span');//flash下所有的span
flash.onmouseover=function(){
	flashLeft.style.display='block';
	flashRight.style.display='block';
	clearInterval(dodo);
};
flash.onmouseout=function(){
	flashLeft.style.display='none';
	flashRight.style.display='none';
	dodo=setInterval(rightDo,3000);//每隔3秒执行一次
};
for(var i=0;i<spans.length;i++)
{
	spans[i].onmouseover=function(){
		var oldNum,newNum;//定义旧的位置与新的位置
		if(this.className=='flash_btnCur'){//当前span已经有样式就直接跳出
			return;//直接跳出
		}
		this.setAttribute('flash','true');//给当前鼠标移上去的span标记一个属性
		for(var j=0;j<spans.length;j++)
		{
			if(spans[j].getAttribute('flash')=='true')//通过循环判断哪个一个span上有标记
			{
				newNum=j;
				spans[j].removeAttribute('flash');//防止下一次标记的时候之前的没被清楚，产生混乱
			}
			if(spans[j].className=='flash_btnCur')//循环判断找出现在span上有这个样式的位置
			{
				oldNum=j;
			}
		}
		opacity(0,newNum,oldNum);
	}
}

var bool=1;//变为1表示递归完成了；0表示是递归正在进行中
flashRight.onclick=rightDo;

flashLeft.onclick=function(){
	var oldNum,newNum;//定义旧的位置与新的位置
	for(var i=0;i<lis.length;i++)
	{
		if(lis[i].className=='flash_imgCur')//通过循环判断得到现在li有样式的li位置
		{
			oldNum=i;
			break;//跳出循环
		}
	}
	if(oldNum==0)
	{
		newNum=lis.length-1;
	}
	else
	{
		newNum=oldNum-1;
	}
	if(bool==1)
	{
		bool=0;
		opacity(0,newNum,oldNum);
	}
};

function rightDo(){
	var oldNum,newNum;//定义旧的位置与新的位置
	for(var i=0;i<lis.length;i++)
	{
		if(lis[i].className=='flash_imgCur')//通过循环判断得到现在li有样式的li位置
		{
			oldNum=i;
			break;//跳出循环
		}
	}
	if(oldNum!=lis.length-1)//正常点击右边按钮，下一张变成不透明度1；之前一张不透明度变为0
	{
		 newNum=oldNum+1;
	}
	else//临界点；之前那张li已经是最后一个位置时；下一张图就是第一个位置
	{
		newNum=0;
	}
	if(bool==1)
	{
		bool=0;
		opacity(0,newNum,oldNum);
	}
	
}

function opacity(num,newNum,oldNum){
	spans[newNum].className='flash_btnCur';
	spans[oldNum].className='';
	if(!document.all)//非ie
	{
		num+=5;
	}
	else//IE
	{
		num+=20;
	}
	if(num<=100)
	{
		
			lis[oldNum].style.opacity=(100-num)/100;//支持非IE
			lis[newNum].style.opacity=num/100;//支持非IE
			lis[oldNum].style.filter="alpha(opacity="+(100-num)+')';//支持IE
			lis[newNum].style.filter="alpha(opacity="+num+')';//支持IE
	}
	else//表示递归完成
	{
		lis[oldNum].className='';
		lis[newNum].className='flash_imgCur';
		bool=1;//变为1表示递归完成了
		return;//跳出方法
	}
	return setTimeout(function(){opacity(num,newNum,oldNum);},20);
}


var dodo=setInterval(rightDo,3000);//每隔3秒执行一次
/*if(document.all)//IE
{
	lis[0].style.filter="alpha(opacity="+num+")";
}
else//非IE
{
	lis[0].style.opacity=num;
}*/
/*****幻灯片end****/
