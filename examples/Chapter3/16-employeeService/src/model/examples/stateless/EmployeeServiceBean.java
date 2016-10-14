package examples.stateless;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.Stateless;
// import javax.jms.Queue;
// import javax.jms.QueueConnectionFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    //@Resource(mappedName="destinationQueue") Queue destinationQueue;
    //@Resource(mappedName="factory") QueueConnectionFactory factory;

    public EntityManager getEntityManager() {
        return em;
    }

    public Employee createEmployee(int id, String name, long salary) {
        Employee emp = new Employee(id);
        emp.setName(name);
        emp.setSalary(salary);
        getEntityManager().persist(emp);
        return emp;
    }

    public Employee removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            getEntityManager().remove(emp);
            return emp;
        } else return null;

    }

    public Employee changeEmployeeSalary(int id, long newSalary) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            emp.setSalary(newSalary);
        }
        return emp;
    }

    public Employee findEmployee(int id) {
        return getEntityManager().find(Employee.class, id);
    }

    public Collection<Employee> findAllEmployees() {
        Query query = getEntityManager().createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>) query.getResultList();
    }
}
