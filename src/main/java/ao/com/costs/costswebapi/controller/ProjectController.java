package ao.com.costs.costswebapi.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<String> createProject(@RequestBody Project project, @org.jetbrains.annotations.NotNull @AuthenticationPrincipal OAuth2User principal) throws Exception {
        String email = principal.getAttribute("email");
        return projectService.createProjectForUser(project, email);
    }
    

    @GetMapping("/project/all")
    public List<Project> getAllProjects(@AuthenticationPrincipal OAuth2User principal) throws Exception{
        String email = principal.getAttribute("email");
        return projectService.findAllProjectsFromUser(email);
    }

    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal) throws ProjectNotFoundException, Exception{
        String email = principal.getAttribute("email");
        return projectService.findSingleProjectFromUser(id,email);
    }

    @PutMapping("/project/edit/{id}")
    public ResponseEntity<String> editProject(@PathVariable Long id, @RequestBody Project projectDetails, @AuthenticationPrincipal OAuth2User principal) throws Exception {
        String email = principal.getAttribute("email");
        return projectService.editProjectFromUser(id, projectDetails,email);
    }

    @Transactional
    @DeleteMapping("/project/del/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id,@AuthenticationPrincipal OAuth2User principal) throws ProjectNotFoundException, Exception{
        String email = principal.getAttribute("email");
        return projectService.deleteProjectFromUser(id,email);
    }
}
