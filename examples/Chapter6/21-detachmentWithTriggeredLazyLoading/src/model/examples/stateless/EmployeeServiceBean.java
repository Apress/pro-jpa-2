package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    private EntityManager em;
    
    public List findAll() {
        List<Employee> emps = em.createQuery("SELECT e FROM Employee e")
                      .getResultList();
        for (Employee emp : emps) {
            if (emp.getDepartment() != null) {
                emp.getDepartment().getName();
            }
        }
        return emps;
    }
}
