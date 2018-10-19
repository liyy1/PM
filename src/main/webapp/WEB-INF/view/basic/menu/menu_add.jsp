<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<form id="menu_add_form" method="post" action='/menu/insert'>
	<table style="padding:0px;">
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单名称：</td>
			<td>
				<input id="menu_add_name" class="easyui-textbox" name="menuName" style="width:200px;" data-options="required:true">
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">上级菜单：</td>
			<td>
				<input id="menu_add_parent_name" value="${parent.menuName}" style="width:200px;">
				<input id="menu_add_parent_id" type="hidden" name="parentId" value="${parent.menuId}"/>
				<input id="menu_add_level" type="hidden" name="menuLevel" value="${parent.menuLevel + 1}"/>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单URL：</td>
			<td>
				<input id="menu_add_url" class="easyui-textbox" name="menuUrl" style="width:200px;">
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">图标CLASS：</td>
			<td>
				<input id="menu_add_icon" class="easyui-textbox" name="iconUrl" style="width:200px;">
				<i id="menu_add_iconview"></i>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单类型：</td>
			<td>
				<input type="radio" id="menu_add_type_1" name="menuType" value="1">菜单</input>
				<input type="radio" id="menu_add_type_2" name="menuType" value="2">按钮</input>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单排序：</td>
			<td>
				<input id="menu_add_order" class="easyui-textbox" name="menuOrder" value="1"
					style="width:200px;" data-options="validType:'number'">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
$(function() {
	$('#menu_add_parent_name').combotree({
		url: '/menu/tree?menuType=1',
		editable: false,
		onClick: function(node){
			$('#menu_add_parent_id').val(node.id);
			$('#menu_add_level').val(node.level + 1);
		}
	});
	$("#menu_add_icon").textbox({
		"onChange" : function(newValue, oldValue){
			$("#menu_add_iconview").removeClass().addClass("fa").addClass(newValue);
		}
	});
});
</script>
</body>
</html>
