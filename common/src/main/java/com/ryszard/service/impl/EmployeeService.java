package com.ryszard.service.impl;

import com.google.protobuf.ServiceException;
import com.ryszard.domain.to.Employee;
import com.ryszard.exception.NoSuchEntityException;

public interface EmployeeService extends GenericServiceInterface <Employee,Employee> {
    Employee loadbyId (Employee employeeId) throws ServiceException, NoSuchEntityException;


    Employee authorization(Employee employee) throws ServiceException;
}
