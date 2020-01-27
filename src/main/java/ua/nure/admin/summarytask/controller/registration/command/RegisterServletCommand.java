package ua.nure.admin.summarytask.controller.registration.command;

import javax.servlet.http.HttpServletRequest;

public interface RegisterServletCommand {
    /**
     * This method allows you to register a user depending on the selected role
     * in the application. Thanks to the request, you can get all the necessary
     * parameters for the correct operation of the method.
     *
     * @param request
     * @author Bohdan Admin
     */
    void registerUser(HttpServletRequest request);
}
