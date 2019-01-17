package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DTO.User;
import yun.fast.webproject.board.Util.ConnectionContextHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-17
 * Github : https://github.com/YeoHoonYun
 */
public class UserDaoImpl implements UserDao{
    private static UserDao instance = new UserDaoImpl();
    private UserDaoImpl(){}
    public static UserDao getInstance(){
        return instance;
    }

    @Override
    public void addUser(User user) {
        try{
            Connection conn = ConnectionContextHolder.getConnection();
            // name, nickname, email, passwd
            try(PreparedStatement ps = conn.prepareStatement(UserDAOSQL.INSERT);) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getNickname());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPasswd());
                ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getPasswdByEmail(String email) {
        String passwd = null; // return할 타입을 선언한다.

        Connection conn = ConnectionContextHolder.getConnection();
        try{

            try(PreparedStatement ps = conn.prepareStatement(UserDAOSQL.SELECT_BY_EMAIL);) {
                ps.setString(1, email);

                try(ResultSet rs = ps.executeQuery();){ // SELECT 문장을 실행, executeUpdate() - insert, update, delete

                    if (rs.next()) {
                        passwd = rs.getString(1);
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return passwd;
    }

    @Override
    public User getPasswdByUser(String email) {
        String name = null;
        String nickname = null;
        String passwd = null;
        Long id = 0L;
        Connection conn = ConnectionContextHolder.getConnection();
        try{

            try(PreparedStatement ps = conn.prepareStatement(UserDAOSQL.SELECT_BY_USER);) {
                ps.setString(1, email);

                try(ResultSet rs = ps.executeQuery();){ // SELECT 문장을 실행, executeUpdate() - insert, update, delete

                    if (rs.next()) {
                        id = rs.getLong(1);
                        name = rs.getString(2);
                        nickname = rs.getString(3);
                        email = rs.getString(4);
                        passwd = rs.getString(5);
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return new User(id, name, nickname, email, passwd);
    }
}