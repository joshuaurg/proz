<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/context.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>title</title>
</head>
<body class="pace-done body-small">
    <div id="wrapper">
    	<%@include file="/WEB-INF/views/back/menu.jsp"%>
        <div id="page-wrapper" class="gray-bg" style="min-height: 420px;">
        <%@include file="/WEB-INF/views/back/head.jsp"%>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>新增接口</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${ctx}">首页</a>
                    </li>
                    <li class="active">
                        <strong>新增接口</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form method="post" class="form-horizontal" action="${ctx }/api/save">
                                <div class="form-group"><label class="col-sm-1 control-label">接口名称</label>
                                    <div class="col-sm-5"><input type="text" name="name" class="form-control"></div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group"><label class="col-sm-1 control-label">接口类型</label>
                                    <div class="col-sm-5">
                                    	<select class="form-control" name="aid">
                                    		<c:forEach items="${cateList }" var="cate">
                                    			<option value="${cate.id }">${cate.name }</option>
                                    		</c:forEach>
                                    	</select>
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group"><label class="col-sm-1 control-label">接口地址</label>
                                    <div class="col-sm-5"><input type="text" name="url" class="form-control"> 
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group"><label class="col-sm-1 control-label">接口描述</label>
                                    <div class="col-sm-5"><input type="text" name="description" class="form-control"> 
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group"><label class="col-sm-1 control-label">接口备注</label>
                                    <div class="col-sm-5"><input type="text" name="remark" class="form-control"> 
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                	<label class="col-sm-1 control-label">请求类型</label>
                                    <div class="col-sm-5">
                                    	<select class="form-control" name="type">
                                    		<option value="post">POST</option>
                                    		<option value="get">GET</option>
                                    		<option value="put">PUT</option>
                                    		<option value="delete">DELETE</option>
                                    	</select>
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group" id="paramGroup" style="display: none">
                                	<div class="col-sm-11" id="paramTemplate">
                                		<label class="col-sm-1 control-label">参数列</label>
	                                    <div class="col-sm-2 control-label">
	                                    	<input type="text" placeholder="参数名称" name="paramName" class="form-control"/>
	                                    </div>
	                                    <div class="col-sm-2 control-label">
	                                    	<input type="text" placeholder="参数描述" name="paramDesc" class="form-control"/>
	                                    </div>
                                	</div>
                                </div>
                                <div class="hr-line-dashed" id="paramGroupLine"  style="display: none"></div>
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <button class="btn btn-white" type="button">取消</button>
                                        <button class="btn btn-primary" type="button" onclick="addParams()">添加请求参数</button>
                                        <button class="btn btn-primary" type="button">添加返回字段</button>
                                        <button class="btn btn-primary" type="submit">保存</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/WEB-INF/views/back/foot.jsp"%>
        </div>
        </div>
        <script type="text/javascript">
        	$(function(){
        		
        	});
        	function addParams(){
        		if($("#paramGroup").is(":visible")==true){
        			$("#paramGroup").append($("#paramTemplate").clone());
        		}else{
        			$("#paramGroup").show();
            		$("#paramGroupLine").show();
        		}
        	}
        </script>
</body>
</html>
