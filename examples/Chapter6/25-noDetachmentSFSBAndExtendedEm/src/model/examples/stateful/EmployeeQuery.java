package examples.stateful;

import java.util.List;

public interface EmployeeQuery {
    public List findAll();
    public void finished();
}