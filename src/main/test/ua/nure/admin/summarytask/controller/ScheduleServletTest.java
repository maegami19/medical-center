package ua.nure.admin.summarytask.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.Schedule;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.ScheduleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServletTest {

    private static final String SELECT_SCHEDULE_PAGE = "pages/selectSchedulePage.jsp";
    private static final String DATE_TIME_FOR_SCHEDULE = "datetime";
    private String testId = "0";
    private String testDateTime = "21/01/2019T18:30";
    Schedule schedule;
    private ScheduleServlet servlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private DoctorService doctorService;
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private ServletContext context;

    @Before
    public void setUp() throws Exception {
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(SELECT_SCHEDULE_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.DOCTOR_SERVICE)).thenReturn(doctorService);
        Mockito.when(context.getAttribute(Constant.SCHEDULE_SERVICE)).thenReturn(scheduleService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter(DATE_TIME_FOR_SCHEDULE)).thenReturn(testDateTime);
        Mockito.when(request.getParameter(Constant.DOCTOR)).thenReturn(testId);
        schedule = new Schedule();
        schedule.setDatetime(testDateTime);
        schedule.setDoctorId(Integer.parseInt(testId));
        servlet = new ScheduleServlet();
        servlet.init(servletConfig);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.doGet(request, response);
        Mockito.verify(doctorService, Mockito.times(1)).getDoctorsByCategory();
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        servlet.doPost(request, response);
        Mockito.verify(scheduleService, Mockito.times(1)).addSchedule(schedule);
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }
}