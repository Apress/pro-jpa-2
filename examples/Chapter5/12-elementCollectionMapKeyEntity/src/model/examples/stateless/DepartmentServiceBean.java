package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Department;
import examples.model.Employee;


@Stateless
public class DepartmentServiceBean implements DepartmentService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Department createDepartment(String name) {
        Department dept = new Department();
        dept.setName(name);
        em.persist(dept);
        
        return dept;
    }

    public Employee setEmployeeSeniority(int deptId, int empId, int seniority) {
        Department dept = em.find(Department.class, deptId);
        Employee emp = em.find(Employee.class, empId);
        dept.setEmployeeSeniority(emp, seniority);
        return emp;
    }

    public Collection<Department> findAllDepartments() {
        Query query = em.createQuery("SELECT d FROM Department d");
        return (Collection<Department>) query.getResultList();
    }
}
