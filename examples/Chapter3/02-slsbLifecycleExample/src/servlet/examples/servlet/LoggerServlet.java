package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.Logger;

public class LoggerServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Stateless Session Bean Lifecycle Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic use of lifecycle callbacks to initialize a Stateless Session Bean. </br>" +
        "Enter a and click 'Go'.  This will trigger a servlet client that talks " +
        "to a Stateless Session Bean to log a message.";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        
        // if there was a message submitted, log it
        String message = request.getParameter("message");
        if (message != null) {
            // lookup the Logger
            Logger service = null;
            try {
                service = (Logger)
                    new InitialContext().lookup("java:comp/env/ejb/Logger");
            } catch (Exception e) {
                throw new ServletException(e);
            }
            
            // use the logger bean to log a message
            service.logMessage(message);
            out.println("Message '" + message + "' sent to logger.  " +
            "See the console or the log file at &lt;EXAMPLES_HOME&gt;/glassfish/domains/domain1/logs/server.log.");
        }

        
        printHtmlFooter(out);
    }
    
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("</hr>");
        out.println("<form action=\"LoggerServlet\" method=\"GET\">");
        out.println("<table><tbody>");
        out.println("<tr><td>Message:</td><td><input type=\"text\" name=\"message\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<input name=\"action\" type=\"submit\" value=\"Go\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
