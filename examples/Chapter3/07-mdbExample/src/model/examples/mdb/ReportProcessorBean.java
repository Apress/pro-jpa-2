package examples.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

// use of mappedName is vendor specific.  In this case, it is used
// to specify the JNDI location of the JMS Queue to use.
@MessageDriven(
    mappedName="destinationQueue",
    activationConfig = {
        @ActivationConfigProperty(propertyName="destinationType",
                                  propertyValue="javax.jms.Queue"),
        @ActivationConfigProperty(propertyName="messageSelector",
                                  propertyValue="RECIPIENT='ReportProcessor'")
})
public class ReportProcessorBean implements javax.jms.MessageListener {
    public void onMessage(javax.jms.Message message) {
        System.out.println("Processing message: " + message.toString());
    }
}
