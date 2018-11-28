package com.csh.servlet;

import csh.entity.PageView;
import search.SearchDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 2018/10/25.
 */
@WebServlet(name = "QueryResultServlet")
public class QueryResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentpageStr = request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage");
        int currentpage = Integer.parseInt(currentpageStr);
        // 每页显示多少条
        int maximum = 100;
        // 可以显示多少页
        int viewperpage = 20;
        SearchDao searchDao=new SearchDao();
        try {
            request.setAttribute("QueryResult",searchDao.getToday());
            long totalrecordnumber=SearchDao.getTodayCount();
            PageView pageView =new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
            request.setAttribute("pageView", pageView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/order-list.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
