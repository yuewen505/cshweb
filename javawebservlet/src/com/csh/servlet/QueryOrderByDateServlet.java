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
import java.util.Map;

/**
 * Created by admin on 2018/11/7.
 */
@WebServlet(name = "QueryOrderByDateServlet")
public class QueryOrderByDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取完整的请求url
//        String uri = request.getScheme() + "://"
//                + request.getServerName() + ":" + request.getServerPort()
//                + request.getRequestURI()
//                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        request.setCharacterEncoding("utf-8");
        String start = "";
        String end = "";
        String contrller = "";
        String uname = "";
        String uri = request.getRequestURL() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");


        // 当前是第几页
//        String currentpageStr ="1";
//        int currentpage = Integer.parseInt(currentpageStr);
        // 当前是第几页
        String currentpageStr = request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage");
        int currentpage = Integer.parseInt(currentpageStr);
        // 每页显示多少条
        int maximum = 100;
        // 可以显示多少页
        int viewperpage = 10;

        //如果为空，http://192.168.211.26:8080/UpdateStatusServlet?start=&end=&contrller=
        //不为空，http://192.168.211.26:8080/QueryOrderByDateServlet?start=2018-11-01&end=2018-11-07&contrller=0
        System.out.println(uri);
        Map<String, String[]> querymap = request.getParameterMap();
        //判断不为空
        for (String key : querymap.keySet()) {
            String t = querymap.get(key)[0];
            if (key.equals("start")) {
                if (!t.equals("")) {
                    start = querymap.get(key)[0];
                }
            } else if (key.equals("end")) {
                if (!t.equals("")) {
                    end = querymap.get(key)[0];
                }

            } else if (key.equals("contrller")) {
                if (!t.equals("")) {
                    contrller = querymap.get(key)[0];
                }
            } else if (key.equals("uname")) {
                if (!t.equals("")) {
                    uname = querymap.get(key)[0];
                    System.out.println("获取的控件名字：" + uname);
                }
            }
        }
        //判断时间为空
        if (start.equals("") || end.equals("") || start.equals("null") || end.equals("null")) {
            //判断状态为空
            if (contrller.equals("") || contrller.equals("null")) {
                //判断时间和状态都为空，名字为空
                if (uname.equals("") || uname.equals("null")) {
                    try {
                        long totalrecordnumber = SearchDao.getAllCount();
                        PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                        request.setAttribute("QueryResult", SearchDao.getAllByDate(currentpage, maximum));
                        request.setAttribute("pageView", pageView);
                        request.setAttribute("contrller", contrller);
                        request.setAttribute("uname", uname);
                        request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {//判断时间和状态都为空，姓名不为空
                    try {
                        long totalrecordnumber = SearchDao.getOrderCountByName(uname);
                        PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                        request.setAttribute("QueryResult", SearchDao.getOrderByName(uname, currentpage, maximum));
                        request.setAttribute("pageView", pageView);
                        request.setAttribute("contrller", contrller);
                        request.setAttribute("uname", uname);
                        request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (uname.equals("") || uname.equals("null")) {//判断时间为空，状态不为空,名字为空
                try {//getOrderCountByStatus
                    long totalrecordnumber = SearchDao.getOrderCountByStatus(contrller);
                    PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                    request.setAttribute("QueryResult", SearchDao.getOrderByStatus(contrller, currentpage, maximum));
                    request.setAttribute("pageView", pageView);
                    request.setAttribute("contrller", contrller);
                    request.setAttribute("uname", uname);
                    request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {//判断时间为空，状态不为空,名字不为空
                try {//getOrderCountByStatus
                    long totalrecordnumber = SearchDao.getOrderCountByStatusAndName(contrller, uname);
                    PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                    request.setAttribute("QueryResult", SearchDao.getOrderByStatusAndName(contrller, uname, currentpage, maximum));
                    request.setAttribute("pageView", pageView);
                    request.setAttribute("contrller", contrller);
                    request.setAttribute("uname", uname);
                    request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //时间不为空
        } else {
            //时间不为空，状态为空
            if (contrller.equals("") || contrller.equals("null")) {
                //时间不为空，状态为空，名字为空
                if (uname.equals("") || uname.equals("null")) {
                    try {
                        long totalrecordnumber = SearchDao.getOrderCountByDate(start, end);
                        PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                        request.setAttribute("QueryResult", SearchDao.getOrderByDate(start, end, currentpage, maximum));
                        request.setAttribute("pageView", pageView);
                        request.setAttribute("start", start);
                        request.setAttribute("end", end);
                        request.setAttribute("contrller", contrller);
                        request.setAttribute("uname", uname);
                        request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {//时间不为空，状态为空，名字不为空
                    try {
                        long totalrecordnumber = SearchDao.getOrderCountByNameContrller(uname);
                        PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                        request.setAttribute("QueryResult", SearchDao.getOrderByNameContrller(uname,currentpage, maximum));
                        request.setAttribute("pageView", pageView);
                        request.setAttribute("contrller", contrller);
                        request.setAttribute("uname", uname);
                        request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (uname.equals("") || uname.equals("null")) {//判断时间不为空，状态不为空,名字为空
                try {
                    long totalrecordnumber = SearchDao.getOrderCountByStatusDate(start, end, contrller);
                    PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                    request.setAttribute("QueryResult", SearchDao.getOrderByStatusDate(start, end, contrller, currentpage, maximum));
                    request.setAttribute("pageView", pageView);
                    request.setAttribute("contrller", contrller);
                    request.setAttribute("uname", uname);
                    request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {//判断时间不为空，状态不为空,名字不为空
                try {
                    long totalrecordnumber = SearchDao.getOrderCountByDateContrllerName(start, end, contrller, uname);
                    PageView pageView = new PageView(totalrecordnumber, currentpage, maximum, viewperpage);
                    request.setAttribute("QueryResult", SearchDao.getOrderByDateContrllerName(start, end, contrller, uname, currentpage, maximum));
                    request.setAttribute("pageView", pageView);
                    request.setAttribute("contrller", contrller);
                    request.setAttribute("uname", uname);
                    request.getRequestDispatcher("/order-list.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

