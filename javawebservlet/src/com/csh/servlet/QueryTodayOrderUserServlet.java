package com.csh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by admin on 2018/11/2.
 */
@WebServlet(name = "QueryTodayOrderUserServlet")
public class QueryTodayOrderUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //SearchDao searchDao=new SearchDao();
        HttpSession session=request.getSession();
//        try {
            //String data = (String) session.getAttribute("username");
            //request.setAttribute("QueryTodayOrderUserServlet", SearchDao.gettodayuserbyname((String) session.getAttribute("username")));
            request.setAttribute("username", session.getAttribute("username"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        request.getRequestDispatcher("/member-list.jsp").forward(request,response);

    }
}
