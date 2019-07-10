package ua.nure.admin.summarytask.controller;

import ua.nure.admin.summarytask.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showdoc_by_category"})
public class ShowDoctorsByCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DoctorService doctorService = (DoctorService) httpServletRequest.getServletContext().getAttribute("doctorService");
        httpServletRequest.setAttribute("list_doc", doctorService.getDoctorsByCategory());
        httpServletRequest.getRequestDispatcher("pages/showdoc.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
