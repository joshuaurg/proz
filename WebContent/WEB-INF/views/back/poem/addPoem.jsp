<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/context.jsp"%>
<html>
<head>
    <title>诗词列表</title>
</head>
<body>
   	<%@ include file="/WEB-INF/views/back/menu.jsp"%>
    <div id="page-wrapper" class="gray-bg">
    <%@ include file="/WEB-INF/views/back/head.jsp"%>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                	<form class="form-horizontal" action="${ctx}/poem/back/add" method="post" id="addPoem" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">作者: </label>
                        <div class="col-sm-3">
	                        <input class="form-control" type="text" id="author" name="author">
	                   	</div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">标题: </label>
                        <div class="col-sm-3">
	                       <input class="form-control" type="text" id="title" name="title">  
	                   	</div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="float: left;">封面图: </label>
                        <div class="col-sm-3">
                        	<input class="form-control" type="file" name="profileImg" id="profileImg" accept="image/*" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容: </label>
                        <div class="col-sm-3">
                        	<textarea rows="10" cols="50" id="content" name="content"></textarea>
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
</body>
<script type="text/javascript">
	
</script>
</html>
