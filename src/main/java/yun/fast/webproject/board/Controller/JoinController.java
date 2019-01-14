package yun.fast.webproject.board.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "joincontroller", urlPatterns = "/join/*")
public class JoinController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().equals("/")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
