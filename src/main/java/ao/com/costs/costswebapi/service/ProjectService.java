package ao.com.costs.costswebapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> createProject(@RequestBody Project project){
        project.setServices(List.of());
        project.setCreatedAt(LocalDateTime.now());
        projectRepository.save(project);

        return ResponseEntity.ok()
                .body("your project was successfully created !");
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
    public ResponseEntity<String> editProject(@PathVariable Long id, @RequestBody Project projectDetails) throws ProjectNotFoundException{
        
        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException(id));

        project.setName(projectDetails.getName());
        project.setBudget(projectDetails.getBudget());
        project.setCategory(projectDetails.getCategory());

        projectRepository.save(project);

        return ResponseEntity.ok()
                .body("your project has been successfully updated");
    }

    // DELETE's a project
    public ResponseEntity<String> deleteProject(@PathVariable Long id) throws ProjectNotFoundException{
        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException(id));

        projectRepository.delete(project);

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Project Deleted !");
    }
}
