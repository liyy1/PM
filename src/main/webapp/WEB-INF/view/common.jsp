<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/my/img/domor.ico" />

<link href="/my/js/easyui/insdep.easyui.min.css" rel="stylesheet" type="text/css">
<link href="/my/js/easyui/icon.css" rel="stylesheet" type="text/css">
<link href="/my/js/layui/css/layui.css" rel="stylesheet" type="text/css">
<link href="/my/css/admin.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=basePath %>my/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>my/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>my/js/easyui/insdep.extend.min.js"></script>
<script type="text/javascript" src="<%=basePath %>my/js/layui/layui.all.js"></script>
<script type="text/javascript" src="<%=basePath %>my/js/my/admin.js"></script>
<script>
</script>