package com.ryszard.service.impl;

import com.google.protobuf.ServiceException;
import com.ryszard.dao.EmployeeDAO;
import com.ryszard.dao.factory.DaoFactory;
import com.ryszard.domain.to.Employee;
import com.ryszard.exception.DaoException;
import com.ryszard.exception.NoSuchEntityException;
import com.ryszard.service.validator.EmployeeValidator;
import com.ryszard.service.validator.LoginValidator;
import com.ryszard.service.validator.ValidationException;
import com.ryszard.service.validator.ValidatorInterface;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();

    private static final ValidatorInterface<Employee> VALIDATE = LoginValidator.getInstance();
    private static final ValidatorInterface<Employee> VALIDATE_EMPLOYEE = EmployeeValidator.getInstance();

    private static class SingletonHolder {
        private static final EmployeeServiceImpl instance = new EmployeeServiceImpl();
    }

    public static EmployeeService getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public Employee loadbyId(Employee employeeId) throws ServiceException, NoSuchEntityException {
        return null;
    }

    @Override
    public Employee authorization(Employee employee) throws ServiceException {
        try {
            EmployeeDAO employeeDAO = factory.getEmployeeDao();

            if (VALIDATE.isValid(employee)) {

                String password = employee.getPassword();
                String passwordMD5 = DigestUtils.md5Hex(password);
                employee.setPassword(password);

                boolean check = employeeDAO.checkEmployee(employee.getLogin(), employee.getPassword());
                if (!check) {
                    return null;
                } else {
                    return employeeDAO.getEmployeeNode(employee.getLogin(), employee.getPassword());
                }
            } else {
                throw new ValidationException("Validation Exception");
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public Employee create(Employee obj) throws ServiceException {
        EmployeeDAO employeeDAO = factory.getEmployeeDao();
        try {
            obj.setEmployee_id(new Integer(employeeDAO.create(obj)));
        } catch (DaoException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
        return obj;
    }

    @Override
    public List<Employee> loadAll() throws ServiceException {
        EmployeeDAO employeeDAO = factory.getEmployeeDao();
        try {
            return employeeDAO.findAll();
        } catch (DaoException e) {
            return null;
        }
    }

    @Override
    public Employee update(Employee entity) throws ServiceException {
        EmployeeDAO employeeDAO = factory.getEmployeeDao();
        if (VALIDATE_EMPLOYEE.isValid(entity) == VALIDATE.isValid(entity) == true) {
            try {
                Long id = employeeDAO.update(entity);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
}
