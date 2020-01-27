package ua.nure.admin.summarytask.service;

public interface MailService {

    /**
     * This method allows you to send a letter to the recipient with his username and password.
     *
     * @param login    - new user's username
     * @param password - new user's password
     * @param receiver - new user's email
     */
    void send(String login, String password, String receiver);
}
