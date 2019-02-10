package com.ryszard.domain.to;

import java.io.Serializable;

/**
 * Created by user on 15.01.2019.
 */

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;
    private Long employee_id;
    private String roleName;

    public Role() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (!roleId.equals(role.roleId)) {
            return false;
        }
        if (!employee_id.equals(role.employee_id)) {
            return false;
        }
        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return (int) (7 * this.roleId.hashCode() + 7 * this.employee_id.hashCode() + 7 * this.roleName.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("\n");
        sb.append("Role ID: ");
        sb.append(this.roleId);
        sb.append("\n");
        sb.append("Employee ID: ");
        sb.append(this.employee_id);
        sb.append("\n");
        sb.append("Role name: ");
        sb.append(this.roleName);
        sb.append("\n");

        return sb.toString();
    }
}

