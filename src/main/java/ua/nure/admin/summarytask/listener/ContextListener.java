package ua.nure.admin.summarytask.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.admin.summarytask.repository.*;
import ua.nure.admin.summarytask.repository.impl.*;
import ua.nure.admin.summarytask.service.*;
import ua.nure.admin.summarytask.service.impl.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger log = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        UserRepository userRepository = new UserRepositoryImpl();
        DoctorRepository doctorRepository = new DoctorRepositoryImpl();
        NurseRepository nurseRepository = new NurseRepositoryImpl();
        PatientRepository patientRepository = new PatientRepositoryImpl();
        MedcardRepository medcardRepository = new MedcardRepositoryImpl();

        UserService userService = new UserServiceImpl(userRepository);
        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
        NurseService nurseService = new NurseServiceImpl(nurseRepository);
        PatientService patientService = new PatientServiceImpl(patientRepository);
        MedcardService medcardService = new MedcardServiceImpl(medcardRepository);

        ServletContext ctx = servletContextEvent.getServletContext();

        ctx.setAttribute("userService", userService);
        ctx.setAttribute("doctorService", doctorService);
        ctx.setAttribute("nurseService", nurseService);
        ctx.setAttribute("patientService", patientService);
        ctx.setAttribute("medcardService", medcardService);

        try {
            Properties log4hprops = new Properties();
            log4hprops.load(getClass().getClassLoader().getResourceAsStream("log4j.properties"));
            PropertyConfigurator.configure(log4hprops);
        } catch (IOException e) {
            log.error("Cannot configure log4j.");
            e.printStackTrace();
        }

        log.info("Application initialized successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
