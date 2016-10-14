package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public void removeParkingSpace(int empId);
    public void removeParkingSpaceWithFailure(int empId);
    public Collection<Employee> findAllEmployees();
}
