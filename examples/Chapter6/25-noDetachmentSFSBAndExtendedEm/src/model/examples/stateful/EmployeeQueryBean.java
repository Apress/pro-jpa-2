package examples.stateful;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmployeeQueryBean implements EmployeeQuery {
    @PersistenceContext(type=PersistenceContextType.EXTENDED, 
                        unitName="EmployeeService")
    EntityManager em;
    
    public List findAll() {
        return em.createQuery("SELECT e FROM Employee e")
                 .getResultList();
    }
    
    @Remove
    public void finished() {
    }
}

