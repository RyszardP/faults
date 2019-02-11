package com.ryszard.dao.impls;

import com.ryszard.dao.EmployeeDAO;
import com.ryszard.dao.connectionPool.ConnectionPool;
import com.ryszard.dao.connectionPool.ConnectionPoolException;
import com.ryszard.domain.to.Employee;
import com.ryszard.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLEmployeeDao implements EmployeeDAO {
    private static final String DELETE_BY_ID = "DELETE FROM employee WHERE employee_id = ?";
    private static final String SELECT_SPECIFIC_EMPLOYEE = "SELECT * FROM employee WHERE  login = ? AND name = ?";

    private static final String SELECT_BY_ID = "SELECT * FROM employee WHERE employee_id = ?";

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
    private static final String SELECT_EMPLOYEE = "SELECT * FROM employee WHERE  login = ? and password = ?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET password = ? WHERE employee_id = ? LIMIT 1";
    private static final String CREATE_EMPLOYEE = "INSERT INTO employee (name, surname, email, speciality, gender, login, password, employee_sector) " +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LAST_INSERT_ID = "SELECT last_insert_id() as lastId";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String SPECIALITY = "speciality";
    private static final String ROLE = "role";
    private static final String EMPLOYEE_ID = "employee_id";
    private static final String EMAIL = "email";
    private static final String EMPLOYEE_SECTOR_ID = "employeeSectorId";
    private static final String LAST_ID_ATTRIBUTE = "lastId";


    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private SQLEmployeeDao() {
    }

    private static class SingletonHolder {
        private static final EmployeeDAO instance = new SQLEmployeeDao();
    }

    public static EmployeeDAO getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public boolean checkEmployee(String login, String password) throws DaoException {

        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_EMPLOYEE)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


    private Employee getEmployeeFromResultSet(ResultSet set) throws DaoException {
        try {
            Employee employee = new Employee();
            employee.setEmployee_id(set.getInt(EMPLOYEE_ID));
            employee.setLogin(set.getString(LOGIN));
            employee.setPassword(set.getString(PASSWORD));
            employee.setRole(set.getString(ROLE));
            employee.setName(set.getString(NAME));
            employee.setSurname(set.getString(SURNAME));
            employee.setSpeciality(set.getString(SPECIALITY));
            employee.setEmail(set.getString(EMAIL));
            return employee;
        } catch (SQLException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public List<Employee> findAll() throws DaoException {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Employee employee = new Employee();
                employee.setLogin(set.getString(LOGIN));
                employee.setPassword(set.getString(PASSWORD));
                employee.setEmployee_id(set.getInt(EMPLOYEE_ID));
                employee.setName(set.getString(NAME));
                employee.setSurname(set.getString(SURNAME));
                employee.setSpeciality(set.getString(SPECIALITY));
                employee.setEmail(set.getString(EMAIL));
                employeeList.add(employee);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return employeeList;
    }


    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DaoException("Exception! ", e);
        }
    }

    /**
     * Method find employee node in database by unique number
     *
     * @param id unique number of node in database
     * @return employee object with all identification information: login, password, role, etc.;
     * null - if node can't be find in database
     * @throws DaoException if there were errors while reading employee from database.
     */
    @Override
    public Employee findById(Long id) throws DaoException {

        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                Employee employee = new Employee();
                employee.setPassword(set.getString(PASSWORD));
                return employee;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("id Exception", e);
        }
    }


    @Override
    public Employee getEmployeeNode(String login, String password) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_EMPLOYEE)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                Employee employee = new Employee();
                employee.setLogin(login);
                employee.setLogin(password);
                return employee;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    /**
     * Method execute creating new fault node in database.
     *
     * @param newEmployee fault object that can be inserted in database.
     * @return unique number of new node.
     * @throws DaoException if there were errors while inserting data into database.
     */
    @Override
    public int create(Employee newEmployee) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_EMPLOYEE);
             PreparedStatement statementTwo = connect.prepareStatement(LAST_INSERT_ID)) {
            statement.setString(1, newEmployee.getName());
            statement.setString(2, newEmployee.getSurname());
            statement.setString(3, newEmployee.getEmail());
            statement.setString(4, newEmployee.getSpeciality());
            statement.setString(5, newEmployee.getGender() != null ? String.valueOf(newEmployee.getGender()) : "Male");
            statement.setString(6, newEmployee.getLogin());
            statement.setString(7, newEmployee.getPassword());
            statement.setString(8, newEmployee.getEmployeeSector_id());


            statement.executeUpdate();

            ResultSet set = statementTwo.executeQuery();
            set.next();
            return set.getInt(LAST_ID_ATTRIBUTE);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(Employee entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_EMPLOYEE)) {

            statement.setString(1, entity.getPassword());

            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


}
