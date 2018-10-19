<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <body>
    <div class="easyui-panel" style="width:100%;height:100%;padding-top:20px;border:0">
	    <form id="role_add_form" method="post" action='/role/role_add_save'>
	    	<table cellpadding="10">
	    		<tr style="height:50px;">
	    			<td>角色名称：</td>
	    			<td><input class="easyui-textbox" type="text" name="roleName" style='width:200px' data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="height:50px;">
	    			<td>角色说明：</td>
	    			<td><input class="easyui-textbox" type="text" name="roleType" style='width:200px' data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
  </body>
</html>
