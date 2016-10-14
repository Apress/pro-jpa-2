package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Department;
import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public Employee createEmployee(int empId, String empName, int deptId) {
        Department dept = em.find(Department.class, deptId);
        Employee emp = new Employee();
        emp.setId(empId);
        emp.setName(empName);
        emp.setDepartment(dept);
        dept.getEmployees().add(emp);
        em.persist(emp);
        return emp;
    }
    
    public Collection<Employee> findAllEmployees() {
        return (Collection<Employee>) em.createQuery(
                "SELECT e FROM Employee e").getResultList();
    }

    public Collection<Department> findAllDepartments() {
        return (Collection<Department>) em.createQuery(
                "SELECT d FROM Department d").getResultList();
    }
}
