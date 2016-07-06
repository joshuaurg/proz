<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");   
	response.setDateHeader("Expires",0);   
	response.setHeader("Pragma","No-cache");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath%>" />
<meta name="viewport" content="width=device-width, initial-scale = 1.0">
<script type="text/javascript" src="${ctx}/assets/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/json2.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/peity/jquery.peity.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/jqGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/inspinia.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/validate/validate.expand.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/jqueryPanigation/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/morris/raphael-2.1.0.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/morris/morris.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/plugins/dropzone/dropzone.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/common.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/ueditor/themes/default/css/ueditor.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/plugins/toastr/toastr.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" >
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/plugins/jqGrid/ui.jqgrid.css" >
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/animate.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/js/plugins/jqueryPanigation/css/pagination.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/plugins/morris/morris-0.4.3.min.css" >
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/plugins/sweetalert/sweetalert.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/plugins/blueimp/css/blueimp-gallery.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/plugins/dropzone/dropzone.css">
