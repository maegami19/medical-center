package ua.nure.admin.summarytask.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowPatientsByBirthdayServletTest {

    private static final String SHOW_PATIENTS_PAGE = "pages/showPatientsPage.jsp";
    private ShowPatientsByBirthdayServlet servlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PatientService patientService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private ServletContext context;

    @Before
    public void setUp() throws Exception {
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(SHOW_PATIENTS_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.PATIENT_SERVICE)).thenReturn(patientService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        servlet = new ShowPatientsByBirthdayServlet();
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.init(servletConfig);
        servlet.doGet(request, response);
        Mockito.verify(patientService, Mockito.times(1)).getPatientsByBirthday();
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }
}