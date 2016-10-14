package examples.stateless;

import javax.ejb.Stateless;

@Stateless
public class HelloServiceBean implements HelloServiceRemote {
    public String sayHello(String name) {
        return "Hello, "  + name;
    }
}
