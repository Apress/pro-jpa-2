package examples.stateless;

import java.util.Collection;

import examples.model.Department;
import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployee(int empId, String empName, int deptId);
    public Collection<Employee> findAllEmployees();
    public Collection<Department> findAllDepartments();
}
