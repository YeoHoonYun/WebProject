package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DTO.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
public class BoardDAO implements BoardDAOImpl{
    @Override
    public List<Board> selectLists() {
        List<Board> boards = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select b.id as id, b.title as title, b.user_id as userId, b.content as content, b.regdate as regdate " +
                     "from board b, user u " +
                     "where b.user_id = u.user_id order by b.id";
        try {
            conn = DBConnector.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong(1);
                String title = rs.getString(2);
                String writer = rs.getString(3);
                String content = rs.getString(4);
                Date regdate = rs.getDate(5);

                Board board = new Board(id,title,writer,content,regdate);
                boards.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boards;
    }

    @Override
    public Board selectOneBoard(Long id) {
        Board board = new Board();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select b.id as id, b.title as title, b.user_id as userId, b.content as content, b.regdate as regdate " +
                "from board b, user u " +
                "where b.user_id = u.user_id " +
                "and b.id = ? " +
                "order by b.id";
        try {
            conn = DBConnector.connect();
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();

            if(rs.next()){
                Long num = rs.getLong(1);
                String title = rs.getString(2);
                String writer = rs.getString(3);
                String content = rs.getString(4);
                Date regdate = rs.getDate(5);

                board = new Board(num,title,writer,content,regdate);
            }else {
                System.out.println("값이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return board;
    }

    @Override
    public void insertBoard(String title, String userId, String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into board(title,user_id,content, groupno, grpord,depth) select ?,?, ? test8,MAX(groupno)+1,0,1 from board";
        try {
            conn = DBConnector.connect();
            ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2, userId);
            ps.setString(3, content);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
