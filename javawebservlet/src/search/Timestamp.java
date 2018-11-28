package search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ��ˬ
 * @create 2018-10-26 10:09
 **/
public class Timestamp {
    /*
    * ��ʱ���ת��Ϊʱ��
    */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        //System.out.println(res);
        return res;
    }


        /*
         * ��ʱ��ת��Ϊʱ���
         */

    public static Long dateToStamp(String s) throws ParseException {
        //Long res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        //res = String.valueOf(ts);
        return ts;
    }

    public static Long dateToStamp2(String s) throws ParseException {
        //Long res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        //res = String.valueOf(ts);
        return ts;
    }
}
