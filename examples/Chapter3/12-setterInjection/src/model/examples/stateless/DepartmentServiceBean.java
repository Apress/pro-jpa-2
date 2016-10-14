package examples.stateless;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DepartmentServiceBean implements DepartmentService {
    private AuditService audit;

    @EJB
    public void setAuditService(AuditService audit) {
        this.audit = audit;
    }

    public void performAudit() {
        audit.audit();
    }
    
    // ...
}

