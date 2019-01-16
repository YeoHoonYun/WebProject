package yun.fast.webproject.board.DAO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
public class DBConnector {
    private static HikariConfig config = null;
    private static DataSource ds = null;
    private static DBConnector instance = new DBConnector();

    private DBConnector(){
        String configFile = "/datasource.properties";
        HikariConfig config = new HikariConfig(configFile);

//        HikariConfig config = new HikariConfig();
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=UTF-8");
//        config.setUsername("connect");
//        config.setPassword("connect");

        ds = new HikariDataSource(config);
    }

    public static DBConnector getInstance(){
        return instance;
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = ds.getConnection();
        }catch(Exception ex){
            ex.printStackTrace(); // 로그를 남기는 코드가 있어야 한다.
            throw new RuntimeException("DB연결을 할 수 없습니다.");
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        try{ rs.close(); } catch(Exception ignore){}
        close(ps, conn);
    }

    public static void close(PreparedStatement ps, Connection conn){
        try{ ps.close(); } catch(Exception ignore){}
        try{ conn.close(); } catch(Exception ignore){}
    }
}
