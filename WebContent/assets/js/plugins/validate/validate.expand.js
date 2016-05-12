	function getRootPath(){
		var curWwwPath=window.document.location.href;
		var pathName=window.document.location.pathname;
		var pos=curWwwPath.indexOf(pathName);
		var localhostPaht=curWwwPath.substring(0,pos);
		var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		return(localhostPaht+projectName);
	}
	/**
	 * 自动以校验方法
	 */
	//添加广告图-跳转地址特殊处理-是否必填
	jQuery.validator.addMethod("isRedirectAddrRequire", function(value, element) {   
	    var option =  $("#typeSelect").find("option:selected").val();
	    if(option==0){
	    	return true;
	    }else if(value == null || value == ''){
	    	return false;
	    }else
	    	return true;
	}, "请填写跳转地址");

	jQuery.validator.addMethod("checkRedirectAddr", function(value, element) { 
		var type = document.all.typeSelect.value
	    var redirectaddr = $('#redirectaddr').val();
		var result = true;
		$.ajax({
	          type : "post",
	          url : getRootPath()+"/more/vifyAd",
	          async : false,
	          dataType : "json",
	          data : {
	              type : type,
	              redirectaddr : redirectaddr,
	          },
	          success : function(data){
	              if(data.status == "error"){
	            	  result = false;
	              }else{
	            	  result = true;
	              }
	          }
	    });
		return result;
	}, "跳转地址不合法");
	
	//正整数校验
	jQuery.validator.addMethod("isPositiveInteger", function(value, element) { 
		var result = true;
		var type = /^[0-9]*[1-9][0-9]*$/;
        var reg = new RegExp(type);
        if (value.match(reg) == null) {
            return false;
        }else{
        	return true;
        }
	}, "请输入正整数");
