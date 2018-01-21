<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<%
    String tPath = request.getContextPath();
    String tBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + tPath + "/";%>

<link href="<%=tBasePath%>css/eleui.css" rel="stylesheet">
<link href="<%=tBasePath%>css/bootstrap-theme.css" rel="stylesheet">
<script src="<%=tBasePath%>js/vue.js"></script>
<script src="<%=tBasePath%>js/eleui.js"></script>
<script src="<%=tBasePath%>js/bootstrap.js"></script>
<script src="//libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>