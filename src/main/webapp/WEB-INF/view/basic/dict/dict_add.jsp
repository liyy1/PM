<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
<form id="dict_add_form" method="post" action='/dict/add'>
	<table style="padding:25px;">
		<tr style="height:45px;">
			<td style="width:100px;text-align:right;">类型：</td>
			<td>
				<input id="dict_add_type" class="easyui-textbox" name="type" data-options="required:true" style="width:230px;" >
			</td>
		</tr>
		<tr style="height:45px;">
			<td style="width:100px;text-align:right;">键：</td>
			<td>
				<input id="dict_add_key" class="easyui-textbox" name="key" data-options="required:true" style="width:230px;" >
			</td>
		</tr>
		<tr style="height:45px;">
			<td style="width:100px;text-align:right;">值：</td>
			<td>
				<input id="dict_add_value" class="easyui-textbox" name="value" data-options="required:true" style="width:230px;" >
			</td>
		</tr>
		<tr style="height:70px;">
			<td style="width:100px;text-align:right;">备注：</td>
			<td>
				<input class="easyui-textbox" type="text" name="memo"   data-options="multiline:true,width:'230px',height:'60px'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
