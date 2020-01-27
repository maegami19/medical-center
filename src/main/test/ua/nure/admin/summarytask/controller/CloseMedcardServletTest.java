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
import ua.nure.admin.summarytask.service.MedcardService;

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
public class CloseMedcardServletTest {

    private static final String CLOSE_MEDCARD_PAGE = "pages/closeMedcardPage.jsp";
    private static final String MEDCARD = "medcard";
    private static final String DIAGNOSIS = "diagnosis";
    private String testMedcard = "1";
    private String testDiagnosis = "testDiagnosis";
    private String testUsername = "testUsername";
    private User user;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private MedcardService medcardService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext context;
    private Medcard medcard;
    private CloseMedcardServlet servlet;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername(testUsername);
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(CLOSE_MEDCARD_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.MEDCARD_SERVICE)).thenReturn(medcardService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        Mockito.when(session.getAttribute(Constant.USER)).thenReturn(user);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter(MEDCARD)).thenReturn(testMedcard);
        Mockito.when(request.getParameter(DIAGNOSIS)).thenReturn(testDiagnosis);
        servlet = new CloseMedcardServlet();
        medcard = new Medcard();
        medcard.setDiagnosis(testDiagnosis);
        medcard.setId(Integer.parseInt(testMedcard));
        servlet.init(servletConfig);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.doGet(request, response);
        Mockito.verify(medcardService, Mockito.times(1)).getMedcardByDoctor(user.getUsername());
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        servlet.doPost(request, response);
        Mockito.verify(medcardService, Mockito.times(1)).closeNote(medcard);
    }
}