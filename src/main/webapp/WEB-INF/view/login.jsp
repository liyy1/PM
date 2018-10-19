<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>项目管理系统</title>
    <style>
		.kit-login {
			position: relative;
			height: 100vh;
		}

		.kit-login-bg {
			position: absolute;
			top: 0px;
			left: 0px;
			right: 0px;
			bottom: 0px;
			background-size: cover;
			background-image: url(https://img.alicdn.com/tfs/TB1zsNhXTtYBeNjy1XdXXXXyVXa-2252-1500.png);
		}

		.kit-login-wapper {
			position: absolute;
			top: -100px;
			left: 0;
			right: 0;
			bottom: 0;
			max-width: 1080px;
			margin: 0 auto;
			display: -webkit-box;
			display: -webkit-flex;
			display: -ms-flexbox;
			display: flex;
			-webkit-justify-content: space-around;
			-ms-flex-pack: distribute;
			justify-content: space-around;
			-webkit-box-align: center;
			-webkit-align-items: center;
			-ms-flex-align: center;
			align-items: center;
		}

		.kit-login-wapper .kit-login-slogan {
			text-align: center;
			color: #fff;
			font-size: 36px;
			letter-spacing: 2px;
			line-height: 48px;
			margin-bottom: 9px;
			font-weight: 500;
		}

		.kit-login-wapper .kit-login-form {
			display: flex;
			justify-content: center;
			flex-direction: column;
			padding: 30px;
			background: #ffffff;
			border-radius: 6px;
			box-shadow: #eeeeee 1px 1px 2px;
			height: 260px;
			width: 280px;
			text-align: center;
		}

		.fl{float:left;color:#7d7d7d !important;}
		.fr{float:right;color:#7d7d7d !important;}

	</style>
</head>
<body>
<div class="kit-login">
	<div class="kit-login-bg"></div>
	<div class="kit-login-wapper">
		<h2 class="kit-login-slogan">欢迎使用 <br> 动脉智能项目管理系统</h2>
		<div class="kit-login-form">
			<span style="font-size:22px;color: #00b5ad">登<span style="font-family: 宋体">&nbsp;</span>录</span>
			<form>
				<div style="height:20px;padding-bottom: 10px;">
					<p id="login_errormsg" style="color:#FF5722;"></p>
				</div>
				<div style="margin-bottom:25px">
					<input id="username" name="username" class="easyui-textbox theme-textbox-radius" prompt="手机号或用户名" iconWidth="28" style="width:100%;height:38px;padding:10px;">
				</div>
				<div style="margin-bottom:16px">
					<input id="password" name="password" class="easyui-passwordbox theme-textbox-radius" prompt="密码" iconWidth="28" style="width:100%;height:38px;padding:10px">
				</div>
				<div style="height:20px;margin-bottom:10px">
					<p><a href="javascript:;" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p>
				</div>
				<div style="margin-bottom: 15px;">
					<a href="#" class="easyui-linkbutton button-info button-lg" style="width: 100%"  onClick="loginSystem();">登录</a>
				</div>
			</form>
		</div>
	</div>
	<div style="position: absolute;bottom: 5px;width: 100%;height: 25px;text-align: center;">
		<span style="font-size: 12px">Copyright ©<a style="font-size:12px;color: #113656" href="http://www.domor.net/" target="_blank">山东动脉智能科技股份有限公司</a>2018，All Rights Reserved</span>
	</div>
</div>
<script type="text/javascript">
    $(document).keypress(function(event) {
        if (event.keyCode == 13)
            loginSystem();
    });
    function loginSystem(){
        $("#login_errormsg").html("");
        var username = $("#username").val();
        var password = $("#password").val();
        if(!username){
            $("#login_errormsg").html("请输入手机号或用户名！");
            return;
        }
        if(!password){
            $("#login_errormsg").html("请输入密码！");
            return;
        }
        $.post('/login',{username:username,password:password},function(data){
            if(data.code==0){
                layer.msg('登录成功', {icon: 1,time: 1000}, function(){
                    window.location.href = '/index'; //后台主页
                });
            }else{
                $("#login_errormsg").html(data.message);
            }
        })
    }
</script>
</body>
</html>