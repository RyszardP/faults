package com.ryszard.controller.impl;

import com.ryszard.controller.command.CommandException;
import com.ryszard.controller.command.CommandInterface;
import com.ryszard.dao.EmployeeDAO;
import com.ryszard.dao.factory.DaoFactory;
import com.ryszard.domain.to.Employee;
import com.ryszard.service.impl.EmployeeService;
import com.ryszard.service.impl.EmployeeServiceImpl;
import com.ryszard.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.ryszard.controller.impl.PagePath.ADMIN;

public class EditPasswordCommand  implements CommandInterface {
    private static final EmployeeService SERVICE_EMPLOYEE = EmployeeServiceImpl.getInstance();
    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final EmployeeDAO employeeDAO = factory.getEmployeeDao();

    private static final String EMPLOYEE_ID = "employee_id";
    private static final String PASSWORD = "password";

    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new EditPasswordCommand();
    }
     public EditPasswordCommand (){}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PagePath page = null;
        try {
            HttpSession session = request.getSession(true);
            Long employee_id = (Long) session.getAttribute(EMPLOYEE_ID);
            String password = request.getParameter(PASSWORD);

            Employee employee = new Employee();
            employee = employeeDAO.findById(employee_id);

            employee.setPassword(password);
            SERVICE_EMPLOYEE.update(employee);
            request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
            page = PagePath.ADMIN;
        } catch (ValidationException e) {
            request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE);
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            page = PagePath.ERRORPASW;
        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
        return page.toString().toLowerCase();
    }
}
