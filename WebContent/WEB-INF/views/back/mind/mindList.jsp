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
                	<button type="button" class="btn btn-w-m btn-success">生活咨询</button>
		             <button type="button" class="btn btn-w-m btn-success">生活常识</button>
		             <button type="button" class="btn btn-w-m btn-success">家居生活</button>
		             <button type="button" class="btn btn-w-m btn-success">健康养生</button>
		             <button type="button" class="btn btn-w-m btn-success">美食厨房</button>
	                 <a onclick="fnClickAddRow();" href="${ctx }/minds/addView" class="btn btn-primary ">新增奇思妙想</a>
        		</div>
	        </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table table-bordered">
						            <thead>
							            <tr>
								            <th>标题</th>
								            <th>创建时间</th>
								            <th>发布时间</th>
								            <th>是否发布</th>
								            <th>是否显示</th>
								            <th>所属分类</th>
								            <th class="center">操作</th>
							            </tr>
						            </thead>
						            <tbody>
						            	<c:forEach items="${mindList }" varStatus="status" var="mind">
						            		<tr>
								                <td>${mind.title }</td>
								                <td><fmt:formatDate value="${mind.creTime }"  type="both" /></td>
								                <td><fmt:formatDate value="${mind.pubTime }"  type="both" /></td>
								                <td>
								                	<c:if test="${mind.isPub ==1 }">发布</c:if>
								                	<c:if test="${mind.isPub ==0 }">暂存</c:if>
								                </td>
								                <td>
								                	<c:if test="${mind.isActive ==1 }">显示</c:if>
								                	<c:if test="${mind.isActive ==0}">隐藏</c:if>
								                </td>
								                <td>
								                	${mind.categoryName }
								                </td>
								                <td>
								                	<button type="button" onclick="viewMind('${mind.id}')" class="btn btn-w-m btn-success">查看</button>
								                	<button type="button" class="btn btn-w-m btn-danger">删除</button>
								                </td>
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
		function viewMind(id){
			window.location.href = "${ctx}/minds/viewMind?id="+id;
		}
	</script>
</body>
</html>
