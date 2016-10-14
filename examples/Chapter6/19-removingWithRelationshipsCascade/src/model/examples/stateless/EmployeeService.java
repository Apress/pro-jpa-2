package examples.stateless;

import java.util.Collection;

import examples.model.Employee;
import examples.model.Phone;

public interface EmployeeService {
    public void removeEmployee(int empId);
    public Collection<Phone> findAllPhones();
    public Collection<Employee> findAllEmployees();
}
