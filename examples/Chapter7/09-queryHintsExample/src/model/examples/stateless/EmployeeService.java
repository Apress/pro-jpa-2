package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee findEmployeeNoCache(int id);
    public Employee findEmployeeNoCacheNamed(int id);
    public Employee findEmployee(int id);
    public Collection<Employee> findAllEmployees();
}
