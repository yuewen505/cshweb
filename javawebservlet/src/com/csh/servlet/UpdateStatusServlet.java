package com.csh.servlet;

import search.SearchDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/11/5.
 */
@WebServlet(name = "UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String username=request.getParameter("username");
        //��ȡ����url�е��û����֣�������ת��
        String username="";
        String status="";
        String comment="";
        Map<String, String[]> querymap = request.getParameterMap();
        for (String key:querymap.keySet()){
            if (key.equals("username")){
                username=new String(querymap.get(key)[0].getBytes("ISO8859-1"),"UTF-8");
                System.out.println(username);
            }else if (key.equals("status")){
                status=new String(querymap.get(key)[0].getBytes("ISO8859-1"),"UTF-8");
                System.out.println(status);
            }else if(key.equals("comment")){

            }
        }
        SearchDao searchDao=new SearchDao();
        String msg="";
        try {
            List list=searchDao.gettodayuserbyname(username);
            System.out.println("�б�ĳ���Ϊ��"+list.size());
            if(list.size()==0){
                searchDao.updateuserstatusbyname(username,status);
                request.getRequestDispatcher("/QueryResultServlet").forward(request,response);
            }else{
                msg="�������Ѿ���������~";
                System.out.println(msg);
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/QueryResultServlet").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
