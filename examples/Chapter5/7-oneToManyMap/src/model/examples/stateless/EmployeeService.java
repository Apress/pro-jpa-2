package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployee(String name, long salary);
    public Employee setEmployeeDepartment(int empId, String cubeId, int deptId);
    public Collection<Employee> findAllEmployees();
}
