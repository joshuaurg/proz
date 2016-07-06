<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">当前用户：董晨旭</div>
                <div class="logo-element">BACK</div>
            </li>
            <li>
                <a href="#"><i class="fa fa-diamond"></i> <span class="nav-label">相册管理</span></a>
            	<ul class="nav nav-second-level collapse">
                    <li><a href="${ctx }/album/back/album/group/list/view">相册列表</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-diamond"></i> <span class="nav-label">诗词管理</span></a>
            	<ul class="nav nav-second-level collapse">
                    <li><a href="${ctx }/poem/back/view/list">诗词列表</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-diamond"></i> <span class="nav-label">诗歌管理</span></a>
            	<ul class="nav nav-second-level collapse">
            		<li><a href="${ctx }/belief/lordsong/back/album/list/view">专辑管理</a></li>
                    <li><a href="${ctx }/belief/lordsong/back/list">诗歌管理</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>