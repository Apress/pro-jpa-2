package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.Project;

@Stateless
public class ProjectServiceBean implements ProjectService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;
    
    public void assignEmployeeToProject(int empId, int projectId) {
        Project project = em.find(Project.class, projectId);
        Employee employee = em.find(Employee.class, empId);
        project.getEmployees().add(employee);
        employee.getProjects().add(project);
    }

    public Collection<Project> findAllProjects() {
        return (Collection<Project>) em.createQuery(
                "SELECT p FROM Project p").getResultList();
    }
}
