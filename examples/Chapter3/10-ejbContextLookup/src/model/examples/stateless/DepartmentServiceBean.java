package examples.stateless;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@EJB(name="audit", beanInterface=AuditService.class)
public class DepartmentServiceBean implements DepartmentService {
    // use of resource dependency injection is covered later in the chapter
    @Resource SessionContext context;
    AuditService audit;

    public void setSessionContext(SessionContext context) {
        this.context = context;
    }
    
    @PostConstruct
    public void init() {
        audit = (AuditService) context.lookup("audit");
    }

    public void performAudit() {
        audit.audit();
    }
    
    // ...
}

