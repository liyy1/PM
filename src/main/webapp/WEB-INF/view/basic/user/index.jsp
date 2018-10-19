<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file = "../../common.jsp" %>
</head>
<body onload="frameResize()" onresize="frameResize()">
<div class="frame-content layui-card layui-row">
	<div class="layui-col-md3" style="height: 100%;background-color: #fafafa;padding:15px;">
		<div id="dept"></div>
	</div>
	<div class="layui-col-md9" >
		<div class="layui-form layui-card-header layuiadmin-card-header-customer layui-form-pane">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">行内表单</label>
					<div class="layui-input-inline">
						<input type="text" name="number" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn layui-btn-primary layuiadmin-btn-list" lay-submit lay-filter="LAY-app-contlist-search">
						<i class="layui-icon">&#xe615;</i> 查询
					</button>
				</div>
			</div>
		</div>
		<div class="layui-card-body">
			<div style="padding-bottom: 10px;">
				<div class="layui-btn-group">
					<button class="layui-btn layuiadmin-btn-list" data-type="add">增加</button>
					<button class="layui-btn layuiadmin-btn-list" data-type="edit">编辑</button>
					<button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
				</div>
			</div>
			<table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
			<script type="text/html" id="buttonTpl">
				{{#  if(d.status){ }}
				<button class="layui-btn layui-btn-xs">已发布</button>
				{{#  } else { }}
				<button class="layui-btn layui-btn-primary layui-btn-xs">待修改</button>
				{{#  } }}
			</script>
			<script type="text/html" id="table-content-list">
				<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
			</script>
		</div>
	</div>
</div>

<script>
    layui.use(['index', 'contlist', 'table','atree'], function(){
        var table = layui.table,
			form = layui.form,
			admin = layui.admin,
    		$ = layui.$;

        var tree = layui.atree({
            elem: '#dept' //指定元素
            ,
            check: 'checkbox' //勾选风格
            ,
            skin: 'as' //设定皮肤
            //,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
            ,
//              drag: true,
            spreadAll:true,
            props:{
                addBtnLabel:'新增',
                deleteBtnLabel:'删除',
                name: 'name',
                id: 'id',
                children:'children',
                checkbox:'checkbox',
                spread:'spread'
            },
            change:function(val){
                console.group('change event')
                console.log('Array')
                console.log(val);
                console.groupEnd()
            },
            click: function(item) { //点击节点回调
                console.group('click event')
                console.log('Object')
                console.log(item);
                console.groupEnd()
            },
            addClick:function(item,elem,add){
                console.group('append event')
                console.log('Object')
                console.log(item);
                console.log('dom')
                console.log(elem);
                console.log('dom add event')
                var item ={
                    name: '测试节点'+new Date().getTime(),
                    id:-1
                }
                add(item)
                console.groupEnd()
            },
            deleteClick:function(item,elem,done){
                console.group('delete event')
                console.log('Object')
                console.log(item);
                console.log('dom')
                console.log(elem);
                console.log('dom delete event')
                done();
                console.groupEnd()
            },
            nodes: [ //节点
                {
                    name: '常用文件夹',
                    id: 1,
                    alias: 'changyong',
                    children: [{
                        name: '所有未读',
                        id: 11
                        //,href: 'http://www.layui.com/'
                        ,
                        alias: 'weidu'
                    }, {
                        name: '置顶邮件',
                        id: 12
                    }, {
                        name: '标签邮件',
                        id: 13
                    }]
                }, {
                    name: '我的邮箱',
                    id: 2,
                    spread: true,
                    children: [{
                        name: 'QQ邮箱',
                        id: 21,
                        spread: true,
                        children: [{
                            name: '收件箱',
                            id: 211,
                            spread: true,
                            children: [{
                                name: '所有未读',
                                checkbox:true,
                                id: 2111
                            }, {
                                name: '置顶邮件',
                                id: 2112
                            }, {
                                name: '标签邮件',
                                id: 2113
                            }]
                        }, {
                            name: '已发出的邮件',
                            id: 212
                        }, {
                            name: '垃圾邮件',
                            id: 213
                        }]
                    }, {
                        name: '阿里云邮',
                        id: 22,
                        children: [{
                            name: '收件箱',
                            id: 221
                        }, {
                            name: '已发出的邮件',
                            id: 222
                        }, {
                            name: '垃圾邮件',
                            id: 223
                        }]
                    }]
                }
            ]
        });

        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function(data){
            var field = data.field;
            table.reload('LAY-app-content-list', {
                where: field
            });
        });

        var active = {
            batchdel: function(){
                var checkStatus = table.checkStatus('LAY-app-content-list')
                    ,checkData = checkStatus.data; //得到选中的数据

                if(checkData.length === 0){
                    return layer.msg('请选择数据');
                }

                layer.confirm('确定删除吗？', function(index) {

                    //执行 Ajax 后重载
                    /*
                    admin.req({
                      url: 'xxx'
                      //,……
                    });
                    */
                    table.reload('LAY-app-content-list');
                    layer.msg('已删除');
                });
            },
            add: function(){
                admin.popupR({url:"/user/insert"});
            },
            edit: function(){
                admin.popupR({url:"/user/update?username="+'admin'});
            }
        };

        $('.layui-btn.layuiadmin-btn-list').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>

