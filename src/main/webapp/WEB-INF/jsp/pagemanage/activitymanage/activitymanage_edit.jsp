<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
	<script type="text/javascript" charset="utf-8" src="static/ueditor1.4.3/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="static/ueditor1.4.3/ueditor.all.min.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="static/ueditor1.4.3/lang/zh-cn/zh-cn.js"></script>
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">

					<form action="activitymanage/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
						<input type="hidden" name="ACTIVITYMANAGE_ID" id="ACTIVITYMANAGE_ID" value="${pd.ACTIVITYMANAGE_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
								<table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:75px;text-align: right;padding-top: 13px;">标题:</td>
                                        <td><input type="text" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="255" placeholder="这里输入标题" title="标题" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:75px;text-align: right;padding-top: 13px;">描述:</td>
										<td>
											<div style="width:100%;padding: 10px;border: 1px solid #ccc;">
												<div class="ueQ313596790Que"></div><script id="editor" type="text/plain" style="height:200px;"></script><div class="ueQ313596790Que" style="width:95%;"></div>
											</div>
										</td>
										<%--<td>--%>
											<%--<script id="editor" type="text/plain" style="width:98%;height:500px;"></script>--%>
										<%--</td>--%>
                                        <%--<td><input type="text" name="DESCRIBES" id="DESCRIBES" value="${pd.DESCRIBES}" maxlength="255" placeholder="这里输入描述" title="描述" style="width:98%;"/></td>--%>
                                    </tr>
                                    <tr>
                                        <td style="width:75px;text-align: right;padding-top: 13px;">图片</td>
                                        <td id="imgcontext">
                                            <div style="width:98%;padding: 10px;border: 1px solid #ccc;">
                                                <c:choose>
                                                    <c:when test="${pd.IMG_PATH!=null}">
                                                        <img style="height: 300px;" src="${pageContext.request.contextPath}${pd.IMG_PATH}"/></br></br>
                                                        <span style="padding-left: 20px">
                                                                       <input class="btn btn-primary"  type="button" name="newImg" id="newImg" value="重新选择"/>
                                                                    </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="file" id="image" name="image"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:75px;text-align: right;padding-top: 13px;">是否展示在首页:</td>
                                        <td>
											<select name="ISSHOW" id="ISSHOW" style="width:98%;border: 1px solid #ccc;">
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
										</td>
                                    </tr>
                                    <tr>
                                        <td style="width:75px;text-align: right;padding-top: 13px;">发布人:</td>
                                        <td><input type="text" name="RELEASEPEOPLE" id="RELEASEPEOPLE" value="${pd.RELEASEPEOPLE}" maxlength="255" placeholder="这里输入发布人" title="发布人" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: center;" colspan="10">
                                            <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
                                            <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
                                        </td>
                                    </tr>
                                </table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						<input type="text" name="IMG_PATH" id="IMG_PATH" value="${pd.IMG_PATH}" maxlength="255" hidden="true"/>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#TITLE").val()==""){
				$("#TITLE").tips({
					side:3,
		            msg:'请输入标题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TITLE").focus();
			return false;
			}
			if($("#DESCRIBES").val()==""){
				$("#DESCRIBES").tips({
					side:3,
		            msg:'请输入描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DESCRIBES").focus();
			return false;
			}
			if($("#ISSHOW").val()==""){
				$("#ISSHOW").tips({
					side:3,
		            msg:'请输入是否展示在首页',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ISSHOW").focus();
			return false;
			}
			if($("#ADDTIME").val()==""){
				$("#ADDTIME").tips({
					side:3,
		            msg:'请输入添加时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ADDTIME").focus();
			return false;
			}
			if($("#ADDUSER").val()==""){
				$("#ADDUSER").tips({
					side:3,
		            msg:'请输入添加人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ADDUSER").focus();
			return false;
			}
			if($("#RELEASEPEOPLE").val()==""){
				$("#RELEASEPEOPLE").tips({
					side:3,
		            msg:'请输入发布人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RELEASEPEOPLE").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		function fileChange(){
			//检测上传文件的类型
			var videoName = $("#image").val();
			//获取"."所在的位置
			var idx = videoName.lastIndexOf(".");
			if( idx != -1 ){
				//获取文件的后缀名
				var ext = videoName.substr(idx+1).toUpperCase();
				//将后缀名变成小写
				ext = ext.toLowerCase( );
				if(ext != 'jpg' && ext != 'png' && ext != 'jpeg' && ext != 'gif' && ext != 'bmp'){
					$("#image").tips({
						side:3,
						msg:'上传的图片格式错误',
						bg:'#AE81FF',
						time:2
					});
					return false;
				} else {
					$("#Form").submit();
				}
			} else {
				$("#image").tips({
					side:3,
					msg:'上传的图片格式错误',
					bg:'#AE81FF',
					time:2
				});
				return false;
			}
		}
		$("#newImg").click(function(){
//            alert("重新选择");
			$("#imgcontext").empty();
			$("#imgcontext").append(" <input type='file' id='image' name='image'/>"+
					"<input  type='text' hidden='hidden' name='imgChange' id='imgChange' value='true'/>");
		});
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
<%--<div>
	<h1>完整demo</h1>
	<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<div id="btns">
	<div>
		<button onclick="getAllHtml()">获得整个html的内容</button>
		<button onclick="getContent()">获得内容</button>
		<button onclick="setContent()">写入内容</button>
		<button onclick="setContent(true)">追加内容</button>
		<button onclick="getContentTxt()">获得纯文本</button>
		<button onclick="getPlainTxt()">获得带格式的纯文本</button>
		<button onclick="hasContent()">判断是否有内容</button>
		<button onclick="setFocus()">使编辑器获得焦点</button>
		<button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
		<button onmousedown="setblur(event)" >编辑器失去焦点</button>

	</div>
	<div>
		<button onclick="getText()">获得当前选中的文本</button>
		<button onclick="insertHtml()">插入给定的内容</button>
		<button id="enable" onclick="setEnabled()">可以编辑</button>
		<button onclick="setDisabled()">不可编辑</button>
		<button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
		<button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
		<button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
	</div>

	<div>
		<button onclick="getLocalData()" >获取草稿箱内容</button>
		<button onclick="clearLocalData()" >清空草稿箱</button>
	</div>

</div>
<div>
	<button onclick="createEditor()">
		创建编辑器</button>
	<button onclick="deleteEditor()">
		删除编辑器</button>
</div>--%>

<script type="text/javascript">

	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');


	/*function isFocus(e){
		alert(UE.getEditor('editor').isFocus());
		UE.dom.domUtils.preventDefault(e)
	}
	function setblur(e){
		UE.getEditor('editor').blur();
		UE.dom.domUtils.preventDefault(e)
	}
	function insertHtml() {
		var value = prompt('插入html代码', '');
		UE.getEditor('editor').execCommand('insertHtml', value)
	}
	function createEditor() {
		enableBtn();
		UE.getEditor('editor');
	}
	function getAllHtml() {
		alert(UE.getEditor('editor').getAllHtml())
	}*/
	/*function getContent() {
		var arr = [];
		arr.push("使用editor.getContent()方法可以获得编辑器的内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getContent());
		alert(arr.join("\n"));
	}
	function getPlainTxt() {
		var arr = [];
		arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getPlainTxt());
		alert(arr.join('\n'))
	}
	function setContent(isAppendTo) {
		var arr = [];
		arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
		UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
		alert(arr.join("\n"));
	}
	function setDisabled() {
		UE.getEditor('editor').setDisabled('fullscreen');
		disableBtn("enable");
	}

	function setEnabled() {
		UE.getEditor('editor').setEnabled();
		enableBtn();
	}

	function getText() {
		//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		var range = UE.getEditor('editor').selection.getRange();
		range.select();
		var txt = UE.getEditor('editor').selection.getText();
		alert(txt)
	}

	function getContentTxt() {
		var arr = [];
		arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
		arr.push("编辑器的纯文本内容为：");
		arr.push(UE.getEditor('editor').getContentTxt());
		alert(arr.join("\n"));
	}
	function hasContent() {
		var arr = [];
		arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
		arr.push("判断结果为：");
		arr.push(UE.getEditor('editor').hasContents());
		alert(arr.join("\n"));
	}
	function setFocus() {
		UE.getEditor('editor').focus();
	}
	function deleteEditor() {
		disableBtn();
		UE.getEditor('editor').destroy();
	}
	function disableBtn(str) {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			if (btn.id == str) {
				UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
			} else {
				btn.setAttribute("disabled", "true");
			}
		}
	}
	function enableBtn() {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
		}
	}

	function getLocalData () {
		alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
	}

	function clearLocalData () {
		UE.getEditor('editor').execCommand( "clearlocaldata" );
		alert("已清空草稿箱")
	}*/
</script>
</body>
</html>