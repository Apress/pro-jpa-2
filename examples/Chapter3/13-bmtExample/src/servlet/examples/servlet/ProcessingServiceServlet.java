package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.ProcessingService;

public class ProcessingServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Bean Managed Transaction (BMT) Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basic use of bean managed transactions in an EJB. </br>" +
        "Click the 'Process' button.  This will trigger a servlet client that invokes " +
        "a SLSB that uses bean managed transactions. The SLSB first injects an instance of " +
        "UserTransaction and then uses its API to demarcate the transaction.";

    @EJB
    ProcessingService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        if (request.getParameter("action") != null) {
            service.process();
            out.println("Processing done");
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
        out.println("<form action=\"ProcessingServiceServlet\" method=\"POST\">");
        out.println("<input name=\"action\" type=\"submit\" value=\"Process\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
