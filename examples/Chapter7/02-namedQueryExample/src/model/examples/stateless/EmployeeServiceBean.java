package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="NamedQueries")
    EntityManager em;

    public Employee findEmployeeByName(String name) {
        try {
            return (Employee) em.createNamedQuery("Employee.findByName")
                                .setParameter("name", name)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
   }

    public Employee findEmployeeByPrimaryKey(int id) {
        try {
            return (Employee) em.createNamedQuery("Employee.findByPrimaryKey")
                                .setParameter("id", id)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public long findSalaryForNameAndDepartment(String deptName, String empName) {
        try {
            return (Long) em.createNamedQuery("findSalaryForNameAndDepartment")
                            .setParameter("deptName", deptName)
                            .setParameter("empName", empName)
                            .getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }
    
    public Collection<Employee> findAllEmployees() {
        return (Collection<Employee>) em.createNamedQuery("Employee.findAll")
                                        .getResultList();
    }
}
