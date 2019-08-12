<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang ="ja">
<head>
    <meta charset="UTF-8">
    <title>支出管理</title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h1><a href="${pageContext.request.contextPath}/via">支出管理</a></h1>
        </div>
        <div id="content">${param.content }</div>
        <div id="footer">作成 楠田</div>
    </div>
</body>
</html>