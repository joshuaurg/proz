var yesOrNo = true;
var _callback ;
function confirmOpt(tip,callback){
	_callback = callback;
	$("#tip").html(tip);
	$("#confirm_container").show();
}
function tipShow(tip){
	$("#suggestion").html(tip);
	$("#tip_container").show();
}
function confirm(){
	_callback();
	close();
}
function cancel(){
	close();
}
//视频播放通用方法
function play(videoUrl){ 
	$("#play-container").find("video").attr("src",videoUrl);
	$(".theme-popover").show();
}
$(function(){
	$('.theme-poptit .close').click(function(){
		close();
	});
});
function close(){
	$('.theme-popover-mask').fadeOut(100);
	$('.theme-popover').slideUp(200);
}

//图片预览通用方法
function previewImage(file){  
  var MAXWIDTH  = 200;  
  var MAXHEIGHT = 200;  
  var div = document.getElementById('preview');  
  if (file.files && file.files[0]){  
    div.innerHTML = '<img id=imghead>';  
    var img = document.getElementById('imghead');  
    img.onload = function(){  
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
      img.width = rect.width;  
      img.height = rect.height;  
      img.style.marginLeft = rect.left+'px';  
      img.style.marginTop = rect.top+'px';  
    }  
    var reader = new FileReader();  
    reader.onload = function(evt){img.src = evt.target.result;}  
    reader.readAsDataURL(file.files[0]);  
  }else{  
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';file.select();  
    var src = document.selection.createRange().text;  
    div.innerHTML = '<img id=imghead>';  
    var img = document.getElementById('imghead');  
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;  
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);  
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";  
  }  
}  

//图片预览通用方法
function previewImage(file,id){  
  var MAXWIDTH  = 150;  
  var MAXHEIGHT = 150;  
  var div = document.getElementById('preview'+id);  
  if (file.files && file.files[0]){  
    div.innerHTML = '<img id=imghead'+id+'>';  
    var img = document.getElementById('imghead'+id);  
    img.onload = function(){  
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
      img.width = rect.width;  
      img.height = rect.height;  
      img.style.marginLeft = rect.left+'px';  
      img.style.marginTop = rect.top+'px';  
    }  
    var reader = new FileReader();  
    reader.onload = function(evt){img.src = evt.target.result;}  
    reader.readAsDataURL(file.files[0]);  
  }else{  
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';file.select();  
    var src = document.selection.createRange().text;  
    div.innerHTML = '<img id=imghead'+id+'>';  
    var img = document.getElementById('imghead'+id);  
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;  
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);  
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";  
  }  
}  
function clacImgZoomParam( maxWidth, maxHeight, width, height ){  
    var param = {top:0, left:0, width:width, height:height};  
    if( width>maxWidth || height>maxHeight ){  
        rateWidth = width / maxWidth;  
        rateHeight = height / maxHeight;  
        if( rateWidth > rateHeight ){  
            param.width =  maxWidth;  
            param.height = Math.round(height / rateWidth);  
        }else{  
            param.width = Math.round(width / rateHeight);  
            param.height = maxHeight;  
        }  
    }  
    param.left = Math.round((maxWidth - param.width) / 2);  
    param.top = Math.round((maxHeight - param.height) / 2);  
    return param;  
}  
//统计模块，按年月日搜索通用方法
function setCurrentStatistics(i){
	$(".statistcsBtn").each(function(j,e){
		if(i==j){
			$(this).addClass("btn-success");
			$("."+$(this).attr("id")).show();
		}else{
			$(this).removeClass("btn-success");
			$("."+$(this).attr("id")).hide();
		}
	});
}
$(function(){
	$(".statistcsBtn").each(function(i,e){
		$(this).on("click",function(){
			setCurrentStatistics(i);
		});
	});
	
	$("#searchType").on("change",function(){
		var searchType = $(this).find("option:checked").val();
		$(".searchType").each(function(){
			if($(this).attr("id") == searchType){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
	});
});
//获取当前时间
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes();
    return currentdate;
}

function popImage(obj){
    var event = event || window.event;
    var target = event.target || event.srcElement;
    var link = target.src ? target.parentNode : targe;
    var options = {index: link, event: event};
    var links = obj.getElementsByTagName('a');
    blueimp.Gallery(links, options);
}
