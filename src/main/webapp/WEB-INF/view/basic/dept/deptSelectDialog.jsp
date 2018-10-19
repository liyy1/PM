<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<form id="dept_search_search_form" action="" method="post">
<input type="hidden" id="dept_search_deptLevel" value="${deptLevel}">
<table  style="table-layout: fixed;margin-top:10px;margin-left:30px;font-size:14px;">
    <tr>
        <td>部门名称：</td>
        <td>
            <input class="easyui-textbox" type="text"  id="SysDept_name" name="key11"/>
        </td>
        <td>
            <a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="search_load('dept_select_list', 'dept_search_search_form','treegrid')">查询</a>
        </td>
    </tr>
</table>
</form>
<div id="dept_select_list"></div>
<script>
$(function () {
var url = '/dept/query';
var deptLevel = parseInt($('#dept_search_deptLevel').val());
if (deptLevel > 0) {
	url += '?deptLevel=' + deptLevel;
}
$('#dept_select_list').treegrid({
    url: url,
    fitColumns: true,
    nowrap: true,
    treeField:"name",
    //height: $(window).height()-128,
    fit: true,
    collapsible: true,
    pagination: true,
    autoRowHeight: false,
    idField: 'id',
    striped: true, //奇偶行是否区分
    singleSelect: false,//单选模式
    rownumbers: true,//行号
    remoteSort: false,
    pageSize: 10, //每一页多少条数据
    pageList: [10, 20, 30, 40, 50],  //可以选择的每页的大小的combobox
    columns: [[
	    { field: 'ck', checkbox: true },
	    { field: 'id', title: '部门编码', width: 100 },
	    { field: 'name', title: '部门名称', width: 100 },
	    { field: 'deptLevel', title: '级别', width: 100,hidden:true},
	    {
	        field: 'delete_flag',
	        title: '是否可用',
	        sortable: true,
	        width: 100,
	        formatter: function (value, row) {
	            if (value == false) return '<span style=color:green; >是</span>';
	            if (value == true) return '<span style=color:red; >否</span>';
	        }    
	    }
    ]]
    /* onLoadSuccess:function(row,data){           	
        var selectedId = $("#dept_edit_parentId").val();
        if(selectedId)
          $('#dept_select_list').treegrid('select',selectedId);
    } */
});
});
</script>
</body>
</html>
