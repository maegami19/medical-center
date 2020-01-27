package ua.nure.admin.summarytask.service.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.mailer.MailSender;
import ua.nure.admin.summarytask.service.MailService;

import java.io.IOException;

public class MailServiceImpl implements MailService {

    private static final Logger log = Logger.getLogger(MailServiceImpl.class);

    @Override
    public void send(String login, String password, String receiver) {
        if (login != null && password != null && receiver != null) {
            try {
                MailSender.send(login, password, receiver);
            } catch (IOException e) {
                log.error("Cannot send mail", e);
            }
        }
    }
}
