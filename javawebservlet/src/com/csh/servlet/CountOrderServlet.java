package com.csh.servlet;

import search.SearchDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 2018/11/22.
 */
@WebServlet(name = "CountOrderServlet")
public class CountOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long noOrder = 0;
        long haveOrder=0;
        try {
            noOrder = SearchDao.getOrderCountByStatus("2");
            haveOrder=SearchDao.getOrderCountByStatus("1");
            request.setAttribute("noOrder", noOrder);
            request.setAttribute("haveOrder", haveOrder);
            request.getRequestDispatcher("/echarts4.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
