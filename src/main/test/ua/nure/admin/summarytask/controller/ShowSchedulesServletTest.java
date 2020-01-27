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
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.ScheduleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowSchedulesServletTest {

    private static final String SHOW_SCHEDULE_PAGE = "pages/showSchedulePage.jsp";
    private User sessionUser;
    private String testUsername = "testUsername";
    private String testId = "1";
    private ShowSchedulesServlet servlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private DoctorService doctorService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private ServletContext context;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        sessionUser = new User();
        sessionUser.setUsername(testUsername);
        Mockito.when(request.getRequestDispatcher(SHOW_SCHEDULE_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.SCHEDULE_SERVICE)).thenReturn(scheduleService);
        Mockito.when(context.getAttribute(Constant.DOCTOR_SERVICE)).thenReturn(doctorService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute(Constant.USER)).thenReturn(sessionUser);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(doctorService.getDoctorId(sessionUser.getUsername())).thenReturn(Integer.parseInt(testId));
        servlet = new ShowSchedulesServlet();
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.init(servletConfig);
        servlet.doGet(request, response);
        Assert.assertEquals(sessionUser.getUsername(), testUsername);
        Mockito.verify(doctorService, Mockito.times(1)).getDoctorId(sessionUser.getUsername());
        Mockito.verify(scheduleService, Mockito.times(1)).getSchedulesByDoctorIdAndStatus(Integer.parseInt(testId));
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }
}