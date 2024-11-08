package ao.com.costs.costswebapi.service;

import java.time.LocalDateTime;
import java.util.List;

import ao.com.costs.costswebapi.domain.User;
import ao.com.costs.costswebapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ao.com.costs.costswebapi.domain.Project;
import ao.com.costs.costswebapi.exception.ProjectNotFoundException;
import ao.com.costs.costswebapi.repository.ProjectRepository;

@Service
@AllArgsConstructor
public class ProjectService {

    ProjectRepository projectRepository;
    UserService userService;

    // CREATE a new Project
    public ResponseEntity<String> createProject(@RequestBody Project project, Authentication auth) throws Exception{

        OAuth2User principal = (OAuth2User) auth.getPrincipal();
        String loggedUserEmail = principal.getAttribute("email");

        User loggedUser = this.userService.getUserByEmail(loggedUserEmail);

        project.setServices(List.of());
        project.setCreatedAt(LocalDateTime.now());
        project.setUser(loggedUser);

        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED).build();
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
        
        Project project = this.getSingleProject(id);

        project.setName(projectDetails.getName());
        project.setBudget(projectDetails.getBudget());
        project.setCategory(projectDetails.getCategory());

        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    // DELETE's a project
    public ResponseEntity<String> deleteProject(@PathVariable Long id) throws ProjectNotFoundException{

        Project project = this.getSingleProject(id);
        projectRepository.delete(project);

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
    }
}
