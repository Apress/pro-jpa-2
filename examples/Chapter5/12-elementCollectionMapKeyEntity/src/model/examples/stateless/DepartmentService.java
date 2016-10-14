package examples.stateless;

import java.util.Collection;

import examples.model.Department;
import examples.model.Employee;

public interface DepartmentService {
    public Department createDepartment(String name);
    public Employee setEmployeeSeniority(int deptId, int empId, int seniority);
    public Collection<Department> findAllDepartments();
}
