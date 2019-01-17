package yun.fast.webproject.board.DAO;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-17
 * Github : https://github.com/YeoHoonYun
 */
public class BoardDaoSQL {
    public static final String SELECT_BY_ID =
            "select b.id as id, b.title as title, u.user_id as userId, b.content as content, b.regdate as regdate, b.read_count as readCount, b.groupno as groupno, b.grpord as grpord, b.depth as depth " +
                    "from board b, user u " +
                    "where b.user_id = u.user_id " +
                    "and b.id = ? ";
    public static final String SELECT_BY_PAGING =
            "select b.id as id, b.title as title, u.user_id as userId, b.content as content, b.regdate as regdate, b.read_count as readCount, b.groupno as groupno, b.grpord as grpord, b.depth as depth " +
                    "from board b, user u " +
                    "where b.user_id = u.user_id " +
                    "order by b.groupno desc, b.grpord";
    public static final String INSERT =
            "insert into board(title,user_id,content, groupno, grpord,depth) select ?,?, ?,IFNULL(MAX(groupno)+1,1),0,1 from board";
    public static final String UPDATE =
            "UPDATE board SET title = ?, content = ? WHERE id = ?";
    public static final String DELETE =
            "delete from board where id = ?";
    public static final String UPDATE_COUNT =
            "UPDATE board SET read_count = read_count + 1 WHERE id = ?";
    public static final String UPDATE_GRPORD =
            "UPDATE board SET grpord = grpord + 1 where groupno = ? and grpord >= ? + 1";
    public static final String INSERT_GROUP =
            "insert into board (title, user_id, content, groupno, grpord, depth) values (?, ?, ?, ?,? + 1, ? + 1)";
}