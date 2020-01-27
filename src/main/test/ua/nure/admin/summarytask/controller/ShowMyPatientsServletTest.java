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
import ua.nure.admin.summarytask.service.PatientService;

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
public class ShowMyPatientsServletTest {

    private static final String SHOW_PATIENTS_PAGE = "pages/showPatientsPage.jsp";
    private ShowMyPatientsServlet servlet;
    private User user;
    private String testUsername = "testUsername";
    private String testId = "0";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private PatientService patientService;
    @Mock
    private DoctorService doctorService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private ServletContext context;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername(testUsername);
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(SHOW_PATIENTS_PAGE)).thenReturn(dispatcher);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute(Constant.USER)).thenReturn(user);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(context.getAttribute(Constant.PATIENT_SERVICE)).thenReturn(patientService);
        Mockito.when(context.getAttribute(Constant.DOCTOR_SERVICE)).thenReturn(doctorService);
        servlet = new ShowMyPatientsServlet();
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.init(servletConfig);
        servlet.doGet(request, response);
        Mockito.verify(doctorService, Mockito.times(1)).getDoctorId(user.getUsername());
        Mockito.verify(patientService, Mockito.times(1)).getPatientByDoctorId(Integer.parseInt(testId));
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }
}