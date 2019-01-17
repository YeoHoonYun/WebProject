package yun.fast.webproject.board.Service;

import yun.fast.webproject.board.DAO.UserDAOSQL;
import yun.fast.webproject.board.DAO.UserDao;
import yun.fast.webproject.board.DAO.UserDaoImpl;
import yun.fast.webproject.board.DTO.User;
import yun.fast.webproject.board.Util.ConnectionContextHolder;
import yun.fast.webproject.board.Util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-17
 * Github : https://github.com/YeoHoonYun
 */
public class UserServiceImpl implements UserService {
    private static UserService instance = new UserServiceImpl();
    private UserServiceImpl(){}
    public static UserService getInstance(){
        return instance;
    }

    @Override
    public void addUser(User user) {
        Connection conn = DBConnector.getConnection();
        UserDao userDao = UserDaoImpl.getInstance();
        PreparedStatement ps = null;
        try{
            ConnectionContextHolder.setConnection(conn);
            userDao.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBConnector.close(conn);
        }
    }

    @Override
    public String getPasswdByEmail(String email) {
        String passwd = null;
        UserDao userDao = UserDaoImpl.getInstance();
        try(Connection conn = DBConnector.getInstance().getConnection();) {
            ConnectionContextHolder.setConnection(conn);
            passwd = userDao.getPasswdByEmail(email);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return passwd;
    }

    @Override
    public User getPasswdByUser(String email) {
        User user = null;
        UserDao userDao = UserDaoImpl.getInstance();
        try(Connection conn = DBConnector.getInstance().getConnection();) {
            ConnectionContextHolder.setConnection(conn);
            user = userDao.getPasswdByUser(email);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
}
