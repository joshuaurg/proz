<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/context.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>title</title>
    <style>
    	.ibox-title{
    		background-color: #808080;
   			color: #ffffff;
    	}
    	.ibox-content{
    		border: 1px solid #BABABA;
    	}
    	.list-group-item{
	    	padding-right: 0;
	    	padding-bottom: 0;
    	}
    	    
    </style>
</head>
<body class="pace-done body-small">
    <div id="wrapper">
    	<%@include file="/WEB-INF/views/back/menu.jsp"%>
        <div id="page-wrapper" class="gray-bg" style="min-height: 420px;">
        <%@include file="/WEB-INF/views/back/head.jsp"%>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>接口列表</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${ctx}">首页</a>
                    </li>
                    <li class="active">
                        <strong>接口列表</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
                <div class="col-lg-12">
                	<button type="button" onclick="addApi()" class="btn btn-w-m btn-success">新增接口</button>
        		</div>
	        </div>
            <div class="row">
                <div class="col-lg-12">
                	<c:forEach items="${apiCategoryList }" var="apiCategory">
                		<div class="ibox float-e-margins">
	                        <div class="ibox-title">
	                            <h5>接口分类：${apiCategory.name }</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content no-padding" style="display: block;">
	                        	<ul class="list-group">
		                        	<c:forEach items="${apiCategory.apiList }" var="api">
		                        		<li class="list-group-item"  style="overflow: hidden">
		                        			<div style="font-size: 20px;font-weight: bold;">${api.url }</div>
		                        			<div style="font-size: 15px;font-weight: bold;color: #9B9B9B;margin-top:10px;">名称：${api.name }</div>
		                                	<button type="button" style="float: right;margin-bottom: 0;border-radius: 0;" class="btn btn-w-m btn-success">${api.type }</button>
		                                </li>
		                        	</c:forEach>
	                            </ul>
	                        </div>
	                    </div>	
                	</c:forEach>
                </div>
                </div>
        </div>
         <%@include file="/WEB-INF/views/back/foot.jsp"%>
      </div>
    </div>
    <script type="text/javascript">
    	function addApi(){
    		window.location.href = "${ctx}/api/add";
    	}
    </script>
</body>
</html>
