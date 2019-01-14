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
    void insertBoard(String title, String userId, String passwd);

}
