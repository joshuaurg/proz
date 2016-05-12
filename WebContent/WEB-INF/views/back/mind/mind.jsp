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
			            <div class="ibox-content">
					         ${mind.content }  	
			          	</div>
            		</div> 
	      		</div>	
        	</div>
		</div>
</body>
</html>
