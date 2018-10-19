<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <body>
    <div class="easyui-panel" style="width:100%;height:100%;border:0">
		<div style="padding-left:40px">
	    <form id="role_edit_form" method="post" action='/role/role_edit_save'>
	    	<input type="hidden" name="roleId" value="${role.roleId}"/>
	    	<table cellpadding="10">
	    		<tr style="height:50px;">
	    			<td>角色名称：</td>
	    			<td><input class="easyui-textbox" type="text" name="roleName" value='${role.roleName }'  style='width:200px' data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="height:50px;">
	    			<td>角色说明：</td>
	    			<td><input class="easyui-textbox" type="text" name="roleType" value='${role.roleType }'  style='width:200px' data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="height:50px;">
					<td>是否可用：</td>
					<td >
					<input id="role_edit_delete_flag_update" type="hidden" name="delete_flag" value="${role.delete_flag}">
					<input id="role_edit_delete_flag" style='width:200px' value="${role.delete_flag}">
					</td>
			    </tr>
	    	</table>
	    </form>
	    </div>
	</div>
	 <script type="text/javascript">
	 $("#role_edit_delete_flag").combobox({
		editable: false,
		data: [
			{
				label: '可用',
				value: 0
			},
			{
				label: '不可用',
				value: 1
			}
		],
		panelHeight: 'auto',
		textField: 'label',
		valueField: 'value',
		onSelect:function(record){
			$("#role_edit_delete_flag_update").val(record.value);
		}
	});
	$(function() {
		$("#role_edit_type").children("option[value='"+$('#role_edit_type').attr("select_value")+"']").eq(0).attr('selected','selected');
	});
  	</script>
  </body>
</html>
