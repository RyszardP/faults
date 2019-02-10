package com.ryszard.dao.factory;

import com.ryszard.dao.EmployeeDAO;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        return SQLDaoFactory.getInstance();
    }

    public abstract EmployeeDAO getEmployeeDao();
}
