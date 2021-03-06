package jp.co.jjs.java_seminar.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HealthJumpServlet
 */
@WebServlet("/HealthJumpServlet")
public class HealthJumpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthJumpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        process(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        process(request, response);
    }

    private void process(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String button = request.getParameter("button");
        String forward = "";

        switch (button) {
        case "一覧":
            forward = "HealthListServlet";
            break;
        case "記録":
            forward = "HealthInsertServlet";

            break;
        case "検索":
            forward = "WEB-INF/jsp/healthsearch/healthsearch.jsp";
            break;
        case "BMI計算":
            forward = "WEB-INF/jsp/healthbmi/healthbmi.jsp";

            break;
        default:
            break;
        }

        RequestDispatcher dispacher = request.getRequestDispatcher(forward);
        dispacher.forward(request, response);

    }

}
