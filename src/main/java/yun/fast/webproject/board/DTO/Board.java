package yun.fast.webproject.board.DTO;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
public class Board {
    private Long id;
    private String title;
    private String userId;
    private String content;
    private Date regdate;
    private int readCount;
    private int groupno;
    private int grpord;
    private int depth;

    public Board(Long id, String title, String userId, String content, Date regdate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regdate = regdate;
    }

    public Board(Long num, String title, String writer, String content, Date regdate, int readCount) {
        this(num, title, writer, content, regdate);
        this.readCount = readCount;
    }

    public Board(Long num, String title, String writer, String content, Date regdate, int readCount, int groupno, int grpord, int depth) {
        this(num, title, writer, content, regdate, readCount);
        this.groupno = groupno;
        this.grpord = grpord;
        this.depth = depth;
    }

    public Board() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getGroupno() {
        return groupno;
    }

    public void setGroupno(int groupno) {
        this.groupno = groupno;
    }

    public int getGrpord() {
        return grpord;
    }

    public void setGrpord(int grpord) {
        this.grpord = grpord;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
