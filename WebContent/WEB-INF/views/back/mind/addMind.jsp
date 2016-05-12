<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/context.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>title</title>
</head>
<body>
    <div id="wrapper">
        <%@include file="/WEB-INF/views/back/menu.jsp"%>
        <div id="page-wrapper" class="gray-bg">
	        <%@include file="/WEB-INF/views/back/head.jsp"%>
      		 <div class="col-lg-12">
                 <div class="ibox float-e-margins">
                     <div class="ibox-title">
                         <h5>新增奇思妙想</h5>
                     </div>
                     <div class="ibox-content">
                         <form method="post" class="form-horizontal" action="${ctx }/minds/save">
                             <div class="form-group">
                             	<label class="col-sm-1 control-label">标题</label>
                                 <div class="col-sm-3"><input type="text" name="title" class="form-control"></div>
                             </div>
                             <div class="hr-line-dashed"></div>
                             <div class="form-group">
                             	<label class="col-sm-1 control-label">内容</label>
                                 <div class="col-sm-10">
                                  	<input type="hidden" name="content" class="form-control">
									<div id="content"></div>
                                 </div>
                             </div>
                             <div class="form-group">
                             	<label class="col-sm-10"></label>
                                 <div class="col-sm-2">
                                  	<button type="submit" class="btn btn-w-m btn-success">保存</button>
                                 </div>
                             </div>
                         </form>
                     </div>
                 </div>
      		</div>	
        </div>
	</div>
</body>
<script type="text/javascript">
   var ue = UE.getEditor("content", {
       wordCount:true,
       maximumWords:2000,
       autoHeightEnabled: true,
       //关闭elementPath
       elementPathEnabled:false,
       //默认的编辑区域高度
       initialFrameHeight:500
       //更多其他参数，请参考ueditor.config.js中的配置项
   });
</script>
</html>
