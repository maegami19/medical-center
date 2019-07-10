package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.repository.UserRepository;
import ua.nure.admin.summarytask.repository.impl.UserRepositoryImpl;
import ua.nure.admin.summarytask.service.UserService;
import ua.nure.admin.summarytask.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("pages/login.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        UserService userService = (UserService) httpServletRequest.getServletContext().getAttribute("userService");
        HttpSession session = httpServletRequest.getSession();
        List<User> users = userService.getAllUsers();
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        for (User u : users) {
            if (user.equals(u)) {
                user.setRole(u.getRole());
                user.setId(u.getId());
                session.setAttribute("user", user);
                httpServletResponse.sendRedirect("/");
                break;
            }
        }
        if (session.getAttribute("user") == null) {
            httpServletRequest.setAttribute("reply", "*Incorrect username or password");
            httpServletRequest.getRequestDispatcher("pages/login.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}