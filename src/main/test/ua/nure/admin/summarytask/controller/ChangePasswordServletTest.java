package ua.nure.admin.summarytask.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordServletTest {

    private static final String NEW_PASSWORD = "newpass";
    private static final String CURRENT_PASSWORD = "currentpass";
    private static final String CHANGE_PASSWORD_PAGE = "pages/changePasswordPage.jsp";

    private String testPassword = "testpass";
    private String testNewPassword = "brandnewPAssword";
    private User sessionUser = getTestUser();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ServletContext context;
    @Mock
    private RequestDispatcher dispatcher;

    private ChangePasswordServlet servlet;

    @Before
    public void setUp() throws Exception {
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(CHANGE_PASSWORD_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.USER_SERVICE)).thenReturn(userService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute(Constant.USER)).thenReturn(sessionUser);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter(NEW_PASSWORD)).thenReturn(testNewPassword);
        Mockito.when(request.getParameter(CURRENT_PASSWORD)).thenReturn(testPassword);
        servlet = new ChangePasswordServlet();
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        servlet.init(servletConfig);
        servlet.doPost(request, response);
        Assert.assertEquals(sessionUser.getPassword(), testNewPassword);
        Mockito.verify(userService, Mockito.times(1)).updateUserPassword(sessionUser);
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }

    private User getTestUser() {
        User user = new User();
        user.setPassword(testPassword);
        return user;
    }
}