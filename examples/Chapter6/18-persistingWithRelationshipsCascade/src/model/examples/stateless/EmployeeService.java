package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployeeAndAddress(int id, String name,
                      int addrId, String street, String city, String state);
    public Collection<Employee> findAllEmployees();
}
