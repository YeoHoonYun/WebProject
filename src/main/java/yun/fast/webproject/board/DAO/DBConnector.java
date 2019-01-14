package yun.fast.webproject.board.DAO;

import java.sql.*;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
public class DBConnector {
    private static final String USERNAME = "yun";
    private static final String PASSWORD = "hadoop";
    private static final String CONN_STRING = "jdbc:mysql://192.168.0.101:3306/fastcampus?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";

    public static Connection connect(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(ps, conn);
    }

    public static void close(PreparedStatement ps, Connection conn){
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
