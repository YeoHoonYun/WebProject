package yun.fast.webproject.board.Controller;

import org.apache.commons.collections4.ListUtils;
import yun.fast.webproject.board.DAO.BoardDAO;
import yun.fast.webproject.board.DTO.Board;
import yun.fast.webproject.board.DTO.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "BoardController", urlPatterns = "/board/*")
public class BoardController extends HttpServlet {
    private static final int PAGESIZE = 5;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");

        if(req.getPathInfo().contains("/insert")){
            String title = getPa(req, "title");
            int id = (Integer) session.getAttribute("id");
            String userId = user.getName();
            String content = getPa(req, "content");
            BoardDAO boardDAO = new BoardDAO();
            boardDAO.insertBoard(title,id,userId, content);
            resp.sendRedirect("/board/main");

        }else if(req.getPathInfo().contains("/detail")){
            BoardDAO boardDAO = new BoardDAO();
            Board board = boardDAO.selectOneBoard(longGetPa(req, "id"));
            boardDAO.selectCount(longGetPa(req, "id"));
            req.setAttribute("board", board);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
            requestDispatcher.forward(req,resp);

        }else if(req.getPathInfo().contains("/update")){
            BoardDAO boardDAO = new BoardDAO();
            if(req.getQueryString()!= null){
                boardDAO.updateBoard(longGetPa(req, "id"), getPa(req, "title"), getPa(req, "content"));
                resp.sendRedirect("/board/main");
            }
            else{
                Board board = boardDAO.selectOneBoard(Long.parseLong(req.getPathInfo().substring(8)));
                req.setAttribute("board", board);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/update.jsp");
                requestDispatcher.forward(req,resp);
            }
        }else if(req.getPathInfo().contains("/delete")){
            BoardDAO boardDAO = new BoardDAO();
            boardDAO.deleteBoard(Long.parseLong(req.getParameter("id")));
            resp.sendRedirect("/board/main");

        }else if(req.getPathInfo().contains("/main")){
            int page = 1;
            BoardDAO boardDAO = new BoardDAO();
            List<List<Board>> boards = ListUtils.partition(boardDAO.selectLists(),PAGESIZE);
            if(req.getParameter("p") != null){
                page = Integer.parseInt(req.getParameter("p"));
            }
            req.setAttribute("size", boards.size());
            try {
                req.setAttribute("list", boards.get(page - 1));
            }catch (Exception e){
                System.out.println("패스");
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
            requestDispatcher.forward(req,resp);

        }else if(req.getPathInfo().contains("/write")){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/write.jsp");
            requestDispatcher.forward(req,resp);

        }else if(req.getPathInfo().contains("/addWrite")){
            BoardDAO boardDAO = new BoardDAO();
            if((req.getParameter("groupno") == null)) {
                Board board = boardDAO.selectOneBoard(longGetPa(req, "id"));
                req.setAttribute("board", board);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/addWrite.jsp");
                requestDispatcher.forward(req, resp);
            }
            else{
                boardDAO.updateGrp(intGetPa(req, "groupno"), intGetPa(req, "grpord"));
                boardDAO.createGrp(getPa(req, "title"), user.getId(), user.getName(), getPa(req, "content"), intGetPa(req, "groupno"),intGetPa(req, "grpord"),intGetPa(req, "depth"));
                resp.sendRedirect("/board/main");
            }
        }
    }
    String getPa(HttpServletRequest req, String s){
        return req.getParameter(s);
    }
    int intGetPa(HttpServletRequest req, String s){
        return Integer.parseInt(req.getParameter(s));
    }
    Long longGetPa(HttpServletRequest req, String s){
        return Long.parseLong(req.getParameter(s));
    }
}
