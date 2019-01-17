package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DTO.User;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-17
 * Github : https://github.com/YeoHoonYun
 */
public interface UserDao {
    void addUser(User user);

    String getPasswdByEmail(String email);

    User getPasswdByUser(String email);
}
