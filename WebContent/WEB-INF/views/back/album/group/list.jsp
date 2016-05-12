<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/context.jsp"%>
<html>
<head>
    <title>相册管理</title>
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

<script type="text/javascript">

$(function(){
	$("#Pagination").pagination(
		${result.totalRecord},
		{
	        items_per_page : ${result.pageSize},
	        current_page:${result.currentPage}-1,
	        num_display_entries:5, // 分页显示的条目数
	        next_text:"下一页",
	        prev_text:"上一页",
	        num_edge_entries:2 // 连接分页主体，显示的条目数
		}
	);
	$(".pagination > a").each(function(){
		$(this).on("click",function(){
			var current = $(".pagination > .current").text();
			var page = $(this).text();
			if($(this).hasClass("prev") || $(this).hasClass("next")){
				page = parseInt(current);
				if(isNaN(page)){
					page = 1;
				}
			}
			$("#stuForm").attr("action", "${ctx}/album/back/album/group/list?pageNum="+page);
			$("#stuForm").submit();
		});
	});
});
function addAlbumGroup(){
	window.location.href = "${ctx}/album/back/album/group/addview";
}
function viewAlbumGroup(groupId){
	window.location.href = "${ctx}/album/back/album/photo/list?groupid="+groupId;
}
</script>
<body class="pace-done body-small">
    <div id="wrapper">
    	<%@include file="/WEB-INF/views/back/menu.jsp"%>
        <div id="page-wrapper" class="gray-bg" style="min-height: 420px;">
        <%@include file="/WEB-INF/views/back/head.jsp"%>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>相册管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${ctx}">首页</a>
                    </li>
                    <li class="active">
                        <strong>相册管理</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
                <div class="col-lg-12">
                	<button type="button" onclick="addAlbumGroup()" class="btn btn-w-m btn-success">新增相册</button>
        		</div>
	        </div>
            <div class="row">
                <div class="col-lg-12">
	                <form id="stuForm" method="post">
	                </form>
	                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>相册名称</th>
                                    <th>创建时间</th>
                                    <th>显示状态</th>
                                    <th>封面图</th>
                                    <th>相册描述</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${result.dataList }" var="albumGroup">
									<tr>
										<td>${albumGroup.id }</td>
										<td>${albumGroup.name }</td>
										<td>${albumGroup.creTime }</td>
										<td>${albumGroup.delFlag }</td>
										<td>${albumGroup.profile }</td>
										<td>${albumGroup.remark }</td>
										<td>
											<button type="button" onclick="viewAlbumGroup('${albumGroup.id }')" class="btn btn-w-m btn-success">查看相册</button>
										</td>
									</tr>
								</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                </div>
                <div class="col-lg-12"><div id="Pagination" class="pagination"></div></div>
            </div>
        </div>
         <%@include file="/WEB-INF/views/back/foot.jsp"%>
      </div>
    </div>
</body>
</html>
