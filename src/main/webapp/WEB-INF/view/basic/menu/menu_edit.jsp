<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
<form id="menu_edit_form" method="post" action='/menu/update'>
	<input type="hidden" name="oldParentId" value="${ menu.parentId }"></input>
	<table style="padding:0px;">
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单名称：</td>
			<td>
				<input type="hidden" name="menuId" value="${menu.menuId}"/>
				<input id="menu_edit_name" class="easyui-textbox" name="menuName" value="${menu.menuName}"
					style="width:200px;" data-options="required:true">
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">上级菜单：</td>
			<td>
				<input id="menu_edit_parent_name" value="${parent.menuName}" style="width:200px;">
				<input id="menu_edit_parent_id" type="hidden" name="parentId" value="${menu.parentId}"/>
				<input id="menu_edit_level" type="hidden" name="menuLevel" value="${menu.menuLevel}"/>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单URL：</td>
			<td>
				<input id="menu_edit_url" class="easyui-textbox" name="menuUrl" value="${menu.menuUrl}"
					style="width:200px;">
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">图标CLASS：</td>
			<td>
				<input id="menu_edit_icon" class="easyui-textbox" name="iconUrl" style="width:200px;" value="${menu.iconUrl}">
				<i id="menu_edit_iconview" class="fa ${menu.iconUrl}"></i>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单类型：</td>
			<td>
				<input type="radio" id="menu_edit_type_1" name="menuType" value="1">菜单</input>
				<input type="radio" id="menu_edit_type_2" name="menuType" value="2">按钮</input>
				<input type="hidden" id="menu_edit_hidden_type" value="${menu.menuType}"/>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="width:120px;text-align:right;">菜单排序：</td>
			<td>
				<input id="menu_edit_order" class="easyui-textbox" name="menuOrder" value="${menu.menuOrder}"
					style="width:200px;" data-options="validType:'number'">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
$(function() {
	$('#menu_edit_parent_name').combotree({
		url: '/menu/tree?menuType=1',
		editable: false,
		onClick: function(node){
			$('#menu_edit_parent_id').val(node.id);
			$('#menu_edit_level').val(node.level + 1);
		}
	});
	
	var menuType = $('#menu_edit_hidden_type').val();
	$("input[name=menuType][value=" + menuType + "]").attr("checked",true);
	
	$("#menu_edit_icon").textbox({
		"onChange" : function(newValue, oldValue){
			$("#menu_edit_iconview").removeClass().addClass("fa").addClass(newValue);
		}
	});
});
</script>
</body>
</html>
