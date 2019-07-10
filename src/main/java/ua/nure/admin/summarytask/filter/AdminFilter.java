package ua.nure.admin.summarytask.filter;

import ua.nure.admin.summarytask.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/showdoc_by_alphabet", "/showdoc_by_category", "/showdoc_by_count_patients", "/showpatients_by_alphabet", "/showpatients_by_birthday"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && "admin".equals(user.getRole())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.setAttribute("error", "ACCESS DENIED");
            request.getServletContext().getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
