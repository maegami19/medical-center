package ua.nure.admin.summarytask.controller.registration.command.impl;

import ua.nure.admin.summarytask.controller.registration.command.RegisterServletCommand;
import ua.nure.admin.summarytask.entity.Nurse;
import ua.nure.admin.summarytask.service.NurseService;

import javax.servlet.http.HttpServletRequest;

public class RegisterNurseCommandImpl implements RegisterServletCommand {

    @Override
    public void registerUser(HttpServletRequest request) {
        NurseService nurseService = (NurseService) request.getServletContext().getAttribute("nurseService");
        Nurse nurse = new Nurse();
        nurse.setFirstname(request.getParameter("firstname"));
        nurse.setLastname(request.getParameter("lastname"));
        nurse.setUsername(request.getParameter("username"));
        nurseService.addNurse(nurse);
    }
}
