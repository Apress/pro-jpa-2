package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface QueryService {
    public Collection<Employee> findAllEmployees();
    public long queryEmpSalary(String deptName, String empName);
    public long queryEmpSalaryUsingParams(String deptName, String empName);
}
