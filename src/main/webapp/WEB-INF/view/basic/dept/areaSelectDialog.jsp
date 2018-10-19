<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div id="area_select_list"></div>
<script>
$(function () {
	$('#area_select_list').treegrid({
	    url: '/dept/getAreaTreeData',
	    fitColumns: true,
	    nowrap: true,
	    treeField:"areaname",
	    //height: $(window).height()-128,
	    fit: true,
	    collapsible: true,
	    // pagination: true,
	    autoRowHeight: false,
	    idField: 'id',
	    striped: true, //奇偶行是否区分
	    singleSelect: false,//单选模式
	    rownumbers: true,//行号
	    remoteSort: false,
	   // pageSize: 10, //每一页多少条数据
	   // pageList: [10, 20, 30, 40, 50],  //可以选择的每页的大小的combobox
	    columns: [[
		    { field: 'ck', checkbox: true },
		    { field: 'id', title: '区划编码', width: 100 },
		    { field: 'areaname', title: '区划名称', width: 100 },
		    { field: 'level', title: '级别', width: 100,hidden:true}
	    ]]
	    /* onLoadSuccess:function(row,data){           	
	        var selectedId = $("#dept_edit_parentId").val();
	        if(selectedId)
	          $('#area_select_list').treegrid('select',selectedId);
	    } */
	});
});
</script>
</body>
</html>
