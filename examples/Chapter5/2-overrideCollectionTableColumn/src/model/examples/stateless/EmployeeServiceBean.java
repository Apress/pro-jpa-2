package examples.stateless;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Employee;
import examples.model.VacationEntry;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String name, long salary) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setSalary(salary);
        
        em.persist(emp);        
        return emp;
    }

    public Employee addEmployeeNickname(int empId, String nickname) {
        Employee emp = em.find(Employee.class, empId);
        if (emp != null) {
            emp.getNickNames().add(nickname);
        }
        return emp;
    }
    
    public Collection<Employee> findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
		Collection<Employee> emps = (Collection<Employee>) query.getResultList();
        // trigger lazy collections
        for (Employee emp : emps) {
			emp.getVacationBookings().size();
			emp.getNickNames().size();
		}
		return emps;
    }
}
