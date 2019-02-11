package com.ryszard.service.validator;

import com.ryszard.domain.to.Employee;

public class EmployeeValidator implements ValidatorInterface {

    private static final ValidatorInterface<Employee> instance = new EmployeeValidator();

   private EmployeeValidator(){}

   public static ValidatorInterface<Employee> getInstance( ){return  instance; }



    @Override
    public boolean isValid(Object entity) {
        return false;
    }
}
