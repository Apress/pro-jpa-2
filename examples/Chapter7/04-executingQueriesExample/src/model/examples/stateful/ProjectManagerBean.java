package examples.stateful;

import static javax.persistence.PersistenceContextType.EXTENDED;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Employee;
import examples.model.Project;

@Stateful
public class ProjectManagerBean implements ProjectManager {
    @PersistenceContext(unitName="EmployeeService"/*, type=EXTENDED*/)
    EntityManager em;
    
    Query unassignedQuery;
    
    @PostConstruct
    public void init() {
        unassignedQuery = 
            em.createQuery("SELECT e " +
                           "FROM Employee e " +
                           "WHERE e.projects IS EMPTY");
    }

    public List findEmployeesWithoutProjects() {
        return unassignedQuery.getResultList();
    }

    public List findProjectEmployees(String projectName) {
        return em.createQuery("SELECT e " +
                              "FROM Project p JOIN p.employees e " +
                              "WHERE p.name = :project " +
                              "ORDER BY e.name")
                 .setParameter("project", projectName)
                 .getResultList();
    }

    public Collection<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e").getResultList();
    }

    public Collection<Project> findAllProjects() {
        return em.createQuery("SELECT p FROM Project p").getResultList();
    }
}
