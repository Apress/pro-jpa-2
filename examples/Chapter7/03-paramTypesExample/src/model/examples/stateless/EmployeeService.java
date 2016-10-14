package examples.stateless;

import java.util.Collection;
import java.util.Date;

import examples.model.Department;
import examples.model.Employee;

public interface EmployeeService {
    public Collection<Employee> findAllEmployees();
    public Collection<Employee> findEmployeesAboveSal(Department dept, long minSal);
    public Collection<Employee> findEmployeesHiredDuringPeriod(Date start, Date end);
    public Employee findHighestPaidByDepartment(Department dept);
}
