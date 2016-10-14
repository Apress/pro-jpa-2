package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.DepartmentService;

public class DepartmentServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Setter Injection Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates setter injection of a dependency. </br>" +
        "Click the 'Audit' button.  This will trigger a servlet client that looks " +
        "up a SLSB dependency that was defined in the web.xml file. That SLSB in turn " +
        "accesses another SLSB that was automatically injected into the setter by " +
        "the container using the @EJB annotation. The example is identical to the " +
        "previous one, except that the SLSB uses setter injection to aquire its dependency.";

    DepartmentService service;
    
    @EJB
    public void setDepartmentService(DepartmentService service) {
        this.service = service;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        if (request.getParameter("action") != null) {
            service.performAudit();
            out.println("Audit Performed");
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
        out.println("<form action=\"DepartmentServiceServlet\" method=\"POST\">");
        out.println("<input name=\"action\" type=\"submit\" value=\"Audit\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
