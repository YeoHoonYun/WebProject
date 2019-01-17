package yun.fast.webproject.board.DAO;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-17
 * Github : https://github.com/YeoHoonYun
 */
public class UserDAOSQL {
    public static final String INSERT = "INSERT INTO user2 (name, nickname, email, passwd) VALUES (?, ?, ?, ?)";
    public static final String SELECT_BY_EMAIL = "SELECT passwd FROM user2 WHERE email = ?";
    public static final String SELECT_BY_USER = "SELECT id, name, nickname, email, passwd FROM user2 WHERE email = ?";
}