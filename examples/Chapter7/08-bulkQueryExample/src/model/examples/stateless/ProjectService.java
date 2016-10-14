package examples.stateless;

import java.util.Collection;

import examples.model.Project;

public interface ProjectService {
    public void removeEmptyProjects();
    public Collection<Project> findAllProjects();
}
