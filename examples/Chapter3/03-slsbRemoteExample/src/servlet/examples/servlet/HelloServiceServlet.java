package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.stateless.HelloServiceRemote;

public class HelloServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Remote Stateless Session Bean Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates access to a Stateless Session Bean by using " +
        "its remote interface.</br>" +
        "Enter a name and click 'Go'.  This will trigger a servlet client that talks " +
        "to a Stateless Session Bean to create a 'hello' String that is then " +
        "displayed in the browser.";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        
        // if there was a name submitted, print the hello string
        String name = request.getParameter("name");
        if (name != null) {
            // lookup the HelloService
            HelloServiceRemote service = null;
            try {
                service = (HelloServiceRemote)
                    new InitialContext().lookup("java:comp/env/ejb/HelloService");
            } catch (Exception e) {
                throw new ServletException(e);
            }
            
            // use the service to print the 'hello' string to the html stream
            out.println(service.sayHello(name));
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
        out.println("<form action=\"HelloServiceServlet\" method=\"GET\">");
        out.println("<table><tbody>");
        out.println("<tr><td>Name:</td><td><input type=\"text\" name=\"name\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<input name=\"Run\" type=\"submit\" value=\"Go\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
