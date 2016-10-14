package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.eclipse.persistence.queries.ObjectLevelReadQuery;
import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="QueryHints")
    private EntityManager em;
    

    public Employee findEmployee(int empId) {
        Query q = em.createQuery("SELECT e FROM Employee e WHERE e.id =  ?1");
        q.setParameter(1, empId);
        try {
            return (Employee) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Employee findEmployeeNoCache(int empId) {
        Query q = em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1");
        // force read from database
        q.setHint("cacheUsage", ObjectLevelReadQuery.DoNotCheckCache);
        q.setParameter(1, empId);
        try {
            return (Employee) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public Employee findEmployeeNoCacheNamed(int empId) {
        Query q = em.createNamedQuery("findEmployeeNoCache");
        q.setParameter(1, empId);
        try {
            return (Employee) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Collection<Employee> findAllEmployees() {
        return (Collection<Employee>) em.createQuery(
                "SELECT e FROM Employee e").getResultList();
    }
}
