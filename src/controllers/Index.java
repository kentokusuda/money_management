package controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Expenses;
import utils.DBUtil;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //年月取得
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;

        EntityManager em = DBUtil.createEntityManager();

        //今年今月のデータが0である時
        long m_count = (long) em.createNamedQuery("getReporCount", Long.class).setParameter("year", year)
                .setParameter("month", month).getSingleResult();
        if (m_count == 0) {

            //ここから今月の日数(int)取得
            Calendar calendar2 = Calendar.getInstance();
            Integer month2 = calendar2.get(Calendar.MONTH);

            //今年の今月の1日をセット、一月進めて一日戻して最終日を取得する
            calendar2.set(year, month2, 1);
            calendar2.add(Calendar.MONTH, 1);
            calendar2.add(Calendar.DAY_OF_MONTH, -1);

            int day = calendar2.get(Calendar.DATE);

            //DayにiをいれるためIntegerにした
            for (Integer i = 1; i <= day; i++) {
                //今月の日付分 年月日0円 だけはいったデータをつくる
                Expenses e = new Expenses();
                e.setYear(year);
                e.setMonth(month);
                e.setDay(i);
                e.setExpense(0);

                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
            }

        }

        //当年当月のデータ全部ひっぱる
        List<Expenses> expenses = em.createNamedQuery("getAllData", Expenses.class).setParameter("year", year)
                .setParameter("month", month).getResultList();

        request.setAttribute("expenses", expenses);
        request.setAttribute("year", year);
        request.setAttribute("month", month);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/expenses/index.jsp");
        rd.forward(request, response);



        em.close();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
