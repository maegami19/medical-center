package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.CaptchaService;
import ua.nure.admin.summarytask.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOGIN_PAGE = "pages/loginPage.jsp";
    private static final String ERROR = "errorString";
    private static final String ERROR_MESSAGE_USER = "Incorrect username or password";
    private static final String ERROR_MESSAGE_CAPTCHA = "Captcha invalid!";
    private static final String RECAPTCHA_RESPONSE = "g-recaptcha-response";
    private static UserService userService;
    private static CaptchaService captchaService;

    @Override
    public void init(ServletConfig servletConfig) {
        userService = (UserService) servletConfig.getServletContext().getAttribute(Constant.USER_SERVICE);
        captchaService = (CaptchaService) servletConfig.getServletContext().getAttribute(Constant.CAPTCHA_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(LOGIN_PAGE);
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter(USERNAME);
        String password = httpServletRequest.getParameter(PASSWORD);
        boolean valid = true;
        String errorString = null;
        User user = userService.checkUser(username, password);
        if (user == null) {
            valid = false;
            errorString = ERROR_MESSAGE_USER;
        }

        if (valid) {
            valid = captchaService.verify(httpServletRequest.getParameter(RECAPTCHA_RESPONSE));
            if (!valid) {
                errorString = ERROR_MESSAGE_CAPTCHA;
            }
        }

        if (!valid) {
            httpServletRequest.setAttribute(ERROR, errorString);
            httpServletRequest.getRequestDispatcher(LOGIN_PAGE).forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.getSession().setAttribute(Constant.USER, user);
            httpServletResponse.sendRedirect("/");
        }
    }
}