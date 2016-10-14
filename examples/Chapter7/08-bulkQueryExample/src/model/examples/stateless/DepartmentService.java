package examples.stateless;

import java.util.Collection;

import examples.model.Department;

public interface DepartmentService {
    public void removeDepartmentFailure();
    public void removeDepartment();
    public Department findDepartment(int id);
    public Collection<Department> findAllDepartments();
}
