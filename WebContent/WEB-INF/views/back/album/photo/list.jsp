<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/context.jsp"%>
<html>
<head>
    <title>照片列表</title>
</head>
<style>
        /* Local style for demo purpose */

        .lightBoxGallery {
            text-align: center;
        }

        .lightBoxGallery img {
            margin: 5px;
        }

    </style>
<script type="text/javascript">
	$(function(){
		
	});
</script>
<body class="pace-done body-small">
    <div id="wrapper">
    	<%@include file="/WEB-INF/views/back/menu.jsp"%>
        <div id="page-wrapper" class="gray-bg" style="min-height: 420px;">
        <%@include file="/WEB-INF/views/back/head.jsp"%>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>照片列表</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${ctx}">首页</a>
                    </li>
                    <li class="active">
                        <strong>照片列表</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox float-e-margins">
                	<div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="lightBoxGallery">
                        	<c:forEach items="${photoList }" var="photo">
                        		<a href="${photo.url }" title="Image from Unsplash" data-gallery=""><img src="${photo.url }" width="100" height="100"></a>
                        	</c:forEach>
                            <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" style="display: none;">
                                <div class="slides" style="width: 138240px;"></div>
                                <a class="prev">‹</a>
                                <a class="next">›</a>
                                <a class="close">×</a>
                                <a class="play-pause"></a>
                                <ol class="indicator"></ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox-content">
                        <form id="my-awesome-dropzone" class="dropzone" method="post" action="${ctx }/album/back/album/photo/upload">
                        	<input type="hidden" name="groupId" value="${groupid }" />
                            <div class="dropzone-previews"></div>
                            <button type="submit" class="btn btn-primary pull-right">Submit this form!</button>
                        </form>
                    </div>
            	</div>
            </div>
        </div>
         <%@include file="/WEB-INF/views/back/foot.jsp"%>
      </div>
    </div>
</body>
</html>
