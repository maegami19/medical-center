package ua.nure.admin.summarytask.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.MedcardService;
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
public class OpenMedcardServletTest {

    private static final String OPEN_MEDCARD_PAGE = "pages/openMedcardPage.jsp";
    private static final String MEDCARD_TYPE = "type";
    private static final String MEDCARD_TO_PATIENT = "to_patient";
    private static final String MEDCARD_DESCRIPTION = "description";
    private OpenMedcardServlet servlet;
    private String testUsername = "testUsername";
    private String testId = "0";
    private String testToPatient = "0";
    private String testType = "testType";
    private String testDescription = "testDescription";
    private String testStatus = "active";
    private User user;
    private Medcard medcard;
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
    private MedcardService medcardService;
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
        Mockito.when(request.getRequestDispatcher(OPEN_MEDCARD_PAGE)).thenReturn(dispatcher);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute(Constant.USER)).thenReturn(user);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(context.getAttribute(Constant.PATIENT_SERVICE)).thenReturn(patientService);
        Mockito.when(context.getAttribute(Constant.DOCTOR_SERVICE)).thenReturn(doctorService);
        Mockito.when(context.getAttribute(Constant.MEDCARD_SERVICE)).thenReturn(medcardService);
        Mockito.when(request.getParameter(MEDCARD_TYPE)).thenReturn(testType);
        Mockito.when(request.getParameter(MEDCARD_TO_PATIENT)).thenReturn(testToPatient);
        Mockito.when(request.getParameter(MEDCARD_DESCRIPTION)).thenReturn(testDescription);
        servlet = new OpenMedcardServlet();
        medcard = fillMedcard();
        servlet.init(servletConfig);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.doGet(request, response);
        Mockito.verify(doctorService, Mockito.times(1)).getDoctorId(user.getUsername());
        Mockito.verify(patientService, Mockito.times(1)).getPatientByDoctorId(Integer.parseInt(testId));
        Mockito.verify(patientService, Mockito.times(1)).getPatientsByAlphabet();
        Mockito.verify(request, Mockito.times(2)).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        servlet.doPost(request, response);
        Mockito.verify(medcardService, Mockito.times(1)).addNote(medcard);
    }

    private Medcard fillMedcard() {
        medcard = new Medcard();
        medcard.setFromMedic(user.getUsername());
        medcard.setToPatient(Integer.parseInt(testToPatient));
        medcard.setType(testType);
        medcard.setDescription(testDescription);
        medcard.setStatus(testStatus);
        return medcard;
    }
}