<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/context.jsp"%>
<html>
<head>
    <title>新增相册</title>
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
                <h2>新增相册</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${ctx}">首页</a>
                    </li>
                    <li class="active">
                        <strong>新增相册</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-8">
                       <form role="form" action="${ctx }/album/back/album/group/save" method="post" enctype="multipart/form-data">
                           <div class="form-group">
			                   <label class="col-sm-4 control-label" style="float: left;">相册名称: </label>
			                   <div class="col-sm-6">
			                   	<input type="text" name="name" placeholder="请输入相册名称" class="form-control">
			                   </div>
			               </div>
                           <div class="form-group">
			                   <label class="col-sm-4 control-label" style="float: left;">封面图: </label>
			                   <div class="col-sm-6">
			                   	<input class="form-control" type="file" name="profile" id="profile" />
			                   </div>
			               </div>
			               <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                          <button type="submit" class="btn btn-primary" style="width: 100px" id="adSubmit">确定</button>
                        </div>
                    </div>
                       </form>
                   </div>
            </div>
        </div>
         <%@include file="/WEB-INF/views/back/foot.jsp"%>
      </div>
    </div>
</body>
</html>
