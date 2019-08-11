package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Via
 */
@WebServlet("/via")
public class Via extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Via() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //indexへ戻る際に現在の年月を表示するように戻すためのサーブレット

        request.getSession().removeAttribute("flush_year");
        request.getSession().removeAttribute("flush_month");


        RequestDispatcher rd = request.getRequestDispatcher("/index");
        rd.forward(request, response);
    }

}
