package com.ryszard.controller.impl;

import com.ryszard.controller.command.CommandException;
import com.ryszard.controller.command.CommandInterface;
import com.ryszard.domain.to.Employee;
import com.ryszard.service.impl.EmployeeService;
import com.ryszard.service.impl.EmployeeServiceImpl;
import com.ryszard.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* Class is designed for the registration of a new employee
 */
public class RegistrationCommand implements CommandInterface {
    private static final EmployeeService SERVICE = EmployeeServiceImpl.getInstance();
    private static final String EMPLOYEE_ROLE = "employee";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String SURNAME_ATTRIBUTE = "surname";
    private static final String EMAIL = "email";
    private static final String SPECIALITY = "speciality";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String GENDER_ATTRIBUTE = "gender";
    private static final String EMPLOYEE_SECTOR_ATTRIBUTE = "employee_sector";

    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final int ERROR_FLAG_VALUE_2 = 2;

    private RegistrationCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new RegistrationCommand();
    }

    /**
     * Method performs the procedure for adding a new customer to the system.
     * Getting all information about new client and then create new node in system.
     * Also determines what action must be made for transition(forward or sendRedirect).
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return the path to go to a specific page
     * @throws CommandException when creating fail
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        PagePath page = null;
        try {

            String name = request.getParameter(NAME_ATTRIBUTE);
            String surname = request.getParameter(SURNAME_ATTRIBUTE);
            String email = request.getParameter(EMAIL);
            String speciality = request.getParameter(SPECIALITY);
            // String role = EMPLOYEE_ROLE;
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String employee_sector = request.getParameter(EMPLOYEE_SECTOR_ATTRIBUTE);

            Employee employee = new Employee();
            employee.setName(name);
            employee.setSurname(surname);
            employee.setEmail(email);
            employee.setSpeciality(speciality);

            //  employee.setRole(role);
            employee.setLogin(login);
            employee.setPassword(password);
            employee.setEmployeeSector_id(employee_sector);


            Employee resultEmployee = SERVICE.create(employee);
            if (resultEmployee == null) {

                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
                page = PagePath.ERROR;
            } else {
                HttpSession session = request.getSession(true);
                page = PagePath.ADMIN;
                session.setAttribute(EMPLOYEE_ROLE, resultEmployee);
                request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            }
        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
        return page.toString().toLowerCase();
    }
}