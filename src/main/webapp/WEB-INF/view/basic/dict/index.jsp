<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
<div class="easyui-panel" style="padding:15px;" data-options="fit:true,border:false">
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',border:false" style="height: 35px;margin-bottom: 10px;">
			<div class="group-label">
				<input class="easyui-textbox theme-textbox-radius" label="类型：" name="type" style="width:200px;">
				<input class="easyui-textbox theme-textbox-radius" label="KEY：" name="key" style="width:200px;">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="resetForm('dict_search_form');">重置</a>
				<a href="#" class="easyui-linkbutton button-info" data-options="iconCls:'icon-search'"
				   onclick="search_load('dict_list', 'dict_search_form', 'datagrid')">查询</a>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div id="dict_list"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	$('#dict_list').datagrid({
        cls: 'theme-border-radius',
		url: '/dict/query',
		fit: true,
		fitColumns: true,
        singleSelect: true,// 单选模式
        pagination: true,
        pageSize: 10, // 每一页多少条数据
        idField: 'id',
        sortName: 'createTime',
		sortOrder:'desc',
		pageList: [ 10, 20, 30, 50, 100 ], // 可以选择的每页的大小的combobox
	    columns:[[
			{field: 'ck', checkbox: true },
			{field:'type', title:'类型',width: 100},
			{field:'key', title:'键',width: 100},
			{field:'value', title:'值',width: 100},
			{field:'memo', title:'说明',width: 100},
			{field:'delete_flag',title: '是否可用',sortable: true,width: 80,formatter: deleteFormatter}
	    ]],
	    toolbar: [{
			url: 'dict/add',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				openDictAddDialog();
			}
		},{
			url: 'dict/edit',
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var row = $('#dict_list').datagrid('getSelected');
                if (!row) {
                    $.messager.alert("温馨提示", "请选择一行！");
                } else {
                    openDictEditDialog(row.id);
                }
			}
		}]
	});
	actionButtonCtr('dict_list');
});

function openDictAddDialog() {
	var $dialog = $("<div id='dict_add_dialog'></div>");
	$dialog.dialog({
        href: '/dict/insert',
        title: '新增数据字典',
        width: 450,
        height: 360,
        closed: true,
        cache: false,
        modal: true,
        buttons: [{
            text: '确定',
            iconCls:'icon-ok',
            handler: function() {
            	submitForm('dict_add_form', 'dict_add_dialog', 'dict_list', 'dict_search_form', this);
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

function openDictEditDialog(id) {
	var $dialog = $("<div id='dict_edit_dialog'></div>");
	$dialog.dialog({
        href: '/dict/update?id=' + id,
        title: '编辑数据字典',
        width: 450,
        height: 400,
        closed: true,
        cache: false,
        modal: true,
        buttons: [{
            text: '确定',
            iconCls:'icon-ok',
            handler: function() {
            	submitForm('dict_edit_form', 'dict_edit_dialog', 'dict_list', 'dict_search_form', this);
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
