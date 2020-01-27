package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.ScheduleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showSchedule"})
public class ShowSchedulesServlet extends HttpServlet {

    private static final String SCHEDULES_BY_DOCTOR_ID = "schedules";
    private static final String SHOW_SCHEDULE_PAGE = "pages/showSchedulePage.jsp";
    private static ScheduleService scheduleService;
    private static DoctorService doctorService;

    @Override
    public void init(ServletConfig servletConfig) {
        scheduleService = (ScheduleService) servletConfig.getServletContext().getAttribute(Constant.SCHEDULE_SERVICE);
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        httpServletRequest.setAttribute(SCHEDULES_BY_DOCTOR_ID, scheduleService.getSchedulesByDoctorIdAndStatus(doctorService.getDoctorId(user.getUsername())));
        httpServletRequest.getRequestDispatcher(SHOW_SCHEDULE_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
