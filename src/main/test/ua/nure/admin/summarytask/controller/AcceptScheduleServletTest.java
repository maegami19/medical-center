package ua.nure.admin.summarytask.controller;

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
public class AcceptScheduleServletTest {

    private static final String ACCEPT_SCHEDULE_PAGE = "pages/acceptSchedulePage.jsp";
    private static final String SCHEDULES = "schedules";
    private String testUsername = "testUsername";
    private String testId = "0";
    private User user;
    private AcceptScheduleServlet servlet;
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
        user = new User();
        user.setUsername(testUsername);
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(ACCEPT_SCHEDULE_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.SCHEDULE_SERVICE)).thenReturn(scheduleService);
        Mockito.when(context.getAttribute(Constant.DOCTOR_SERVICE)).thenReturn(doctorService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute(Constant.USER)).thenReturn(user);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter(SCHEDULES)).thenReturn(testId);
        servlet = new AcceptScheduleServlet();
        servlet.init(servletConfig);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.doGet(request, response);
        Mockito.verify(doctorService, Mockito.times(1)).getDoctorId(user.getUsername());
        Mockito.verify(scheduleService, Mockito.times(1)).getSchedulesByDoctorId(Integer.parseInt(testId));
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        servlet.doPost(request, response);
        Mockito.verify(scheduleService, Mockito.times(1)).updateScheduleStatus(Integer.parseInt(testId));
        Mockito.verify(request, Mockito.times(2)).setAttribute(Mockito.anyString(), Mockito.any());
    }
}