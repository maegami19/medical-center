package ua.nure.admin.summarytask.controller.registration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.controller.registration.command.RegisterServletCommand;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.MailService;
import ua.nure.admin.summarytask.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServletTest {

    private static final String REGISTRATION_PAGE = "pages/registrationPage.jsp";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String MESSAGE = "User added successfully";
    private RegisterServlet servlet;
    private User user;
    private String testUsername = "testUsername";
    private String testPassword = "testPassword";
    private String testEmail = "testEmail";
    private String testRole = "admin";
    private String testAnswer = "testAnswer";
    @Mock
    private PrintWriter writer;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserService userService;
    @Mock
    private MailService mailService;
    @Mock
    private DoctorService doctorService;
    @Mock
    private RegisterServletCommand registerServletCommand;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ServletContext context;
    @Mock
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() throws Exception {
        user = fillUser();
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(REGISTRATION_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.USER_SERVICE)).thenReturn(userService);
        Mockito.when(context.getAttribute(Constant.MAIL_SERVICE)).thenReturn(mailService);
        Mockito.when(context.getAttribute(Constant.DOCTOR_SERVICE)).thenReturn(doctorService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter(ROLE)).thenReturn(testRole);
        Mockito.when(request.getParameter(EMAIL)).thenReturn(testEmail);
        Mockito.when(request.getParameter(USERNAME)).thenReturn(testUsername);
        Mockito.when(userService.addUser(user)).thenReturn(testAnswer);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet = new RegisterServlet();
        servlet.init(servletConfig);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.doGet(request, response);
        Mockito.verify(doctorService, Mockito.times(1)).getCategories();
        Mockito.verify(doctorService, Mockito.times(1)).getAllDoctors();
        Mockito.verify(request, Mockito.times(2)).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void doPostTestWhenRoleAdmin() throws IOException, ServletException {
        servlet.doPost(request, response);
        final String actualAnswer = userService.addUser(user);
        Assert.assertEquals(actualAnswer, testAnswer);
    }

    private User fillUser() {
        User user = new User();
        user.setUsername(testUsername);
        user.setRole(testRole);
        user.setPassword(testPassword);
        user.setEmail(testEmail);
        return user;
    }
}