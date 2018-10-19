<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file = "common.jsp" %>
</head>
<body>

<div class="layui-fluid">
    <div class="layadmin-tips">
        <i class="layui-icon" face>&#xe664;</i>
        <div class="layui-text" style="font-size: 20px;">
            您没有权限访问该页面
        </div>
    </div>
</div>

<script>
    layui.use(['index']);
</script>
</body>
</html>
