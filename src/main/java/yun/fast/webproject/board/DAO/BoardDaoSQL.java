package yun.fast.webproject.board.DAO;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-17
 * Github : https://github.com/YeoHoonYun
 */
public class BoardDaoSQL {
    public static final String SELECT_BY_ID =
//            "select b.id as id, b.title as title, u.user_id as userId, b.content as content, b.regdate as regdate, b.read_count as readCount, b.groupno as groupno, b.grpord as grpord, b.depth as depth " +
//                    "from board b, user u " +
//                    "where b.user_id = u.user_id " +
//                    "and b.id = ? ";
            "select id, title, nickname, content, regdate, read_count, group_no, group_seq, group_depth " +
                    "from board2 " +
                    "where id = ? " +
                    "order by group_no desc, group_seq";
    public static final String SELECT_BY_PAGING =
//            "select b.id as id, b.title as title, u.user_id as userId, b.content as content, b.regdate as regdate, b.read_count as readCount, b.groupno as groupno, b.grpord as grpord, b.depth as depth " +
//                    "from board b, user u " +
//                    "where b.user_id = u.user_id " +
//                    "order by b.groupno desc, b.grpord";
            "select id, title, nickname, content, regdate, read_count, group_no, group_seq, group_depth " +
                    "from board2 " +
                    "order by group_no desc, group_seq";
    public static final String INSERT =
//            "insert into board(title,user_id,content, groupno, grpord,depth) select ?,?, ?,IFNULL(MAX(groupno)+1,1),0,1 from board";
            "insert into board2(title,user_id,nickname,content,group_no                ,group_seq,group_depth) " +
            "            select ?    ,      ?,       ?,      ?,IFNULL(MAX(groupno)+1,1),0        ,1 from board";
    public static final String UPDATE =
//            "UPDATE board SET title = ?, content = ? WHERE id = ?";
            "UPDATE board2 SET title = ?, content = ? WHERE id = ?";
    public static final String DELETE =
            "delete from board2 where id = ?";
    public static final String UPDATE_COUNT =
            "UPDATE board2 SET read_count = read_count + 1 WHERE id = ?";
    public static final String UPDATE_GRPORD =
            "UPDATE board2 SET group_seq = group_seq + 1 where group_no = ? and group_seq >= ? + 1";
    public static final String INSERT_GROUP =
            "insert into board2 (title,user_id,nickname,content,group_no,group_seq,group_depth) " +
                    "    values (?    ,?      , ?     , ?      , ?      ,? + 1    ,? + 1)";
}