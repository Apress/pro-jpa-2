package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class QueryServiceBean implements QueryService {
    private static final String QUERY =
        "SELECT e.salary " +
        "FROM Employee e " +
        "WHERE e.department.name = :deptName AND " +
        "      e.name = :empName ";

    @PersistenceContext(unitName="DynamicQueries")
    EntityManager em;

    public long queryEmpSalary(String deptName, String empName) {
        String query = "SELECT e.salary " +
                       "FROM Employee e " +
                       "WHERE e.department.name = '" + deptName + "' AND " +
                       "      e.name = '" + empName + "'";
        try {
            return (Long) em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }
    
    public long queryEmpSalaryUsingParams(String deptName, String empName) {
        try {
            return (Long) em.createQuery(QUERY)
                            .setParameter("deptName", deptName)
                            .setParameter("empName", empName)
                            .getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }


    public Collection<Employee> findAllEmployees() {
        return (Collection<Employee>) em.createQuery(
                "SELECT e FROM Employee e").getResultList();
    }
}
