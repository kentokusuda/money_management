<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${error != null}">
        <div id="flush">
            入力内容に誤りがあります。
            <c:out value="${error}" />
        </div>
</c:if>
<label for="expense">出費額</label>
<br /><!-- 数字のみ入力で7桁まで入力可能、必須入力 -->
<input type="number" name="expense"  required value="${expenses.expense}" oninput="if(value.length>7)value=value.slice(0,7)">
円
<br />
<br />

<label for="remarks">備考</label>
<br />
<textarea name="remarks" rows="10" cols="50">${expenses.remarks}</textarea>
<br />

<button type="submit">更新</button>
<br />
<p>
    最終更新日時
    <c:if test="${not empty expenses.update_at}">
        <c:out value="${expenses.update_at}" />
    </c:if>