package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DTO.Board;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "select b.id as id, b.title as title, u.user_id as userId, b.content as content, b.regdate as regdate, b.read_count as readCount, b.groupno as groupno, b.grpord as grpord, b.depth as depth " +
                "from board b, user u " +
                "where b.user_id = u.user_id " +
                "order by b.groupno desc, b.grpord";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong(1);
                String title = rs.getString(2);
                String writer = rs.getString(3);
                String content = rs.getString(4);
                Date regdate = rs.getDate(5);
                int readCount = rs.getInt(6);
                int groupno = rs.getInt(7);
                int grpord = rs.getInt(8);
                int depth = rs.getInt(9);
                title = new String(new char[depth-1]).replace("\0","re) ") + title;

                Board board = new Board(id,title,writer,content,regdate,readCount,groupno,grpord,depth);
                boards.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
        return boards;
    }

    @Override
    public Board selectOneBoard(Long id) {
        Board board = new Board();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select b.id as id, b.title as title, u.user_id as userId, b.content as content, b.regdate as regdate, b.read_count as readCount, b.groupno as groupno, b.grpord as grpord, b.depth as depth " +
                "from board b, user u " +
                "where b.user_id = u.user_id " +
                "and b.id = ? ";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();

            if(rs.next()){
                Long num = rs.getLong(1);
                String title = rs.getString(2);
                String writer = rs.getString(3);
                String content = rs.getString(4);
                Timestamp regdate = rs.getTimestamp(5);
                int readCount = rs.getInt(6);
                int groupno = rs.getInt(7);
                int grpord = rs.getInt(8);
                int depth = rs.getInt(9);

                board = new Board(num,title,writer,content,regdate,readCount,groupno,grpord,depth);
            }else {
                System.out.println("값이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
        return board;
    }

    @Override
    public void insertBoard(String title, String userId, String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into board(title,user_id,content, groupno, grpord,depth) select ?,?, ?,IFNULL(MAX(groupno)+1,1),0,1 from board";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2, userId);
            ps.setString(3, content);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
    }

    @Override
    public void deleteBoard(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from board where id = ?";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
    }

    @Override
    public void updateBoard(Long id, String title, String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2, content);
            ps.setLong(3,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
    }
    @Override
    public void selectCount(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE board SET read_count = read_count + 1 WHERE id = ?";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
    }
    @Override
    public void creategrp(String title, String userId, String content,int groupno, int grpord, int depth) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into board (title, user_id, content, groupno, grpord, depth)" +
                "values (?, ?, ?, ?,? + 1, ? + 1)";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2, userId);
            ps.setString(3, content);
            ps.setInt(4, groupno);
            ps.setInt(5, grpord);
            ps.setInt(6, depth);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
    }
    public void updategrp(int groupno, int grpord) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE board SET grpord = grpord + 1 where groupno = ? and grpord >= ? + 1";
        try {
            conn = DBConnector.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1,groupno);
            ps.setLong(2,grpord);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnector.close(ps,conn);
        }
    }
}
