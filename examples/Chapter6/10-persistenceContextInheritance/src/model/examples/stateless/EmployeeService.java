package examples.stateless;

import java.util.Collection;

import examples.model.Department;
import examples.model.Employee;

public interface EmployeeService {
    public Collection<Employee> findAllEmployees();
    public Collection<Department> findAllDepartments();
}
