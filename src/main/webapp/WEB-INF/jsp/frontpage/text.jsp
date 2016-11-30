<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <title>详情</title>
    <link href="static/css/total.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="static/css/content.css"/>
    <script src="static/js/frontjs/jquery-1.9.1.min.js" type="text/javascript"></script>


</head>
<body>
<div class="content">
    <div class="local">
        <span>当前位置：活动公告详情
    </div>
    <div class="text_box">
        <div class="text_header">
            <h2>${pd.TITLE }</h2>
            <div class="back">
                <a href="javascript:history.back(-1)">返回上页</a>
            </div>
            <div class="informal">
                发布时间：<span class="time">${pd.ADDTIME}</span>
                发布者：<span>${pd.RELEASEPEOPLE }</span>
            </div>
            <div class="take_down">
                <img src="static/image/img/printer.png" height="26px" width="26px">
                <button onclick="window.print();">打印文本</button>
            </div>
        </div>
        <div class="text_img"></div>
        <div class="text_content">
            <img src="${pd.IMG_PATH}">
        </div>
        <div >
           ${pd.DESCRIBES}
        </div>


    </div>
</div>

<script>
window.onload=function(){
	if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE7.0")
    {
        //alert("IE 7.0");
        /*联系我们*/
        $(".back").css({"margin-left":"-486px"});
        $(".take_down").css({"left":"422px"});
    }
    else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE8.0")
    {
        //alert("IE 8.0");
        /*联系我们*/
        $("#name").before("<span class='text_ie1'>姓名：</span><span class='text_ie2'>电话：</span><span class='text_ie3'>E-mail：</span>")
    }
    else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE9.0")
    {
        //alert("IE 9.0");
        /*联系我们*/
        $("#name").before("<span class='text_ie1'>姓名：</span><span class='text_ie2'>电话：</span><span class='text_ie3'>E-mail：</span>")

    }
    else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE10.0")
    {
        //alert("IE 10.0");
    }
    var time = "${doc.ADD_TIME }";
	time = time.substring(0, 16);
	$(".time").append("<span>"+time+"</span>");
	
}
</script>

</body>
</html>

