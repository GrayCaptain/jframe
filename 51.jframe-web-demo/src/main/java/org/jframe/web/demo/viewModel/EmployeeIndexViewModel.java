package org.jframe.web.demo.viewModel;

import org.jframe.core.extensions.JList;
import org.jframe.data.entities.Department;
import org.jframe.data.entities.Role;
import org.jframe.infrastructure.AppContext;
import org.jframe.service.demo.DepartmentService;
import org.jframe.service.demo.RoleService;
import org.jframe.web.demo.Menu;
import org.jframe.web.demo.score.DemoLayoutViewModel;

/**
 * created by yezi on 2018/1/16
 */
public class EmployeeIndexViewModel extends DemoLayoutViewModel {

    private Department department;
    private JList<Department> departments;
    private JList<Role> roles;

    public EmployeeIndexViewModel(){
        super("权限管理");
    }

    public EmployeeIndexViewModel build(){
        super.setCurrentPage(Menu.demo().permission());
        this.departments = AppContext.getBean(DepartmentService.class).getAll();
        this.department = this.departments.firstOrNull();
        JList<Role> allRoles = AppContext.getBean(RoleService.class).getAll();
        this.roles = allRoles.orderByDesc(x->x.getId()).toList().where(x -> !x.isSystem());
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(JList<Department> departments) {
        this.departments = departments;
    }

    public JList<Role> getRoles() {
        return roles;
    }

    public void setRoles(JList<Role> roles) {
        this.roles = roles;
    }
}
