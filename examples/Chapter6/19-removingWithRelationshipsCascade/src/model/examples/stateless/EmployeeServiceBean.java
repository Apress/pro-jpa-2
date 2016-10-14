package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.Phone;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public void removeEmployee(int empId) {
        Employee emp = em.find(Employee.class, empId);
        em.remove(emp);

    }
    
    public Collection<Employee> findAllEmployees() {
        return (Collection<Employee>) em.createQuery(
                "SELECT e FROM Employee e").getResultList();
    }
    
    public Collection<Phone> findAllPhones() {
        return (Collection<Phone>) em.createQuery(
                "SELECT p FROM Phone p").getResultList();
    }
}
