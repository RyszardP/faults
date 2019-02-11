package com.ryszard.service.validator;

import com.ryszard.domain.to.Employee;

public class LoginValidator implements ValidatorInterface<Employee> {
    private static final ValidatorInterface<Employee> instance = new LoginValidator();

    private LoginValidator(){}

    public static ValidatorInterface<Employee> getInstance(){
        return instance;
    }

    @Override
    public boolean isValid(Employee employee) {


        return employee.getLogin() != null && employee.getPassword() != null;
    }
}
