package yun.fast.webproject.board.Controller;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import yun.fast.webproject.board.DTO.User;
import yun.fast.webproject.board.Service.UserServiceImpl;
import yun.fast.webproject.board.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-14
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "joincontroller", urlPatterns = "/join/")
public class JoinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");
        String passwd1 = req.getParameter("passwd1");
        String passwd2 = req.getParameter("passwd2");

        if(name != null && name.length() < 2){
            System.out.println("값이 없는 듯?");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
            requestDispatcher.forward(req,resp);
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = passwordEncoder.encode(passwd1);
        if(!passwordEncoder.matches(passwd2, encode)){
            System.out.println("비번 틀린듯?");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
            requestDispatcher.forward(req,resp);
        }

        User user = new User(name, nickname, email, encode);
        UserService userService = UserServiceImpl.getInstance();
        userService.addUser(user);

        resp.sendRedirect("/login");
    }
}
