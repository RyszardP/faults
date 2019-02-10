package com.ryszard.domain.to;



import com.ryszard.domain.enums.Gender;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by user on 15.01.2019.
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private int employee_id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private Gender gender;
    private String specialty;
    private String role;
    private String employeeSector_id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Employee(int employee_id, String name, String surname, String email, String login, String password, Gender gender, String specialty, String role, String employeeSector_id) {
        this.employee_id = employee_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.specialty = specialty;
        this.role = role;
        this.employeeSector_id = employeeSector_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmployeeSector_id() {
        return employeeSector_id;
    }

    public void setEmployeeSector_id(String employeeSector_id) {
        this.employeeSector_id = employeeSector_id;
    }

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employee_id != employee.employee_id) return false;
        if (employeeSector_id != employee.employeeSector_id) return false;
        if (!name.equals(employee.name)) return false;
        if (!surname.equals(employee.surname)) return false;
        if (!email.equals(employee.email)) return false;
        if (!login.equals(employee.login)) return false;
        if (!password.equals(employee.password)) return false;
        if (gender != employee.gender) return false;
        if (!specialty.equals(employee.specialty)) return false;
        return role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, name, surname, email, login, password, gender, specialty, role, employeeSector_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("employee_id=").append(employee_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", specialty='").append(specialty).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", employeeSector_id=").append(employeeSector_id);
        sb.append('}');
        return sb.toString();
    }

}
