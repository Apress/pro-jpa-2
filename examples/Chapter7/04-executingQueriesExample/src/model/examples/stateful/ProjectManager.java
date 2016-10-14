package examples.stateful;

import java.util.Collection;
import java.util.List;

import examples.model.Employee;
import examples.model.Project;

public interface ProjectManager {
    public List findEmployeesWithoutProjects();
    public List findProjectEmployees(String projectName);
    public Collection<Employee> findAllEmployees();
    public Collection<Project> findAllProjects();
}
