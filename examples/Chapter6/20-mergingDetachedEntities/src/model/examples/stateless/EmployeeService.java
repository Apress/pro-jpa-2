package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public void updateEmployee(Employee emp);
    public void updateEmployeeIncorrect(Employee emp);
    public Collection<Employee> findAllEmployees();
}
