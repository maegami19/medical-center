package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.Schedule;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.ScheduleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/schedule"})
public class ScheduleServlet extends HttpServlet {

    private static final String SELECT_SCHEDULE_PAGE = "pages/selectSchedulePage.jsp";
    private static final String ALL_DOCTORS = "doctors";
    private static final String MESSAGE = "message";
    private static final String SUCCESS_MESSAGE = "Schedule added successfully!";
    private static final String DATE_TIME_FOR_SCHEDULE = "datetime";
    private static DoctorService doctorService;
    private static ScheduleService scheduleService;

    @Override
    public void init(ServletConfig servletConfig) {
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
        scheduleService = (ScheduleService) servletConfig.getServletContext().getAttribute(Constant.SCHEDULE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute(ALL_DOCTORS, doctorService.getDoctorsByCategory());
        httpServletRequest.getRequestDispatcher(SELECT_SCHEDULE_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String datetime = httpServletRequest.getParameter(DATE_TIME_FOR_SCHEDULE);
        int doctorId = Integer.parseInt(httpServletRequest.getParameter(Constant.DOCTOR));

        Schedule schedule = new Schedule();
        schedule.setDatetime(datetime);
        schedule.setDoctorId(doctorId);

        scheduleService.addSchedule(schedule);
        httpServletRequest.setAttribute(MESSAGE, SUCCESS_MESSAGE);
        doGet(httpServletRequest, httpServletResponse);
    }
}
