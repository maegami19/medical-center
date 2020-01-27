package ua.nure.admin.summarytask.controller.registration;

import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.controller.registration.command.RegisterServletCommand;
import ua.nure.admin.summarytask.controller.registration.command.impl.RegisterDoctorCommandImpl;
import ua.nure.admin.summarytask.controller.registration.command.impl.RegisterNurseCommandImpl;
import ua.nure.admin.summarytask.controller.registration.command.impl.RegisterPatientCommandImpl;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.UserService;
import ua.nure.admin.summarytask.service.MailService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private static final Map<String, RegisterServletCommand> registerServletCommands = new HashMap<>();
    private static final String LIST_DOCTOR_CATEGORIES = "list";
    private static final String LIST_DOCTORS = "list_doctor";
    private static final String REGISTRATION_PAGE = "pages/registrationPage.jsp";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String ERROR_MESSAGE = "Cannot add user";
    private static DoctorService doctorService;
    private static UserService userService;
    private static MailService mailService;

    @Override
    public void init(ServletConfig servletConfig) {
        registerServletCommands.put(Constant.DOCTOR, new RegisterDoctorCommandImpl());
        registerServletCommands.put(Constant.NURSE, new RegisterNurseCommandImpl());
        registerServletCommands.put(Constant.PATIENT, new RegisterPatientCommandImpl());
        doctorService = (DoctorService) servletConfig.getServletContext().getAttribute(Constant.DOCTOR_SERVICE);
        userService = (UserService) servletConfig.getServletContext().getAttribute(Constant.USER_SERVICE);
        mailService = (MailService) servletConfig.getServletContext().getAttribute(Constant.MAIL_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute(LIST_DOCTOR_CATEGORIES, doctorService.getCategories());
        httpServletRequest.setAttribute(LIST_DOCTORS, doctorService.getAllDoctors());
        httpServletRequest.getRequestDispatcher(REGISTRATION_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        String password = generatePassword();
        String role = httpServletRequest.getParameter(ROLE);
        String email = httpServletRequest.getParameter(EMAIL);
        String username = httpServletRequest.getParameter(USERNAME);
        User user = fillUser(password, role, email, username);

        String answer = userService.addUser(user);
        if (!ERROR_MESSAGE.equals(answer)) {
            mailService.send(user.getUsername(), password, user.getEmail());
        }
        if (!role.equals(Constant.ADMIN)) {
            registerServletCommands.get(role).registerUser(httpServletRequest);
        }

        httpServletResponse.getWriter().print(answer);
    }

    private String generatePassword() {
        String password = new Random().ints(8, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return password;
    }

    private User fillUser(String password, String role, String email, String username) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);
        return user;
    }
}
