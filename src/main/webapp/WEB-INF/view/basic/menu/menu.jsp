<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
<div class="easyui-panel" style="padding:15px;" data-options="fit:true,border:false">
	<div id="menu_list"></div>
</div>
<script type="text/javascript">
$(function() {
	$('#menu_list').treegrid({
		cls: 'theme-border-radius',
	    url: '/menu/query',
	    idField: 'menuId',
	    treeField: 'menuName',
	    singleSelect: true,
	    fit: true,
	    fitColumns: true,
	    columns:[[
			{field: 'ck', checkbox: true },
			{title:'名称', field:'menuName', width:240},
			{title:'URL', field:'menuUrl', width:180},
			{title: '图标', field: 'iconUrl',width: 60,
				formatter: function(value, row) {
					return '<i class="fa '+ value +'"></i>';
				}
			},
			{title:'层级', field:'menuLevel', width:80},
			{title: '是否为叶子结点',field: 'isLeaf', width: 120,
				formatter: function(value, row) {
					if (value == 0) return '否';
					if (value == 1) return '是';
				}
			},
			{title: '类型',field: 'menuType',width: 80,
				formatter: function(value, row) {
					if (value == 1) return '菜单';
					if (value == 2) return '按钮';
				}
			},
			{title:'排序',field:'menuOrder',width:80},
			{title: '是否可用',field: 'delete_flag',width: 80,formatter: deleteFormatter}
	    ]],
	    toolbar : [{
	    	url: 'menu/add',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				var row = $('#menu_list').datagrid('getSelected');
				var parentId = row == null ? 0 : row.menuId;
				var menuType = row == null ? 1 : row.menuType;
				if(menuType == 2) {
					$.messager.alert("温馨提示", "选中菜单的类型为“按钮”，不允许添加子节点！");
				} else {
					openMenuAddDialog(parentId);
				}
			}
		},{
			url: 'menu/edit',
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var row = $('#menu_list').datagrid('getSelected');
				if (!row) {
					$.messager.alert("温馨提示", "请先选择一行！");
				}else{
					openMenuEditDialog(row.menuId);
				}
			}
		},{
			url: 'menu/del',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var row = $('#menu_list').datagrid('getSelected');
				if (!row) {
					$.messager.alert("温馨提示", "请先选择一行！");
				}else {
					$.messager.confirm('温馨提示', '删除操作不可恢复，且删除后，菜单下的所有子节点都将被删除。您确定要删除该菜单吗？', function(r) {
						if (r) {
							$.get("/menu/delete?menuId=" + row.menuId, {}, function(data) {
							if (data.status) {
								$.messager.alert("温馨提示", "删除成功！");
								search_load('menu_list', 'menu_search_form', 'treegrid');
							} else
								$.messager.alert("温馨提示", data.message);
							});
						}
					});
				}
			}
		}]
	});
	actionButtonCtr('menu_list');
});

function openMenuAddDialog(parentId) {
	var $dialog = $("<div id='menu_add_dialog'  style='padding-top:30px;padding-left:30px;'></div>");
	$dialog.dialog({
        href: '/menu/insert?parentId=' + parentId,
        title: '新增菜单',
        width: 470,
        height: 400,
        closed: true,
        cache: false,
        modal: true,
        buttons: [{
            text: '确定',
            iconCls:'icon-ok',
            handler: function() {
            	submitForm('menu_add_form','menu_add_dialog', 'menu_list',"",this,'treegrid');
            }
        },{
            text: '取消',
            iconCls:'icon-cancel',
            handler: function() {
                $dialog.dialog('close');
            }
        }],
        onClose: function() {
            $dialog.dialog('destroy');
        }
    });
    $dialog.dialog('open');
}

function openMenuEditDialog(menuId) {
	var $dialog = $("<div id='menu_edit_dialog' style='padding-top:30px;padding-left:30px;'></div>");
	$dialog.dialog({
        href: '/menu/update?menuId=' + menuId,
        title: '编辑菜单',
        width: 470,
        height: 400,
        closed: true,
        cache: false,
        modal: true,
        buttons: [{
            text: '确定',
            iconCls:'icon-ok',
            handler: function() {
            	submitForm('menu_edit_form','menu_edit_dialog', 'menu_list',"",this,'treegrid');
            }
        },{
            text: '取消',
            iconCls:'icon-cancel',
            handler: function() {
                $dialog.dialog('close');
            }
        }],
        onClose: function() {
            $dialog.dialog('destroy');
        }
    });
    $dialog.dialog('open');
}
</script>
</body>
</html>
