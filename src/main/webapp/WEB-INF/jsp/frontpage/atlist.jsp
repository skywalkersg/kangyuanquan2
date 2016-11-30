<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty varList}">
            <c:forEach items="${varList}" var="var" varStatus="vs">
                <a href="goatdetail.do?ACTIVITYMANAGE_ID=${var.ACTIVITYMANAGE_ID}">${var.TITLE}</a>
            </c:forEach>
    </c:when>
    <c:otherwise>
        没有数据
    </c:otherwise>
</c:choose>
</body>
</html>
