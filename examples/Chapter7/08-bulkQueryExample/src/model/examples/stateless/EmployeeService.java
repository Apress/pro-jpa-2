package examples.stateless;

import java.util.Collection;

import examples.model.Department;
import examples.model.Employee;

public interface EmployeeService {
    public void assignManager(Department dept, Employee manager);
    public Employee findEmployee(int id);
    public Collection<Employee> findAllEmployees();
}
