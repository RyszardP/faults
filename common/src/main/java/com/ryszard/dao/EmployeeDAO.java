package com.ryszard.dao;

import com.ryszard.domain.to.Employee;
import com.ryszard.exception.DaoException;

public interface EmployeeDAO  extends GenericDAO <Employee, Long> {
        /** Method get {@link Employee} object from database by login and password
         *
         * @param login login unique parameter
         * @param password password parameter
         * @return {@link Employee} object
         * @throws DaoException
         */
        Employee getEmployeeNode(String login, String password) throws DaoException;


        boolean checkEmployee(String login, String password) throws DaoException;


    }

