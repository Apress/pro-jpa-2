package examples.stateless;

import java.util.Collection;
import java.util.Date;

import examples.model.Conversation;

public interface ConversationMaintenance {
    public void archiveConversations(Date minAge);
    public Collection<Conversation> findAllConversations();
}
