<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${expenses != null}">
                <div><c:out  value="${expenses.year}" />年<c:out  value="${expenses.month}" />月<c:out  value="${expenses.day}" />日</div>
                <form method="POST" action="<c:url value='/update' />">
                <c:import url="_form.jsp" />
            </form>
            </c:when>
            <c:otherwise>
                <div>データは見つかりませんでした。</div>
            </c:otherwise>
        </c:choose>
        <p class = top_return><a href="${pageContext.request.contextPath}/index">戻る</a></p>
    </c:param>
</c:import>