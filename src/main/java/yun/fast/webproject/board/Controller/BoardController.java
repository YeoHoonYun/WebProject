package yun.fast.webproject.board.Controller;

import com.mysql.cj.Session;
import yun.fast.webproject.board.DAO.BoardDAO;
import yun.fast.webproject.board.DTO.Board;

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
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        BoardDAO boardDAO = new BoardDAO();

        HttpSession session = req.getSession();
        session.setAttribute("userId","yun");

        if(req.getPathInfo().contains("/insert")){
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String userId = (String) session.getAttribute("userId");
            System.out.println(userId);
            boardDAO.insertBoard(title, userId, content);
            resp.sendRedirect("/board/main");

        }else if(req.getPathInfo().contains("/detail")){
            Board board = boardDAO.selectOneBoard(Long.parseLong(req.getParameter("id")));
            req.setAttribute("board", board);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
            requestDispatcher.forward(req,resp);

        }else if(req.getPathInfo().contains("/update")){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/update.jsp");
            requestDispatcher.forward(req,resp);

        }else if(req.getPathInfo().contains("/delete")){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
            requestDispatcher.forward(req,resp);

        }else if(req.getPathInfo().contains("/main")){
            List<Board> boards = boardDAO.selectLists();
            req.setAttribute("list", boards);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
            requestDispatcher.forward(req,resp);

        }else if(req.getPathInfo().contains("/write")){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/write.jsp");
            requestDispatcher.forward(req,resp);
        }
    }
}
