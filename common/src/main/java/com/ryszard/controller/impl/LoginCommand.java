package com.ryszard.controller.impl;


import com.google.protobuf.ServiceException;
import com.ryszard.controller.command.CommandException;
import com.ryszard.controller.command.CommandInterface;
import com.ryszard.domain.to.Employee;
import com.ryszard.service.impl.EmployeeService;
import com.ryszard.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* Class is designed for the log in in system as administrator or client
 */
public class LoginCommand implements CommandInterface {

    private static final EmployeeService SERVICE = EmployeeServiceImpl.getInstance();

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String EMPLOYEE = "employee";
    private static final String ERROR_FLAG = "errorFlag";

    private static final String ACTION = "action";

    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private LoginCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new LoginCommand();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        PagePath page = null;

        Employee tempEmployee = new Employee();
        tempEmployee.setLogin(request.getParameter(LOGIN));
        tempEmployee.setPassword(request.getParameter(PASSWORD));

        HttpSession session = request.getSession(true);
        Employee employee = null;
        try {
            employee = SERVICE.authorization(tempEmployee);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (employee == null) {

            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            page = PagePath.REGISTRATION;
        } else {

            page = PagePath.ADMIN;
            session.setAttribute(EMPLOYEE, employee);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
        }
        return page.toString().toLowerCase();

    }
}