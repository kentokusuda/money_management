<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <div>詳細編集ページ</div>
             <form method="POST" action="<c:url value='/update' />">
                <c:import url="_form.jsp" />
             </form>
    </c:param>
</c:import>