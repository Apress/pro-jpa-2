package examples.stateless;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DepartmentServiceBean implements DepartmentService {
    @EJB AuditService audit;

    public void performAudit() {
        audit.audit();
    }
    
    // ...
}

