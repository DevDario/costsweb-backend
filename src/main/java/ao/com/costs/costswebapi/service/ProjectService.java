package ao.com.costs.costswebapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.com.costs.costswebapi.domain.Project;
import ao.com.costs.costswebapi.repository.ProjectRepository;

@Service
public class ProjectService {
    
    @Autowired
    ProjectRepository projectRepository;

    // GET's all projects
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
}
