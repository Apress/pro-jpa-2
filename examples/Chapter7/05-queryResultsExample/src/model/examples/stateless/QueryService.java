package examples.stateless;

import java.util.Collection;
import java.util.List;

import examples.model.Employee;
import examples.model.Project;

public interface QueryService {
    public List findAllDepartmentsDetached();
    public List findProjectEmployees(String projectName);
    public List findProjectEmployeesWithConstructor(String projectName);
    public Collection<Employee> findAllEmployees();
    public Collection<Project> findAllProjects();
}
