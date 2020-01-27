package ua.nure.admin.summarytask.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.admin.summarytask.constant.Constant;
import ua.nure.admin.summarytask.repository.DoctorRepository;
import ua.nure.admin.summarytask.repository.NurseRepository;
import ua.nure.admin.summarytask.repository.UserRepository;
import ua.nure.admin.summarytask.repository.PatientRepository;
import ua.nure.admin.summarytask.repository.MedcardRepository;
import ua.nure.admin.summarytask.repository.ScheduleRepository;
import ua.nure.admin.summarytask.repository.impl.DoctorRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.NurseRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.UserRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.PatientRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.MedcardRepositoryImpl;
import ua.nure.admin.summarytask.repository.impl.ScheduleRepositoryImpl;
import ua.nure.admin.summarytask.service.DoctorService;
import ua.nure.admin.summarytask.service.NurseService;
import ua.nure.admin.summarytask.service.UserService;
import ua.nure.admin.summarytask.service.PatientService;
import ua.nure.admin.summarytask.service.MedcardService;
import ua.nure.admin.summarytask.service.ScheduleService;
import ua.nure.admin.summarytask.service.MailService;
import ua.nure.admin.summarytask.service.PdfService;
import ua.nure.admin.summarytask.service.CaptchaService;
import ua.nure.admin.summarytask.service.impl.DoctorServiceImpl;
import ua.nure.admin.summarytask.service.impl.NurseServiceImpl;
import ua.nure.admin.summarytask.service.impl.UserServiceImpl;
import ua.nure.admin.summarytask.service.impl.PatientServiceImpl;
import ua.nure.admin.summarytask.service.impl.MedcardServiceImpl;
import ua.nure.admin.summarytask.service.impl.ScheduleServiceImpl;
import ua.nure.admin.summarytask.service.impl.MailServiceImpl;
import ua.nure.admin.summarytask.service.impl.PdfServiceImpl;
import ua.nure.admin.summarytask.service.impl.CaptchaServiceImpl;

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
        ScheduleRepository scheduleRepository = new ScheduleRepositoryImpl();

        UserService userService = new UserServiceImpl(userRepository);
        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
        NurseService nurseService = new NurseServiceImpl(nurseRepository);
        PatientService patientService = new PatientServiceImpl(patientRepository);
        MedcardService medcardService = new MedcardServiceImpl(medcardRepository);
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository);
        MailService mailService = new MailServiceImpl();
        PdfService pdfService = new PdfServiceImpl();
        CaptchaService captchaService = new CaptchaServiceImpl();

        ServletContext ctx = servletContextEvent.getServletContext();

        ctx.setAttribute(Constant.USER_SERVICE, userService);
        ctx.setAttribute(Constant.DOCTOR_SERVICE, doctorService);
        ctx.setAttribute(Constant.NURSE_SERVICE, nurseService);
        ctx.setAttribute(Constant.PATIENT_SERVICE, patientService);
        ctx.setAttribute(Constant.MEDCARD_SERVICE, medcardService);
        ctx.setAttribute(Constant.MAIL_SERVICE, mailService);
        ctx.setAttribute(Constant.PDF_SERVICE, pdfService);
        ctx.setAttribute(Constant.CAPTCHA_SERVICE, captchaService);
        ctx.setAttribute(Constant.SCHEDULE_SERVICE, scheduleService);

        try {
            Properties log4hprops = new Properties();
            log4hprops.load(getClass().getClassLoader().getResourceAsStream("log4j.properties"));
            PropertyConfigurator.configure(log4hprops);
        } catch (IOException e) {
            log.error("Cannot configure log4j.");
        }
        log.info("Application initialized successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
