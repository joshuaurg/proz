<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/context.jsp"%>
<html>
<head>
    <title>相册列表</title>
</head>
<body>
   	<%@ include file="/WEB-INF/views/back/menu.jsp"%>
    <div id="page-wrapper" class="gray-bg">
    <%@ include file="/WEB-INF/views/back/head.jsp"%>
        <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
                <div class="col-lg-12">
                	<button type="button" onclick="addPhotoAlbumGroup()" class="btn btn-success">添加相册</button>
        		</div>
	        </div>
            <div class="row">
                <div class="col-lg-12">
                	<div class="jqGrid_wrapper">
						<table id="table" class="table-striped"></table>
						<div id="pager"></div>
					</div>
                </div>
            </div>
        </div>
         <%@include file="/WEB-INF/views/back/foot.jsp"%>
      </div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#table').jqGrid({
			url : "${ctx}/album/back/album/group/list",
			datatype : "json",
			mtype : "GET",
			height: 600,
			autowidth: true,
			shrinkToFit: true,
			altRows:true,
            altclass:'jqGrid-row-color',
			rowNum : 10,
			rowList: [10, 15, 20],
			colNames: ['编号','标题','背景图','操作'],
			colModel: [
				{name: 'id', index: 'id', width: 80, align:"center"},
				{name: 'name', index: 'name', width: 80, align:"center"},
				{name: 'profile', index: 'profile', width: 80, align:"center",formatter:imageUrlFormatter},
				{name: 'operate', index: 'operate', width: 100, align:"center", sortable: false},
			],
			pager: "#pager",
            viewrecords: true,
            hidegrid: false,
            gridComplete:function(){
            	var ids = jQuery('#table').jqGrid('getDataIDs');
            	for (var i = 0; i<ids.length; i++){
            		var id = ids[i];
            		var rowdata=$('#table').getRowData(id);
            		var uploadBtn = "<a href='javascript:void(0)' style='color: #ffffff;' class='btn btn-primary' onclick='upload(\""+id+"\")'>上传</a>";
            		var updateBtn = "<a href='javascript:void(0)' style='color: #ffffff;' class='btn btn-primary' onclick='_edit(\""+id+"\")'>修改</a>";
            		var delBtn = "<a href='javascript:void(0)' style='color: #ffffff;' class='btn btn-danger' onclick='del(\""+id+"\")'>删除</a>";
            		jQuery('#table').jqGrid('setRowData', ids[i], {operate: uploadBtn +"  "+updateBtn +" " +delBtn});
            	}
            	$("#table").setGridWidth($(".jqGrid_wrapper").width());
            }
		});
	});
	function upload(id){
		window.location.href = "${ctx}/album/back/album/photo/upload/view/?groupid="+id;
	}
	function del(id){
		swal({
	        title: "操作提示",
	        text: "你确认删除吗?",
	        type: "warning", 
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        cancelButtonText: "取消",
	        confirmButtonText: "删除！",
	        closeOnConfirm: true
	    }, function () {
	    	var page = $('#table').jqGrid('getGridParam', 'page');
	    	var rows = $('#table').jqGrid('getGridParam', 'rowNum');
	    	$.ajax({
	    		type : "post",
	            url : "${ctx}/poem/back/del/"+id,
	            async : false,
	            dataType : "json",
	            success : function(data){
	            	if(data.ret == "succ"){
	            		$('#table').jqGrid('setGridParam', {
	    					datatype : 'json',
	    					postData : {
	    						page : page,
	    						rows : rows,
	    					},
	    					page : page ,
	    				}).trigger("reloadGrid");
	            	}else{
	            		swal("删除失败!", data.reason, "error");
	            	}
	            }
	    	});
	    });
	}
 
	function addPhotoAlbumGroup(){
		window.location.href = "${ctx}/album//back/album/group/addview";
	}
	function imageUrlFormatter(cellvalue, options, rowdata){
		var result = "<div onclick='popImage(this)' >";
		result += '<a href="'+rowdata.profile+'"><img class="alarmimg" width="80" height="80" src="'+rowdata.profile+'" /></a>';
	    result += "</div>";
	    return result;
	}
</script>

<%-- swal({   
	title: "请输入标签",   
	text: "这里可以输入并确认:",   
	type: "input",   
	showCancelButton: true,   
	closeOnConfirm: false,   
	animation: "slide-from-top",   
	inputPlaceholder: "标签内容" ,
	confirmButtonText: "确认"
}, function(inputValue){   
	if (inputValue === false) return false;      
	if (inputValue === "") {     
		swal.showInputError("请输入!");     
		return false   
	}
	$.ajax({
		type : "post",
		url : "${_base}/u/addUserTag",
		async : false,
		dataType : "json",
		data : {
			id : id,
			tag : inputValue,
		},
		success : function(data){
			if(data.status == "success"){
				
			}
		}
	});
	swal("OK!", "操作成功", "success"); 
}); --%>
</html>
