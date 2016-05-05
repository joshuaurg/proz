<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">当前用户：董晨旭</div>
                <div class="logo-element">BACK</div>
            </li>
            <li>
                <a href="${ctx }/minds/list"><i class="fa fa-th-large"></i> <span class="nav-label">奇思妙想</span> </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-diamond"></i> <span class="nav-label">接口管理</span></a>
            	<ul class="nav nav-second-level collapse">
                    <li><a href="${ctx }/api/list">接口列表</a></li>
                    <li><a href="${ctx }/api/category/list">接口分类</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>