package examples.stateless;

import java.util.Collection;

import examples.model.Project;

public interface ProjectService {
    public void assignEmployeeToProject(int empId, int projectId);
    public Collection<Project> findAllProjects();
}
