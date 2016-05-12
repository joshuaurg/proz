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
                <h2>接口分类列表</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${ctx}">首页</a>
                    </li>
                    <li class="active">
                        <strong>接口分类列表</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
                <div class="col-lg-12">
                	<button type="button" onclick="addApiCate()" class="btn btn-w-m btn-success">新增分类</button>
        		</div>
	        </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>名称</th>
                                <th>描述</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${cateList }" var="cate"> 
                            		<tr>
		                                <td>${cate.id }</td>
		                                <td>${cate.name }</td>
		                                <td>${cate.description }</td>
		                                <td><button type="button" class="btn btn-w-m btn-danger">删除</button></td>
		                            </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>	
                </div>
            </div>
        </div>
         <%@include file="/WEB-INF/views/back/foot.jsp"%>
      </div>
    </div>
    <script type="text/javascript">
    	function addApiCate(){
    		window.location.href = "${ctx}/api/category/add";
    	}
    </script>
</body>
</html>
