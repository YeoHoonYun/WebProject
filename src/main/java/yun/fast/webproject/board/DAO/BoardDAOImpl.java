package yun.fast.webproject.board.DAO;

import yun.fast.webproject.board.DTO.Board;

import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
public interface BoardDAOImpl {
    List<Board> selectLists();
    Board selectOneBoard(Long id);
    void insertBoard(String title, int id, String userId, String content);
    void deleteBoard(Long id);
    void updateBoard(Long id, String title, String content);
    void selectCount(Long id);
//    boolean checkgrpord(int groupno, int grpord);
    void createGrp(String title, Long id, String userId, String content, int groupno, int grpord, int depth);
//    void existCreategrp(Long id, String title, String userId, String content,int groupno, int grpord, int depth);
    void updateGrp(int groupno, int grpord);

}
