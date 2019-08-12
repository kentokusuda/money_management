<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>


        <p class = index_top><c:out value="${year}" />年<c:out value="${month}" />月出費一覧</p>
        <br />
        <p class = year_month>確認したい年月を選んでください</p>
        <form method="POST" action="<c:url value='/index' />">
            <input type="number" name="expenses_year" min="2019" max="2030" value ="${year}"  required />

            <label for="expenses_year">年</label>
            <input type="number" name="expenses_month" min="1" max="12" value ="${month}" required/>
            <label for="expenses_month">月</label>
            <button type="submit">選択</button>
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

                         <c:choose>
                            <c:when test="${empty expenses.remarks}">
                                <td class="expenses_remarks"></td>
                            </c:when>
                            <c:otherwise>
                                <td class="expenses_remarks">${expenses.remarks}</td>
                            </c:otherwise>
                        </c:choose>


                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <p class = index_footer>合計<c:out
                                value="${sum_money}" />円</p>

        <br />
    </c:param>
</c:import>