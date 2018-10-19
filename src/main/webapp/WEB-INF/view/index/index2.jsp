<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="/my/image/domor.ico" />

<title>一路研学</title>
<link href="/my/css/my.css" rel="stylesheet" type="text/css">
<link href="/my/js/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="/my/js/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
<link href="/my/js/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
<link href="/my/js/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="/my/js/easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">
<link href="/my/js/easyui/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/my/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/my/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/my/js/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="/my/js/my/jquery.extends.js"></script>
<script type="text/javascript" src="/my/js/my/easyui.extends.js"></script>
<script type="text/javascript" src="/my/js/my/easyui.form.js"></script>
<script type="text/javascript" src="/my/js/my/formatter.js"></script>
<script type="text/javascript" src="/my/js/my/js_utils.js"></script>

<link href="/my/js/ueditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript">var webUrl = "${web_url}";</script>
<script type="text/javascript" src="/my/js/ueditor/umeditor.config.js"></script>
<script type="text/javascript" src="/my/js/ueditor/umeditor.min.js"></script>
<script type="text/javascript" src="/my/js/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>

<body>
	<div id="index_layout" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
        	<div class="theme-navigate">
                <div class="left">
                    <a href="#" onclick="collapseMenu();" class="easyui-linkbutton"><i class="fa fa-bars fa-lg"></i></a>
                </div>
                <div class="right">
                    <a href="#" class="easyui-menubutton theme-navigate-user-button" data-options="menu:'.theme-navigate-user-panel',hasDownArrow:false">${user.realname }</a>
                    <a href="#" class="easyui-linkbutton">新建</a>
                    <a href="#" class="easyui-menubutton" data-options="menu:'#mm1',hasDownArrow:false">文件</a>
                    <a href="#" class="easyui-menubutton" data-options="menu:'#mm2',hasDownArrow:false">编辑</a>
                    <a href="#" class="easyui-menubutton" data-options="menu:'#mm3',hasDownArrow:false">消息<span class="badge color-default">15</span></a>
                    <a href="#" class="easyui-menubutton theme-navigate-more-button" data-options="menu:'#more',hasDownArrow:false"></a>
                    
                    <div class="theme-navigate-user-panel">
                       <dl>
                       		<dd>
                            	<img src="/my/js/easyui/themes/insdep/images/portrait86x86.png" width="86" height="86">
                                <b class="badge-prompt">${user.realname }<i class="badge color-important">10</i></b>
                                <span>${user.phone }</span>
                                <p>${user.roleName }</p>
                            </dd>
                            <dt>
                            	<a onclick="openPwdUpdateDialog();">修改密码</a>
                                <a href="/exit" class="theme-navigate-user-logout">退出</a>
                            </dt>
                       </dl>
                    </div>
                    <div id="mm1" class="theme-navigate-menu-panel">
                    	<div>新建</div>
                        <div>打开</div>
                        <div>
                        	<span>打开最近文件</span>
                            <div>
                                <div>1 index.html</div>
                                <div>2 calendar-custom.html</div>
                                <div>3 combo-animation.html</div>
                                <div>4 datebox-restrict.html</div>
                                <div class="menu-sep"></div>
                                <div>启动时重新打开文件</div>
                            </div>
                        </div>
                        <div>关闭</div>
                        <div>全部关闭</div>
                        <div class="menu-sep"></div>
                        <div data-options="disabled:true,iconCls:'icon-save'">保存</div>
                        <div>另存为</div>
                        <div data-options="disabled:true">保存为全部</div>
                        <div class="menu-sep"></div>
                        <div>
                            <span>导入</span>
                            <div>
                                <div>XML 到模板</div>
                                <div>表格式数据</div>
                                <div data-options="disabled:true">Word 文档</div>
                                <div data-options="disabled:true">Excel 文档</div>
                            </div>
                        </div>
                        <div>
                            <span>导出</span>
                            <div>
                                <div>表格</div>
                            </div>
                        </div>
                        <div class="menu-sep"></div>
                        <div>退出</div>
                    </div>
                    <div id="mm2" class="theme-navigate-menu-panel">
                        <div>撤销</div>
                        <div data-options="disabled:true">重做</div>
                        <div class="menu-sep"></div>
                        <div>剪切</div>
                        <div>复制</div>
                        <div data-options="disabled:true">粘贴</div>
                        <div data-options="disabled:true">选择性粘贴</div>
                        <div data-options="disabled:true">清除</div>
                        <div class="menu-sep"></div>
                        <div>全选</div>
                        <div>选择父标签</div>
                        <div>选择子标签</div>
                        <div class="menu-sep"></div>
                        <div>查找和替换</div>
                        <div>查找所选</div>
                        <div>查找下一个</div>
                        <div class="menu-sep"></div>
                        <div>快捷键</div>
                        <div>首选项</div>
                    </div>
                    <div id="mm3" class="theme-navigate-menu-panel" style="width:180px;">
                        <div>产品消息<span class="badge color-success">5</span></div>
                        <div>安全消息<span class="badge color-important">10</span></div>
                        <div>服务消息</div>
                        <div class="menu-sep"></div>
                        <div>查看历史消息</div>
                        <div class="menu-sep"></div>
                        <div>清除消息提示</div>
                    </div>
                    <div id="more" class="theme-navigate-more-panel">
                    	<div>联系我们</div>
                        <div>参与改进计划</div>
                        <div>检测更新</div>
                        <div>关于</div>
                    </div>
                </div>
            </div>
        
        </div>

        <!--开始左侧菜单-->
        <div data-options="region:'west',border:false,bodyCls:'theme-left-layout',hideExpandTool:true,collapsedSize:0" style="width:200px;">
            <div class="theme-left-normal">
                <div class="easyui-layout" data-options="border:false,fit:true">
                    <div data-options="region:'north',border:false" style="height:80px;">
                        <div class="theme-left-user-panel">
                            <dl>
                                <dt><img src="/my/js/easyui/themes/insdep/images/portrait86x86.png" width="43" height="43"></dt>
                                <dd>
                                    <b class="badge-prompt">${user.realname } <i class="badge color-important">10</i></b>
                                    <p>${user.roleName}</p>
                                </dd>
                            </dl>
                        </div>
                    </div>

                    <div data-options="region:'center',border:false">
                        <div class="easyui-accordion" data-options="border:false,fit:true">
                        	<c:forEach items="${menus }" var="menu">
	                            <c:if test="${!empty menu.menuUrl }">
	                            <div title="<i class='fa fa-fw ${menu.iconUrl}'></i>&nbsp;${menu.menuName }" iconCls="fa-cog" onclick='tabManager("${ menu.menuName}","${menu.menuUrl }",this)'></div>
	                            </c:if>
	                            <c:if test="${empty menu.menuUrl }">
	                            <div title="<i class='fa fa-fw ${menu.iconUrl}'></i>&nbsp;${menu.menuName }">
	                            	<c:if test="${!empty menu.children}">
	                                <ul class="easyui-datalist" data-options="border:false,fit:true">
	                            		<c:forEach items="${menu.children }" var="cmenu">
	                                    <li><span onclick='tabManager("${ cmenu.menuName}","${cmenu.menuUrl }",this)'>
                                            <i class='fa fa-fw ${cmenu.iconUrl}'></i>&nbsp;${cmenu.menuName }
                                        </span></li>
	                            		</c:forEach>
	                                </ul>
	                                </c:if>
	                            </div>
	                            </c:if>
                            </c:forEach> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--结束左侧菜单-->

        <div data-options="region:'center',border:false" id="control" style="background:#fff;">
        	<div id="center_tabs" class="easyui-tabs theme-tab-green-line theme-tab-unbackdrop" data-options="fit:true,border:false"></div>
        	<div id="tab_menu" class="easyui-menu" style="width: 150px;">
				<div id="tab_menu-tabrefresh" data-options="iconCls:'icon-reload'">刷新</div>
				<div id="tab_menu-tabclose" data-options="iconCls:'icon-remove'">关闭</div>
				<div class="menu-sep"></div>
				<div id="tab_menu-tabcloseall">关闭所有</div>
				<div id="tab_menu-tabcloseother">关闭其他</div>
				<div id="tab_menu-tabcloseright">关闭右边</div>
				<div id="tab_menu-tabcloseleft">关闭左边</div>
			</div>
        </div>
    </div>
<script>
    /*菜单列表展开与隐藏*/
    var collapsed = false;
    function collapseMenu() {
        if (collapsed) {
            $("#index_layout").layout("expand", "west");
            collapsed = false;
        } else {
            $("#index_layout").layout("collapse", "west");
            collapsed = true;
        }
    }

    /*内容区域选项卡相关操作begin*/
    var $tabArea = null;//tabAreaId  : 中间内容区tab选项卡控件的id
    var $tabMenu = null;//tabMenuId  : 右键弹出的菜单div的id tab_menu
    var indexTitle = "首   页";//首页标题
    $(function() {
        $tabArea = $("#center_tabs");//tabAreaId  : 中间内容区tab选项卡控件的id
        $tabMenu = $("#tab_menu");//tabMenuId  : 右键弹出的菜单div的id  tab_menu
        initTabMenu();//初始化TAB
        tabManager(indexTitle, '/my/info.html');//打开首页
        $(document).on('focus', '.textbox-text', function() {
            $(this).select();
        });
    });
    //初始化tab选项卡的右键菜单
    function initTabMenu() {
        $tabArea.tabs({
            plain: true,
            narrow: true,
            onContextMenu : function(e, title) {
                e.preventDefault();
                $tabArea.tabs('select', title);
                $tabMenu.menu('show', {
                    left : e.pageX,
                    top : e.pageY
                }).data("tabTitle", title);
            }
        });
        tabRightKeyOper();
    }
    function tabManager(title, url, dom) {
        //如果是主页，不显示关闭按钮
        var closable = (title == indexTitle ? false:true);
        //如果存在标题为node.text的tab就先关闭后重新打开
        if ($tabArea.tabs('exists', title)) {
            $tabArea.tabs('close', title);//先关闭后重新打开
            $tabArea.tabs('add', {
                style: {padding:15,background:'#f2f2f2'},
                cls: 'tabs-border-radius',
                title : title,
                href : url,
                closable : closable
            });
        } else { //如果不存在标题为title的tab就增加
            $tabArea.tabs('add', {
                style: {padding:15,background:'#f2f2f2'},
                cls: 'tabs-border-radius',
                title : title,
                href : url,
                closable : closable
            });
        }
    }
    //tab选项卡上右键菜单中的操作方法
    function tabRightKeyOper() {
        $('#tab_menu-tabrefresh').click(function() {
            //获取当前右键操作的tab选项卡的标题title
            var currTitle = $tabMenu.menu().data("tabTitle");
            //根据标题获取选项卡tab对象
            var currTab = $tabArea.tabs('getTab', currTitle);
            //将当前tab设置为选中状态
            $tabArea.tabs('select', currTitle);
            var url = currTab.panel('options').href;
            $tabArea.tabs('update', {
                tab : currTab,
                options : {href : url }
            });
            currTab.panel('refresh');
        });
        //关闭当前
        $('#tab_menu-tabclose').click(function() {
            //获取当前右键操作的tab选项卡的标题title
            var currTitle = $tabMenu.menu().data("tabTitle");
            $tabArea.tabs('close', currTitle);
        });
        //全部关闭
        $('#tab_menu-tabcloseall').click(function() {
            var ts = $tabArea.tabs('tabs');
            var titles = [];
            for ( var i = 0; i < ts.length; i++) {
                var t = ts[i].panel('options').title;
                if(t != indexTitle)	titles.push(t);
            }
            for ( var i = 0; i < titles.length; i++) {
                $tabArea.tabs('close', titles[i]);
            }
        });
        //关闭除当前之外的TAB
        $('#tab_menu-tabcloseother').click(function() {
            //获取当前右键操作的tab选项卡的标题title
            var currTitle = $tabMenu.menu().data("tabTitle");
            var ts = $tabArea.tabs('tabs');
            var titles = [];
            for ( var i = 0; i < ts.length; i++) {
                var t = ts[i].panel('options').title;
                if (t != currTitle)
                    titles.push(t);
            }
            for ( var i = 0; i < titles.length; i++)
                $tabArea.tabs('close', titles[i]);
        });
        //关闭当前右侧的TAB
        $('#tab_menu-tabcloseright').click(function() {
            //获取当前右键操作的tab选项卡的标题title
            var currTitle = $tabMenu.menu().data("tabTitle");
            //根据标题获取选项卡tab对象
            var currTab = $tabArea.tabs('getTab', currTitle);
            //获取该tab对象的index索引
            var currIndex = $tabArea.tabs('getTabIndex', currTab);
            var ts = $tabArea.tabs('tabs');
            var titles = [];
            for ( var i = currIndex + 1; i < ts.length; i++) {
                var t = ts[i].panel('options').title;
                titles.push(t);
            }
            for ( var i = 0; i < titles.length; i++){
                $tabArea.tabs('close', titles[i]);
            }
            //选中标题为currTitle的tab选项卡
            $tabArea.tabs('select', currTitle);
        });
        //关闭当前左侧的tab
        $('#tab_menu-tabcloseleft').click(function() {
            //获取当前右键操作的tab选项卡的标题title
            var currTitle = $tabMenu.menu().data("tabTitle");
            //根据标题获取选项卡tab对象
            var currTab = $tabArea.tabs('getTab', currTitle);
            //获取该tab对象的index索引
            var currIndex = $tabArea.tabs('getTabIndex', currTab);
            var ts = $tabArea.tabs('tabs');
            var titles = [];
            for ( var i = 0; i < currIndex; i++) {
                var t = ts[i].panel('options').title;
                titles.push(t);
            }
            for ( var i = 0; i < titles.length; i++)
                $tabArea.tabs('close', titles[i]);
            //选中标题为currTitle的tab选项卡
            $tabArea.tabs('select', currTitle);
        });
    }
    /*内容区域选项卡相关操作end*/

    function openPwdUpdateDialog() {
        var $dialog = $("<div id='pwd_update_dialog'></div>");
        $dialog.dialog({
            href: '/index/pwd_update',
            iconCls:'icon-save',
            title: '修改密码',
            width: 420,
            height: 320,
            modal: true,
            onClose: function() { $dialog.dialog('destroy'); }
        });
        $dialog.dialog('open');
    }
</script>
<!--第三方插件加载-->
<script src="/my/js/easyui/plugin/justgage-1.2.2/raphael-2.1.4.min.js"></script>
<script src="/my/js/easyui/plugin/justgage-1.2.2/justgage.js"></script>
<script src="/my/js/easyui/plugin/Highcharts-5.0.0/js/highcharts.js"></script>
<!--第三方插件加载结束-->
</body>
</html>
