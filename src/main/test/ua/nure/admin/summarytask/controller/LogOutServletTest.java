package ua.nure.admin.summarytask.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogOutServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void doGetTest() throws IOException {
        new LogOutServlet().doGet(request, response);

        verify(session, times(1)).removeAttribute(Constant.USER);
        verify(session, times(1)).invalidate();
        verify(response).sendRedirect("/");
    }
}