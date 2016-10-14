package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public void createEmployee(Employee emp);
    public void createEmployee2(Employee emp);
    public Collection<Employee> findAllEmployees();
}
