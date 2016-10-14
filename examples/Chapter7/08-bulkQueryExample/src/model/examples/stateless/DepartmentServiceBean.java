package examples.stateless;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Department;

@Stateless
public class DepartmentServiceBean implements DepartmentService {
    @PersistenceContext(unitName="BulkQueries")
    protected EntityManager em;

    
    @TransactionAttribute(REQUIRES_NEW)
    public void removeDepartmentFailure() {
         em.createQuery("DELETE FROM Department d " +
                        "WHERE d.name IN ('CA13', 'CA19', 'NY30')")
           .executeUpdate();
    }

    @TransactionAttribute(REQUIRES_NEW)
    public void removeDepartment() {
        em.createQuery("UPDATE Employee e " +
                       "SET e.department = null " +
                       "WHERE e.department.name IN ('CA13', 'CA19', 'NY30')")
          .executeUpdate();
    }

    public Department findDepartment(int id) {
        return em.find(Department.class, id);
    }

    public Collection<Department> findAllDepartments() {
        return (Collection<Department>) em.createQuery(
                "SELECT d FROM Department d").getResultList();
    }
}
