package yun.fast.webproject.board.DTO;

import java.util.Date;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-17
 * Github : https://github.com/YeoHoonYun
 */
public class User {
    Long id;
    String name;
    String nickname;
    String email;
    String passwd;
    Date regdate;

    public User(String name, String nickname, String email, String passwd) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.passwd = passwd;
    }
    public User(Long id, String name, String nickname, String email, String passwd) {
        this(name,nickname,email,passwd);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
