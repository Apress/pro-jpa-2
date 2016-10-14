package examples.stateless;

import javax.ejb.Stateless;

@Stateless
public class AuditServiceBean implements AuditService {
    public void audit() {
        System.out.println("Audit performed.");
    }
}

