package jdbc.dao;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * ����Excel������
 *
 * @author ��ˬ
 * @create 2018-11-15 10:07
 **/
public class ExcelUtil {
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {

        // ��һ��������һ��HSSFWorkbook����Ӧһ��Excel�ļ�
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // �ڶ�������workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������
        HSSFRow row = sheet.createRow(0);

        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // ����һ�����и�ʽ

        //�����ж���
        HSSFCell cell = null;

        //��������
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //��������
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //�����ݰ�˳�򸳸���Ӧ���ж���
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }
}
