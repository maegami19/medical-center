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

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class UsersServletTest {

    private static final String SHOW_USERS_PAGE = "pages/showUsersPage.jsp";
    private static final String DELETE_PARAMETER = "delete";
    private static final String SHOW_USERS_PAGE_URL = "/users";
    private String testUsername = "testUsername";
    private String testRole = "testRole";
    private UsersServlet servlet;
    private User user;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserService userService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private ServletContext context;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setRole(testRole);
        user.setUsername(testUsername);
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(SHOW_USERS_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.USER_SERVICE)).thenReturn(userService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter(DELETE_PARAMETER)).thenReturn(testUsername);
        Mockito.when(userService.checkUser(testUsername)).thenReturn(user);
        servlet = new UsersServlet();
        servlet.init(servletConfig);
    }

    @Test
    public void doPostTest() throws IOException {
        servlet.doPost(request, response);
        final User userActual = userService.checkUser(testUsername);
        Assert.assertEquals(userActual.getUsername(), testUsername);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(testUsername, userActual.getRole());
        Mockito.verify(response).sendRedirect(SHOW_USERS_PAGE_URL);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.doGet(request, response);
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }
}