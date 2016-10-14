package examples.stateless;

import examples.model.Department;


public interface DepartmentManager {
    public void init(int deptId);
    public Department getDepartment();
    public String getName();
    public void setName(String name);
    public void addEmployee(int empId);
    public void addEmployee2(int empId);
    public void finished();
}
