package examples.stateless;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.FlushModeType;

import examples.model.Conversation;

@Stateless
public class ConversationMaintenanceBean implements ConversationMaintenance {
    @PersistenceContext(unitName="MessageBoard")
    private EntityManager em;
    

    public void archiveConversations(Date minAge) {
        List<Conversation> active = (List<Conversation>)
            em.createNamedQuery("findActiveConversations")
              .getResultList();
        Query maxAge = em.createNamedQuery("findLastMessageDate");
        maxAge.setFlushMode(FlushModeType.COMMIT);
        for (Conversation c : active) {
            maxAge.setParameter("conversation", c);
            Date lastMessageDate  = (Date) maxAge.getSingleResult();
            if (lastMessageDate.before(minAge)) {
                c.setStatus("INACTIVE");
            }
        }
    }


    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Collection<Conversation> findAllConversations() {
        return (Collection<Conversation>) em.createQuery(
                "SELECT c FROM Conversation c").getResultList();
    }
}
