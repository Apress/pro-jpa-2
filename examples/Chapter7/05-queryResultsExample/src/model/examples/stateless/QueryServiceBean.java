package examples.stateless;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.Project;

@Stateless
public class QueryServiceBean implements QueryService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    @TransactionAttribute(NOT_SUPPORTED)
    public List findAllDepartmentsDetached() {
        return em.createQuery("SELECT d FROM Department d")
                 .getResultList();
    }

    public List findProjectEmployees(String projectName) {
        return em.createQuery("SELECT e.name, e.department.name " +
                              "FROM Project p JOIN p.employees e " +
                              "WHERE p.name = :project " +
                              "ORDER BY e.name")
                 .setParameter("project", projectName)
                 .getResultList();
    }

    public List findProjectEmployeesWithConstructor(String projectName) {
        return em.createQuery("SELECT NEW examples.EmpMenu(e.name, e.department.name) " +
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
