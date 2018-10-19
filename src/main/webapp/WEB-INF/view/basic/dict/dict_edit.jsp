<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
<form id="dict_edit_form" method="post" action='/dict/edit'>
	<table style="padding:20px;">
		<tr style="height:45px;">
			<td style="width:120px;text-align:right;">类型：</td>
			<td>
				<input id="dict_edit_type" class="easyui-textbox" name="type" 
					data-options="required:true" style="width:210px;" value="${dict.type}">
					<input name="id" value="${dict.id}" type="hidden">
			</td>
		</tr>
		<tr style="height:45px;">
			<td style="width:120px;text-align:right;">键：</td>
			<td>
				<input id="dict_edit_key" class="easyui-textbox" name="key" 
					data-options="required:true" style="width:210px;" value="${dict.key}">
			</td>
		</tr>
		<tr style="height:45px;">
			<td style="width:120px;text-align:right;">值：</td>
			<td>
				<input id="dict_edit_value" class="easyui-textbox" name="value" 
					data-options="required:true" style="width:210px;" value="${dict.value}">
			</td>
		</tr>
		<tr style="height:45px;">
			<td style="width:120px;text-align:right;">备注：</td>
			<td>
				<input class="easyui-textbox" type="text" name="memo"   data-options="multiline:true,width:'210px',height:'60px'" value="${dict.memo}">
			</td>
		</tr>
		<tr style="height:45px;">
			<td style="width:120px;text-align:right;">是否可用：</td>
			<td>
				<input id="dict_edit_delete_flag" name="delete_flag" value="${dict.delete_flag}" style="width:210px;">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$('#dict_edit_delete_flag').combobox({
        data: [{value: 0,text: '可用'}, {value: 1,text: '不可用'}],
        valueField: 'value',
        textField: 'text',
        editable: false,
        panelHeight: 'auto'
    });
</script>
</body>
</html>
