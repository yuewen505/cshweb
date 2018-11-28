package search;

/**
 * @author 丛爽
 * @create 2018-10-24 18:13
 **/

//import csh.entity.Order;

import csh.entity.Order;
import jdbc.dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SearchDao {
    /**
     * @throws SQLException
     */

    public static long getOrderCountByStatusDate(String start, String end, String contrller) throws SQLException {
        //int id = -1;
        try {
            //HH:mm:ss:SSS
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select COUNT(*) as total from order_user WHERE update_time BETWEEN ? AND ? and status=?");
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
                //totalrecordnumber++;
            }
            System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
            return Integer.parseInt(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(null);
    }

    public static List<Order> getOrderByStatusDate(String start, String end, String contrller, int currentpage, int maximum) throws SQLException {
        try {
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            int maximumstart = maximum * (currentpage - 1);
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE update_time BETWEEN ? AND ? and status=? GROUP BY id DESC limit " + maximumstart + "," + maximum);
//            ps.setString(1, uname);
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Order> getOrderByNameContrller(String uname, int currentpage, int maximum) throws SQLException {
        try {
            int maximumstart = maximum * (currentpage - 1);
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE name=? GROUP BY id DESC limit " + maximumstart + "," + maximum);
            ps.setString(1, uname);
//            ps.setInt(2, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getOrderCountByNameContrller(String uname) throws SQLException {
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select COUNT(*) as total from order_user WHERE name=?");
            //ps.setInt(1, Integer.parseInt(contrller));
//            ps.setInt(3, Integer.parseInt(contrller));
            ps.setString(1, uname);
            //ps.setInt(2, Integer.parseInt(contrller));
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
            }
           // System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }


    public static List<Order> getOrderByName(String username, int currentpage, int maximum) throws SQLException {
        try {
            int maximumstart = maximum * (currentpage - 1);
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE name=? GROUP BY id DESC limit " + maximumstart + "," + maximum);
            ps.setString(1, username);
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getOrderCountByName(String username) throws SQLException {
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select COUNT(*) as total from order_user WHERE name=?");
            //ps.setInt(1, Integer.parseInt(contrller));
//            ps.setInt(3, Integer.parseInt(contrller));
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
            }
            System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }


    public static List<Order> exportOrderByStatus(String contrller) throws SQLException {
        //int id = -1;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE status=? GROUP BY id DESC ");
            ps.setInt(1, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Order> getOrderByStatus(String contrller, int currentpage, int maximum) throws SQLException {
        //int id = -1;
        try {
            int maximumstart = maximum * (currentpage - 1);
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE status=? GROUP BY id DESC limit " + maximumstart + "," + maximum);
            ps.setInt(1, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getOrderCountByStatus(String contrller) throws SQLException {
        //int id = -1;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select COUNT(*) as total from order_user WHERE status=?");
            ps.setInt(1, Integer.parseInt(contrller));
//            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
            }
            System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
            return Integer.parseInt(null);
        }
    }


    public static List<Order> getOrderByStatusAndName(String contrller, String uname, int currentpage, int maximum) throws SQLException {
        //int id = -1;
        try {
            int maximumstart = maximum * (currentpage - 1);
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE status=? and name=?  GROUP BY id DESC limit " + maximumstart + "," + maximum);
            ps.setInt(1, Integer.parseInt(contrller));
            ps.setString(2, uname);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getOrderCountByStatusAndName(String contrller, String uname) throws SQLException {
        //int id = -1;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select COUNT(*) as total from order_user WHERE status=? and name=?");
            ps.setInt(1, Integer.parseInt(contrller));
//            ps.setInt(3, Integer.parseInt(contrller));
            ps.setString(2, uname);
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
            }
            System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getTodayCount() throws SQLException {
        //int id = -1;
        try {

            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select count(*) as total from order_user Where date_format(from_unixtime(update_time/1000),'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')");
//            ps.setLong(1, startstamp);
//            ps.setLong(2, endstamp);
//            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
                //totalrecordnumber++;
            }
            System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
            return Integer.parseInt(null);
        }
    }

    public static List<Order> getAllByDate(int currentpage, int maximum) throws SQLException {
        //int id = -1;
        try {
            int maximumstart = maximum * (currentpage - 1);
//            Long startstamp = Timestamp.dateToStamp(start + ":000");
//            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user GROUP BY id DESC  limit " + maximumstart + "," + maximum);
            // ps.setString(1, "name");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            // long totalrecordnumber = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
                //totalrecordnumber++;
            }
            //System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static List<Order> getOrderByDate(String start, String end, int currentpage, int maximum) throws SQLException {
        //int id = -1;
        try {
            //HH:mm:ss:SSS
            //currentpage=currentpage-1;
            //分页起始点
            int maximumstart = maximum * (currentpage - 1);
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();
            //select * from order_user WHERE update_time BETWEEN 1541001600000 AND 1543507200000 GROUP BY update_time DESC limit 0,2 ;
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE update_time BETWEEN ? AND ? GROUP BY id DESC  limit " + maximumstart + "," + maximum);
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
//            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> exportOrderByDate(String start, String end) throws SQLException {
        //int id = -1;
        try {
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();
            //select * from order_user WHERE update_time BETWEEN 1541001600000 AND 1543507200000 GROUP BY update_time DESC limit 0,2 ;
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user WHERE update_time BETWEEN ? AND ? GROUP BY id DESC");
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
//            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getOrderCountByDate(String start, String end) throws SQLException {
        //int id = -1;
        try {
            //HH:mm:ss:SSS
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            System.out.println(startstamp);
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            System.out.println(endstamp);
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select COUNT(*) as total from order_user WHERE update_time BETWEEN ? AND ?");
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
//            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
                //totalrecordnumber++;
            }
            System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
            return Integer.parseInt(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(null);
    }


    public static long getOrderCountByDateContrllerName(String start, String end, String contrller, String uname) throws SQLException {
        //int id = -1;
        try {
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();//"select COUNT(*) as total from order_user WHERE update_time BETWEEN ? AND ?");
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select count(*) as total from order_user WHERE update_time BETWEEN ? AND ? and status=? and name=?");
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
            ps.setInt(3, Integer.parseInt(contrller));
            ps.setString(4, uname);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
            }
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
            return Long.parseLong(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Order> getOrderByDateContrllerName(String start, String end, String contrller, String uname, int currentpage, int maximum) throws SQLException {
        //int id = -1;
        try {
            int maximumstart = maximum * (currentpage - 1);
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    //"select * from order_user WHERE update_time BETWEEN ? AND ? GROUP BY id DESC  limit " + maximumstart + "," + maximum
                    .prepareStatement("select * from order_user WHERE update_time BETWEEN ? AND ? and status=? and name=? GROUP BY id DESC limit " + maximumstart + "," + maximum);
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
            ps.setInt(3, Integer.parseInt(contrller));
            ps.setString(4, uname);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static long getOrderCountByDateAndContrller(String start, String end, String contrller) throws SQLException {
        //int id = -1;
        try {
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();//"select COUNT(*) as total from order_user WHERE update_time BETWEEN ? AND ?");
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select count(*) as total from order_user WHERE update_time BETWEEN ? AND ? and status=?");
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
            }
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
            return Long.parseLong(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Order> getOrderByDateAndContrller(String start, String end, String contrller, int currentpage, int maximum) throws SQLException {
        //int id = -1;
        try {
            int maximumstart = maximum * (currentpage - 1);
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    //"select * from order_user WHERE update_time BETWEEN ? AND ? GROUP BY id DESC  limit " + maximumstart + "," + maximum
                    .prepareStatement("select * from order_user WHERE update_time BETWEEN ? AND ? and status=? GROUP BY id DESC limit " + maximumstart + "," + maximum);
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> exportOrderByDateAndContrller(String start, String end, String contrller) throws SQLException {
        //int id = -1;
        try {
            Long startstamp = Timestamp.dateToStamp(start + ":000");
            Long endstamp = Timestamp.dateToStamp(end + ":000");
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    //"select * from order_user WHERE update_time BETWEEN ? AND ? GROUP BY id DESC  limit " + maximumstart + "," + maximum
                    .prepareStatement("select * from order_user WHERE update_time BETWEEN ? AND ? and status=? GROUP BY id DESC");
            ps.setLong(1, startstamp);
            ps.setLong(2, endstamp);
            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateuserstatusbyname(String username) throws SQLException {
        try {
            Connection conn = Dao.getConnection();
            //UPDATE  order_user set status=1 Where name='丛爽' and date_format(from_unixtime(update_time/1000),'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

            //获取当前时间
            String time = sd.format(new Date());
            //输出当前时间
            System.out.println("输出当前时间:" + time);

            //时间转换为时间戳
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //java中时间戳毫秒计/1000 换算为秒
            long unixTimestamp = date.getTime();

            //输出时间戳
            System.out.println("输出时间戳:" + unixTimestamp);

            //long currenttime=System.currentTimeMillis();
            //String sql = "UPDATE  order_user set status=1 , update_time="+currenttime+" Where name=? ";
            //INSERT into order_user(name,status,update_time) VALUES ('雕刻',1,11111);
            String sql = "INSERT into  order_user(name,status,update_time) VALUES (?,?,?)";
//            String sql = "INSERT INTO table1(id,user,password,age) VALUES (?,?,?,?)";

            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, 1);
            ps.setLong(3, unixTimestamp);
            System.out.println(sql);
            // ps.setString(1, name);
            ps.executeUpdate();
//            List<Order> orderlist = new ArrayList();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int status = rs.getInt("status");
//                long updatetime = rs.getLong("update_time");
//                String updatetimes=String.valueOf(updatetime);
//                //updatetime=Timestamp.stampToDate(updatetimes);
//                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
//                orderlist.add(order);
//            }
            Dao.updateclose(ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // static String sql = "select * from Student where ? = ?";
    public static List<Order> gettodayuserbyname(String username) throws SQLException {
        try {

            Connection conn = Dao.getConnection();
            String sql = "select * from order_user Where name=? and date_format(from_unixtime(update_time/1000),'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') and status<>0 GROUP BY id DESC";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username);
//             ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                //updatetime=Timestamp.stampToDate(updatetimes);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateuserstatusbyname(String username, String userstatus) throws SQLException {
        try {
//            int s=Integer.parseInt(userstatus);
            Connection conn = Dao.getConnection();
            //UPDATE  order_user set status=1 Where name='丛爽' and date_format(from_unixtime(update_time/1000),'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

            //获取当前时间
            String time = sd.format(new Date());
            //输出当前时间
            System.out.println("输出当前时间:" + time);

            //时间转换为时间戳
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //java中时间戳毫秒计/1000 换算为秒
            long unixTimestamp = date.getTime();

            //输出时间戳
            System.out.println("输出时间戳:" + unixTimestamp);

            //long currenttime=System.currentTimeMillis();
            //String sql = "UPDATE  order_user set status=1 , update_time="+currenttime+" Where name=? ";
            //INSERT into order_user(name,status,update_time) VALUES ('雕刻',1,11111);
            String sql = "INSERT into  order_user(name,status,update_time) VALUES (?,?,?)";
//            String sql = "INSERT INTO table1(id,user,password,age) VALUES (?,?,?,?)";

            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, Integer.parseInt(userstatus));
            ps.setLong(3, unixTimestamp);
//            ps.setString(4, comment);
            //System.out.println(sql);
            // ps.setString(1, name);
            ps.executeUpdate();
            Dao.updateclose(ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Order> getAll() throws SQLException {
        //int id = -1;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select * from order_user GROUP BY id DESC ");
            // ps.setString(1, "name");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static long getAllCount() throws SQLException {
        //int id = -1;
        try {
            //HH:mm:ss:SSS
//            Long startstamp = Timestamp.dateToStamp(start + ":000");
//            System.out.println(startstamp);
//            Long endstamp = Timestamp.dateToStamp(end + ":000");
//            System.out.println(endstamp);
            Connection conn = Dao.getConnection();
            PreparedStatement ps = (PreparedStatement) conn
                    .prepareStatement("select COUNT(*) as total from order_user");
//            ps.setLong(1, startstamp);
//            ps.setLong(2, endstamp);
//            ps.setInt(3, Integer.parseInt(contrller));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            long totalrecordnumber = 0;
            while (rs.next()) {
                totalrecordnumber = rs.getLong("total");
            }
            System.out.println(totalrecordnumber);
            Dao.close(rs, ps, conn);
            return totalrecordnumber;
        } catch (SQLException e) {
            e.printStackTrace();
            return Integer.parseInt(null);
        }
        //return Integer.parseInt(null);
    }


    public static List<Order> getToday() throws SQLException {
        try {
            Connection conn = Dao.getConnection();
            String sql = "select * from order_user Where date_format(from_unixtime(update_time/1000),'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') GROUP BY id DESC";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            // ps.setString(1, "name");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<Order> orderlist = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int status = rs.getInt("status");
                long updatetime = rs.getLong("update_time");
                String updatetimes = String.valueOf(updatetime);
                //updatetime=Timestamp.stampToDate(updatetimes);
                Order order = new Order(id, name, status, Timestamp.stampToDate(updatetimes));
                orderlist.add(order);
            }
            Dao.close(rs, ps, conn);
            return orderlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
