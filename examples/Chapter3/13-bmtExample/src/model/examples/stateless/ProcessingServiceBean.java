package examples.stateless;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProcessingServiceBean implements ProcessingService {
    @Resource UserTransaction tx;

    public void process() {
        try {
            try {
                tx.begin();
                // do the work...
                System.out.println("Processing...");
            } finally {
                tx.commit();
            }
        } catch (Exception e) {
            // handle all the tx.begin()/commit() exceptions
            throw new EJBException(e);
        }
    }
    
    // ...
}
