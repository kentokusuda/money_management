package controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

        //updateから飛んできたときにtopをupdateした年付にする
        Integer year;
        Integer month;

        if(request.getSession().getAttribute("flush_year") != null) {
             year = (Integer)request.getSession().getAttribute("flush_year");
             month = (Integer)request.getSession().getAttribute("flush_month");

        }else {

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
        }
        EntityManager em = DBUtil.createEntityManager();

        //合計金額
        Query query = em.createQuery("select sum(s.expense) from Expenses s WHERE s.year = :year AND s.month =:month", Object.class);
        Object sum_money_ob = query.setParameter("year", year)
                .setParameter("month", month).getSingleResult();

        String sum_money_st = sum_money_ob.toString();
        int sum_money_int = new Integer(sum_money_st).intValue();

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
        request.setAttribute("sum_money", sum_money_int);

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/expenses/index.jsp");
        rd.forward(request, response);

        em.close();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        //年月取得
        Integer year = Integer.parseInt(request.getParameter("expenses_year"));
        Integer month = Integer.parseInt(request.getParameter("expenses_month"));

        request.getSession().setAttribute("flush_year", year);
        request.getSession().setAttribute("flush_month", month);

        EntityManager em = DBUtil.createEntityManager();

        //合計金額
        Query query = em.createQuery("select sum(s.expense) from Expenses s WHERE s.year = :year AND s.month =:month", Object.class);
        Object sum_money_ob = query.setParameter("year", year)
                .setParameter("month", month).getSingleResult();

        String sum_money_st = sum_money_ob.toString();
        int sum_money_int = new Integer(sum_money_st).intValue();

        //選んだ年月のデータが0である時
        long m_count = (long) em.createNamedQuery("getReporCount", Long.class).setParameter("year", year)
                .setParameter("month", month).getSingleResult();
        if (m_count == 0) {

            //ここから今月の日数(int)取得
            Calendar calendar = Calendar.getInstance();


            Integer month2 = month -1;
            //今年の今月の1日をセット、一月進めて一日戻して最終日を取得する
            calendar.set(year, month2, 1);
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            int day = calendar.get(Calendar.DATE);

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
        request.setAttribute("sum_money", sum_money_int);

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/expenses/index.jsp");
        rd.forward(request, response);

        em.close();

    }
}