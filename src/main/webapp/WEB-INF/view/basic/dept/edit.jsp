<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<form id="dept_edit_form" method="post" action='/dept/update'>
	<table style="padding-top:20px;padding-left: 50px;">
			<tr style="height:45px;">
			<td style="width:70px;text-align:right;">部门名称：</td>
			<td>
				<input type="hidden" name="id" value="${dept.id}"/>
				<input id="dept_edit_name" class="easyui-textbox" name="name" value="${dept.name}"  style="width:240px;" data-options="required:true">
			</td>
		</tr>
	 	<tr style="height:45px;">
			<td style="width:70px;text-align:right;">上级部门：</td>
			<td>
				<input id="dept_edit_parent_name" value="${parent.name}" style="width:240px;">
				<input id="dept_edit_parent_id" type="hidden" name="parentId" value="${dept.parentId}"/>
				<input id="dept_edit_level" type="hidden" name="deptLevel" value="${dept.deptLevel}"/>
			</td>
		</tr> 
		<tr style="height:90px;">
			<td style="width:70px;text-align:right;">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
			<td>
				<input class="easyui-textbox" data-options="multiline:true" id="dept_edit_memo" name="memo" value="${dept.memo}"  style="width:240px;height:80px"></textarea>
			</td>
		</tr>
		<tr style="height:45px;">
			<td style="width:70px;text-align:right;">是否可用：</td>
			<td>
			 	<select class="easyui-combobox" style="width: 240px;" id="dept_edit_delete_flag" name="delete_flag" select_value='${dept.delete_flag}' data-options="panelHeight:'auto'">
       				<option value="0">可用</option>
					<option value="1">不可用</option>
	  			</select>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
 $(function() {
	$('#dept_edit_parent_name').combotree({
		url: '/dept/tree?deptType=1',
		editable: false,
		onClick: function(node){
			$('#dept_edit_parent_id').val(node.id);
			$('#dept_edit_level').val(node.level + 1);
		}
	});
	$('#dept_edit_delete_flag').combobox({
		data: [{value: 0,text: '可用'	}, {value: 1,text: '不可用'}],
		valueField: 'value',
		textField: 'text', 
		editable: false,
		panelHeight: 'auto'
	});
});
</script>
</body>
</html>
