package ua.nure.admin.summarytask.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.service.DoctorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ShowDoctorsByAlphabetServletTest {
    private static final String SHOW_DOCTORS_PAGE = "pages/showDoctorsPage.jsp";
    private ShowDoctorsByAlphabetServlet servlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
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
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.when(request.getRequestDispatcher(SHOW_DOCTORS_PAGE)).thenReturn(dispatcher);
        Mockito.when(context.getAttribute(Constant.DOCTOR_SERVICE)).thenReturn(doctorService);
        Mockito.when(servletConfig.getServletContext()).thenReturn(context);
        servlet = new ShowDoctorsByAlphabetServlet();
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        servlet.init(servletConfig);
        servlet.doGet(request, response);
        Mockito.verify(doctorService, Mockito.times(1)).getDoctorsByAlphabet();
        Mockito.verify(request).setAttribute(Mockito.anyString(), Mockito.any());
    }
}