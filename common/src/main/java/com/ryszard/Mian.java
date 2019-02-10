package com.ryszard;

import com.ryszard.dao.EmployeeDAO;
import com.ryszard.dao.connectionPool.ConnectionPool;
import com.ryszard.dao.connectionPool.ConnectionPoolException;
import com.ryszard.dao.factory.DaoFactory;
import com.ryszard.domain.to.Employee;
import com.ryszard.exception.DaoException;

public class Mian {
    public static void main(String[] args) throws ConnectionPoolException {
        ConnectionPool.getInstance().init();
        DaoFactory factory = DaoFactory.getDaoFactory();
        EmployeeDAO employeeDao = factory.getEmployeeDao();

        try {

            Object empl1 = employeeDao.checkEmployee("AndreyLogin", "12345");
            Object empl2 = employeeDao.checkEmployee("PetrIvanov", "12345");

            System.out.println(empl1);
            System.out.println(empl2);


        } catch (DaoException e) {
            e.printStackTrace();
        }

        DaoFactory factory1 = DaoFactory.getDaoFactory();
        EmployeeDAO employeeDAO = factory1.getEmployeeDao();
        try {
            Employee employee1 = employeeDAO.findById(2L);
            System.out.println(employee1.getSurname() + " " + employee1.getName() + " " + employee1.getLogin() + " " + employee1.getPassword());
            System.out.println(" ");
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
