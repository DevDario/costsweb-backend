package ao.com.costs.costswebapi.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ao.com.costs.costswebapi.domain.Project;
import ao.com.costs.costswebapi.exception.ProjectNotFoundException;
import ao.com.costs.costswebapi.service.ProjectService;



@RestController
@CrossOrigin(origins="http://localhost:3333", allowCredentials = "true")
@AllArgsConstructor
public class ProjectController {

    ProjectService projectService;

    @PostMapping("/project/new")
    public ResponseEntity<String> createProject(@RequestBody Project project, Authentication auth) throws Exception {
        return projectService.createProject(project, auth);
    }
    

    @GetMapping("/project/all")
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable Long id) throws ProjectNotFoundException{
        return projectService.getSingleProject(id);
    }

    @PutMapping("/project/edit/{id}")
    public ResponseEntity<String> editProject(@PathVariable Long id, @RequestBody Project projectDetails) throws ProjectNotFoundException {
        return projectService.editProject(id, projectDetails);
    }

    @DeleteMapping("/project/del/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) throws ProjectNotFoundException{
        return projectService.deleteProject(id);
    }
}
