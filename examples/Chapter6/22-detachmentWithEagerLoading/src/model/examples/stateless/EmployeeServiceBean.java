package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    private EntityManager em;
    
    public List findAll() {
        return em.createQuery("SELECT e FROM Employee e")
                 .getResultList();
    }
}
