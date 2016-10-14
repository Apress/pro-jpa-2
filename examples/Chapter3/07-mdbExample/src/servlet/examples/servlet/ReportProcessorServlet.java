package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportProcessorServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Message Driven Bean Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics for defining and accessing a Message Driven Bean. </br>" +
        "Enter the message text and click 'Send'.  This will trigger a servlet " +
        "client that sends a JMS message that will be processed by an MDB.";


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        
        // if there was info submitted, send the message
        String messageText = request.getParameter("message");
        if (messageText != null) {
            try {
                QueueConnectionFactory factory = (QueueConnectionFactory)
                    new InitialContext().lookup("java:comp/env/jms/MyQueueConnectionFactory");
                Queue destinationQueue = (Queue)
                    new InitialContext().lookup("java:comp/env/jms/MyQueue");
                QueueConnection connection = factory.createQueueConnection();
                QueueSession session = connection.createQueueSession(false,
                                            Session.AUTO_ACKNOWLEDGE);
        
                QueueSender sender = session.createSender(null);
                Message message = session.createTextMessage(messageText);
                message.setStringProperty("RECIPIENT", "ReportProcessor");
                sender.send(destinationQueue, message);
                connection.close();
    
                // print a response to the html stream
                out.println("Message \"" + messageText + "\" sent! See the console " +
                            "or the log file at &lt;EXAMPLES_HOME&gt;/glassfish/domains/domain1/logs/server.log.");
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("</hr>");
        out.println("<form action=\"ReportProcessorServlet\" method=\"POST\">");
        out.println("<table><tbody>");
        out.println("<tr><td>Message Text:</td><td><input type=\"text\" name=\"message\"/></td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"Send\"/</td></tr>");
        out.println("</tbody></table>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
