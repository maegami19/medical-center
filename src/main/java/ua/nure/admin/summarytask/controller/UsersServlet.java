package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {

    private static final String ALL_USERS = "list_users";
    private static final String SHOW_USERS_PAGE = "pages/showUsersPage.jsp";
    private static final String DELETE_PARAMETER = "delete";
    private static final String SHOW_USERS_PAGE_URL = "/users";
    private static UserService userService;

    @Override
    public void init(ServletConfig servletConfig) {
        userService = (UserService) servletConfig.getServletContext().getAttribute(Constant.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute(ALL_USERS, userService.getAllUsers());
        httpServletRequest.getRequestDispatcher(SHOW_USERS_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String delete = httpServletRequest.getParameter(DELETE_PARAMETER);
        if (delete != null) {
            User user = userService.checkUser(delete);
            userService.deleteUser(delete, user.getRole());
            httpServletResponse.sendRedirect(SHOW_USERS_PAGE_URL);
        }
    }
}
