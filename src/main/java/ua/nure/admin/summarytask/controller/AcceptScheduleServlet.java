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

@WebServlet(urlPatterns = {"/acceptSchedule"})
public class AcceptScheduleServlet extends HttpServlet {

    private static ScheduleService scheduleService;
    private static DoctorService doctorService;
    private static final String ACCEPT_SCHEDULE_PAGE = "pages/acceptSchedulePage.jsp";
    private static final String SCHEDULES = "schedules";
    private static final String MESSAGE = "message";
    private static final String SUCCESS_MESSAGE = "Schedule updated successfully!";

    @Override
    public void init(ServletConfig servletConfig) {
        scheduleService = (ScheduleService) servletConfig.getServletContext().getAttribute(Constant.SCHEDULE_SERVICE);
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER);
        httpServletRequest.setAttribute(SCHEDULES, scheduleService.getSchedulesByDoctorId(doctorService.getDoctorId(user.getUsername())));
        httpServletRequest.getRequestDispatcher(ACCEPT_SCHEDULE_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(httpServletRequest.getParameter(SCHEDULES));
        scheduleService.updateScheduleStatus(scheduleId);
        httpServletRequest.setAttribute(MESSAGE, SUCCESS_MESSAGE);
        doGet(httpServletRequest, httpServletResponse);
    }
}
