package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Collection<Employee> findAllEmployees();
    public Employee findEmployeeByName(String name);
    public Employee findEmployeeByPrimaryKey(int id);
    public long findSalaryForNameAndDepartment(String deptName, String empName);
}
