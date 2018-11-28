package com.csh.servlet;

import csh.entity.Order;
import jdbc.dao.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import search.SearchDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/11/16.
 */
@WebServlet(name = "ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String start = "";
        String end = "";
        String contrller = "";
        String uri = request.getRequestURL() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        System.out.println(uri);
        Map<String, String[]> querymap = request.getParameterMap();

        //�жϲ�Ϊ��
        for (String key : querymap.keySet()) {
            String t = querymap.get(key)[0];
            if (key.equals("startdate")) {
                if (!t.equals("")) {
                    start = querymap.get(key)[0];
                }
            } else if (key.equals("startend")) {
                if (!t.equals("")) {
                    end = querymap.get(key)[0];
                }

            } else if (key.equals("contrller")) {
                if (!t.equals("")) {
                    contrller = querymap.get(key)[0];
                }
            }
        }

        if (start.equals("") || end.equals("") || start.equals("null") || end.equals("null")) {
            //�ж�ʱ��Ϊ����״̬Ϊ��
            if (contrller.equals("") || contrller.equals("null")) {
                try {
                    export(request, response,SearchDao.getAll());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //�ж�ʱ��Ϊ�յ���״̬��Ϊ��
            } else {
                try {
                    export(request, response,SearchDao.exportOrderByStatus(contrller));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            //�ж�ʱ�䲻Ϊ����״̬��Ϊ��
            if (!contrller.equals("") && !contrller.equals("null")) {
                try {
                    export(request, response,SearchDao.exportOrderByDateAndContrller(start, end, contrller));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //�ж�ʱ�䲻Ϊ�գ�״̬Ϊ��
                try {
                    export(request, response,SearchDao.exportOrderByDate(start, end));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void export(HttpServletRequest request, HttpServletResponse response,List<Order> order) throws Exception {
        //��ȡ����
        List<Order> list = order;

        //excel����
        String[] title = {"ID", "name", "status", "update_time", "comment"};

        //excel�ļ���
        String fileName = "����ͳ�Ʊ�" + System.currentTimeMillis() + ".xls";

        //sheet��
        String sheetName = "����ͳ��";

        String[][] content=new String[list.size()][title.length];
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            Order obj = list.get(i);
            content[i][0] = String.valueOf(obj.getId());
            content[i][1] = obj.getName();
            if(obj.getStatus()==1){
                content[i][2] = "�Ӱ��Ѷ���";
            }else if(obj.getStatus()==2){
                content[i][2] = "�Ӱ�δ����";
            }
            //content[i][2] = String.valueOf(obj.getStatus());
            content[i][3] = obj.getUpdatetime();
            //content[i][4] = obj.get("comment").tostring();
        }

//����HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

//��Ӧ���ͻ���
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //������Ӧ������
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


