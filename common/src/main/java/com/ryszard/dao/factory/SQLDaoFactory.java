package com.ryszard.dao.factory;


import com.ryszard.dao.EmployeeDAO;
import com.ryszard.dao.impls.SQLEmployeeDao;

public class SQLDaoFactory extends DaoFactory {

        private static final SQLDaoFactory instance = new SQLDaoFactory();

        private SQLDaoFactory(){}

        public static SQLDaoFactory getInstance(){
            return instance;
        }


        @Override
        public EmployeeDAO getEmployeeDao() {
            return SQLEmployeeDao.getInstance();
        }

    }
