<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">


        <p><c:out value="${year}" />年<c:out value="${month}" />月のデータ</p>
        <br />
        <p class = year_month>確認したい年月を選んでください</p>
        <form method="POST" action="<c:url value='/expenses/index' />">
            <input type="number" name="expenses_year" min="2019" max="2030" />
            <label for="expenses_year">年</label>
            <input type="number" name="expenses_month" min="1" max="12" />
            <label for="expenses_month">月</label><br />
            <button type="submit">決定</button>
        </form>
        <table id="expenses_list">
            <tbody>
                <tr>
                    <th class="expenses_day">日付</th>
                    <th class="expenses_expense">金額</th>
                    <th class="expenses_remarks">備考</th>
                </tr>

                <c:forEach var="expenses" items="${expenses}">
                    <tr class="row${status.count % 2}">
                        <td class="expenses_day"><a href="${pageContext.request.contextPath}/edit?id=${expenses.id}"><c:out value="${expenses.day}" />日</a></td>
                        <td class="expenses_expense"><c:out
                                value="${expenses.expense}" />円</td>
                        <td class="expenses_remarks"><c:out
                                value="${expenses.remarks}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



    </c:param>
</c:import>