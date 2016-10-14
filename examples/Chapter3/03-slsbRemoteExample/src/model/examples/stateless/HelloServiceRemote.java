package examples.stateless;

import javax.ejb.Remote;

@Remote
public interface HelloServiceRemote {
    public String sayHello(String name);
}
