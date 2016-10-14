package examples.stateless;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import examples.model.Department;
import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public Collection<Employee> findEmployeesAboveSal(Department dept, long minSal) {
        return (Collection<Employee>) em.createNamedQuery("findEmployeesAboveSal")
                                        .setParameter("dept", dept)
                                        .setParameter("sal", minSal)
                                        .getResultList();
    }

    public Collection<Employee> findEmployeesHiredDuringPeriod(Date start, Date end) {
        return (Collection<Employee>) 
            em.createQuery("SELECT e " +
                           "FROM Employee e " +
                           "WHERE e.startDate BETWEEN :start AND :end")
              .setParameter("start", start, TemporalType.DATE)
              .setParameter("end", end, TemporalType.DATE)
              .getResultList();
    }

    public Employee findHighestPaidByDepartment(Department dept) {
        try {
            return (Employee) em.createNamedQuery("findHighestPaidByDepartment")
                                .setParameter("dept", dept)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Collection<Employee> findAllEmployees() {
        return (Collection<Employee>) em.createQuery(
                "SELECT e FROM Employee e").getResultList();
    }
}
