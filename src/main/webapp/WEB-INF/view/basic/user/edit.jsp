<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
	<form class="layui-form dialog-form" action="" >
		<div class="layui-card-header">
			<i class="layui-icon">&#xe63c;</i> 用户信息
		</div>
		<div class="layui-row">
			<div class="layui-col-md12">
				<span>用户名</span>
				<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md6">
				<span>姓名</span>
				<input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-col-md6">
				<span>手机</span>
				<input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input" readonly>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md6">
				<span>单行输入框</span>
				<input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-col-md6">
				<span>单行输入框</span>
				<input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md12">
				<span>单行输入框</span>
				<input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md12">
				<span>单行输入框</span>
				<textarea placeholder="请输入内容" class="layui-textarea"></textarea>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md12">
				<div style="text-align: center">
					<button class="layui-btn">默认按钮</button>
					<button class="layui-btn layui-btn-primary">原始按钮</button>
				</div>
			</div>
		</div>
	</form>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });
    });
</script>

</body>
</html>
