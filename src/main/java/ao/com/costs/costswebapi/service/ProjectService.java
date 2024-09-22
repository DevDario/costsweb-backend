package ao.com.costs.costswebapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ao.com.costs.costswebapi.domain.Project;
import ao.com.costs.costswebapi.exception.ProjectNotFoundException;
import ao.com.costs.costswebapi.repository.ProjectRepository;

@Service
public class ProjectService {
    
    @Autowired
    ProjectRepository projectRepository;

    // CREATE a new Project
    public ResponseEntity<?> createProject(@RequestBody Project project){
        projectRepository.save(project);

        return ResponseEntity.ok().build();
    }

    // GET's all projects
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    // GET one project
    public Project getSingleProject(Long id) throws ProjectNotFoundException{
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    // EDIT's(PUT) a project
    public ResponseEntity<?> editProject(@PathVariable Long id, @RequestBody Project projectDetails) throws ProjectNotFoundException{
        
        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException(id));

        project.setName(projectDetails.getName());
        project.setBudget(projectDetails.getBudget());
        project.setCategory(projectDetails.getCategory());
        project.setNumServices(projectDetails.getNumServices());
        project.setServiceID(projectDetails.getServiceID());
        project.setUsedBudget(projectDetails.getUsedBudget());
        project.setDeadline(projectDetails.getDeadline());
        project.setCreatedAt(projectDetails.getCreatedAt());

        projectRepository.save(project);

        return ResponseEntity.ok().build();
    }

    // DELETE's a project
    public ResponseEntity<?> deleteProject(@PathVariable Long id) throws ProjectNotFoundException{
        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException(id));

        projectRepository.delete(project);

        return ResponseEntity.ok().build();
    }
}
