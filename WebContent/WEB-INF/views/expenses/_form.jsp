<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<label for="expense">金額</label>
<br />
<input type="number" name="expense" value="${expenses.expense}" />
<br />
<br />

<label for="remark">内容</label>
<br />
<textarea name="remark" rows="10" cols="50">${expenses.remarks}</textarea>
<br />

<button type="submit">決定</button>