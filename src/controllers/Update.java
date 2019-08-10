package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Expenses;
import utils.DBUtil;
import validators.Validator;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Expenses e = em.find(Expenses.class, (Integer) (request.getSession().getAttribute("expenses_id")));

        e.setExpense(Integer.parseInt(request.getParameter("expense")));
        e.setRemarks(request.getParameter("remarks"));
        e.setUpdate_at(new Timestamp(System.currentTimeMillis()));

        String error = Validator.validateExpense(e);
        if(!error.equals("")){
            em.close();
            request.setAttribute("expenses",e);
            request.setAttribute("error", error);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/expenses/edit.jsp");
            rd.forward(request, response);
        }else{
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "更新が完了しました。");

            request.getSession().removeAttribute("expenses_id");

            response.sendRedirect(request.getContextPath() + "/index");
        }

    }

}
