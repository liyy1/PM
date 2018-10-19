<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
<form id="dept_add_form" method="post" action='/dept/insert'>
	<table style="padding-top:20px;padding-left: 50px;">
		<tr style="height:45px;">
			<td style="width:70px;text-align:right;">部门名称：</td>
			<td>
				<input id="dept_add_name" class="easyui-textbox" name="name" style="width:240px;"  data-options="required:true">
			</td>
		</tr>
		  <tr style="height:45px;">
			<td style="width:70px;text-align:right;">上级部门：</td>
			<td>
				<input id="dept_add_parent_name" value=""  style="width:240px;"  data-options="required:true">
				<input id="dept_add_parent_id" type="hidden" name="parentId" value=""/>
				<input id="dept_add_level" type="hidden" name="deptLevel" value=""/>
			</td>
		</tr>   
		<tr style="height:90px;">
			<td style="width:70px;text-align:right;">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
			<td>
				<input class="easyui-textbox" data-options="multiline:true" id="dept_add_memo" name="memo"  style="width:240px;height:80px"></textarea>			
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
$(function() {
	$('#dept_add_parent_name').combotree({
		url: '/dept/tree', 
		editable: false,
		onClick: function(node){
			$('#dept_add_parent_id').val(node.id);
			$('#dept_add_level').val(node.level + 1);
		}
	}); 

	$('#dept_add_lat').textbox({
		editable: false,
		width:240,
		buttonText:'查询',    
	    iconAlign:'right',
	    required:true,
	    icons:[{
			iconCls:'icon-clear',
			handler: function(e){
				$(e.data.target).textbox('clear').textbox('textbox').focus();
			}
		}],
	    onClickButton:function(){
            var $map = $("<div id='dept_add_map' style='height:450px;width:500px;border:1px solid #CCC;position:absolute;top:-80px;right:-80px;z-index:100'></div>")
            var $closeMap = $("<div id='dept_add_closemap' onclick=javascript:$('#dept_add_map').remove();$(this).remove() style='position:absolute;top:-75px;right:400px;z-index:101;color:red;font-size:20px;font-weight:bolder;cursor:pointer'>X</div>");
            $("#dept_add_lat_td").append($map);
            $("#dept_add_lat_td").append($closeMap);
            $("#dept_add_lat_td").css("position","relative");
			// 百度地图API功能
			var map = new BMap.Map("dept_add_map");            // 创建Map实例
			var lng = $("#dept_add_area_lng").val();
			var lat =$("#dept_add_area_lat").val();
			
			if(!lng) lng = 116.587242;
			if(!lat) lat = 35.415394;
			
			map.centerAndZoom(new BMap.Point(lng,lat), 11);  // 初始化地图,设置中心点坐标和地图级别
			//map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
			//map.setCurrentCity("上海");          // 设置地图显示的城市 此项是必须设置的
			map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		 
			function showInfo(e){
				$('#dept_add_lat').textbox('setValue',e.point.lng + ", " + e.point.lat);
				$('#dept_add_hidden_lng').val(e.point.lng);
				$('#dept_add_hidden_lat').val(e.point.lat);
				var gc = new BMap.Geocoder();
				gc.getLocation(e.point, function(rs){
				   var addComp = rs.addressComponents;
				   //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
				   $("#dept_add_address").textbox('setValue',addComp.street+addComp.streetNumber);
				   $("#dept_add_map").remove();
				   $("#dept_add_closemap").remove()
				})
			}
			map.addEventListener("click", showInfo);
	    }
    });
     
	$('#dept_add_areaName').textbox({
    	editable: false,
    	width:240,
    	buttonText:'查询',    
        iconAlign:'right',
        required:true,
        icons:[{
			iconCls:'icon-clear',
			handler: function(e){
				$(e.data.target).textbox('clear').textbox('textbox').focus();
			}
		}],
        onClickButton:function(){
        	  showSelectAreaDialog($("#dept_add_areaId").val(),function(row){
				$("#dept_add_areaName").textbox('setValue',row.text);
				$("#dept_add_areaId").val(row.id);
				$("#dept_add_area_lng").val(row.datas.lng);
				$("#dept_add_area_lat").val(row.datas.lat);
				
	        }); 
        }
    });
	
});
</script>
</body>
</html>
