package examples.stateful;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import examples.model.Order;

@Stateful
// Resource declaration is covered later in the chapter.
// use of mappedName is vendor specific.  In this case, it is used
// to specify the JNDI location of the datasource to use.
@Resource(name="jdbc/ds", type=DataSource.class, mappedName="jdbc/sfsbLifecycleExample")
public class OrderBrowserBean implements OrderBrowser {
    DataSource ds;
    Connection conn;

    @PostConstruct
    public void init() {
        // acquire the data source
        try {
            ds = (DataSource)
                new InitialContext().lookup("java:comp/env/jdbc/ds");
        } catch (Exception e) {
            throw new EJBException(e);
        }
        acquireConnection();
    }
    
    @PrePassivate
    public void passivate() {
        releaseConnection();
    }

    @PostActivate
    public void activate() {
        acquireConnection();
    }

    @PreDestroy
    public void shutdown() {
        releaseConnection();
    }
    
    private void acquireConnection() {
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            throw new EJBException(e);
        }
    }

    private void releaseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
        }
        conn = null;
    }

    public Collection<Order> listOrders() {
        // ...
        return new ArrayList<Order>();
    }
}

