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

@WebServlet(urlPatterns = {"/changePassword"})
public class ChangePasswordServlet extends HttpServlet {

    private static final String CHANGE_PASSWORD_PAGE = "pages/changePasswordPage.jsp";
    private static final String NEW_PASSWORD = "newpass";
    private static final String CURRENT_PASSWORD = "currentpass";
    private static final String MESSAGE = "message";
    private static final String SUCCESS_MESSAGE = "Password changed successfully!";
    private static final String ERROR_MESSAGE = "Incorrect current password!";
    private static UserService userService;

    @Override
    public void init(ServletConfig servletConfig) {
        userService = (UserService) servletConfig.getServletContext().getAttribute(Constant.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(CHANGE_PASSWORD_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String password = httpServletRequest.getParameter(CURRENT_PASSWORD);
        String newPassword = httpServletRequest.getParameter(NEW_PASSWORD);
        String message = null;
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        if (user.getPassword().equals(password)) {
            user.setPassword(newPassword);
            userService.updateUserPassword(user);
            message = SUCCESS_MESSAGE;
        } else {
            message = ERROR_MESSAGE;
        }
        httpServletRequest.setAttribute(MESSAGE, message);
        httpServletRequest.getRequestDispatcher(CHANGE_PASSWORD_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
