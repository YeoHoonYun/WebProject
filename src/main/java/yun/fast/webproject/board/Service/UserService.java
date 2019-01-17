package yun.fast.webproject.board.Service;

import yun.fast.webproject.board.DTO.User;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-16
 * Github : https://github.com/YeoHoonYun
 */
public interface UserService {
    void addUser(User user);
    String getPasswdByEmail(String email);

    User getPasswdByUser(String email);
}
