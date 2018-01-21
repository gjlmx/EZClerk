<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<%@include file="common/common.jsp" %>
<html>
<head/>
<meta charset="UTF-8">
</head>
<div id="app">
    <el-button @click="visible = true">按钮</el-button>
    <el-dialog :visible.sync="visible" title="Hello world">
        <p>欢迎使用 Element</p>
    </el-dialog>
</div>
</body>
<!-- 先引入 Vue -->
<!-- 引入组件库 -->
<script>
    new Vue({
        el: '#app',
        data: function () {
            return {visible: false}
        }
    })
</script>
</html>
