<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/context.jsp"%>
<html>
<head>
    <title>更新相册</title>
</head>
<script type="text/javascript">

</script>
<body class="pace-done body-small">
    <div id="wrapper">
    	<%@include file="/WEB-INF/views/back/menu.jsp"%>
        <div id="page-wrapper" class="gray-bg" style="min-height: 420px;">
        <%@include file="/WEB-INF/views/back/head.jsp"%>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>更新相册</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${ctx}">首页</a>
                    </li>
                    <li class="active">
                        <strong>更新相册</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
                <div class="col-lg-12">
                	<button type="button" onclick="addApi()" class="btn btn-w-m btn-success">新增相册</button>
        		</div>
	        </div>
            <div class="row">
                <div class="col-lg-12">
                </div>
            </div>
        </div>
         <%@include file="/WEB-INF/views/back/foot.jsp"%>
      </div>
    </div>
</body>
</html>
