package examples.stateless;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Department;
import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="BulkQueries")
    EntityManager em;

    @TransactionAttribute(REQUIRES_NEW)
    public void assignManager(Department dept, Employee manager) {
         em.createQuery("UPDATE Employee e " +
                        "SET e.manager = :manager " +
                        "WHERE e.department = :dept ")
           .setParameter("manager", manager)
           .setParameter("dept", dept)
           .executeUpdate();
    }

    public Employee findEmployee(int id) {
        return em.find(Employee.class, id);
    }

    public Collection<Employee> findAllEmployees() {
        return (Collection<Employee>) em.createQuery(
                "SELECT e FROM Employee e").getResultList();
    }
}
